// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector.impl.blog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.neosemantix.newsite.article.ArticleManager;
import com.neosemantix.newsite.collector.BaseTimerTaskCollector;
import com.neosemantix.newsite.collector.CollectionArg;
import com.neosemantix.newsite.collector.CollectionArg.UrlType;
import com.neosemantix.newsite.model.Article;
import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author umesh
 *
 */
@Configurable
public class BlogCollector extends BaseTimerTaskCollector {

    private static long ranLast;

    private AccessibleBlog blogToAccess;

    @Autowired
    private ArticleManager articleManager;

    public BlogCollector(CollectionTarget ct) {
	super(ct);
	// let us focus on Google Blogs only for now
	// when other types come, we will have to introduce factory...
	blogToAccess = new GoogleBlog(ct, this);
    }

    protected static class ResponseHandlerImpl<String> implements
	    ResponseHandler<String> {
	public String handleResponse(final HttpResponse response)
		throws ClientProtocolException, IOException {
	    int status = response.getStatusLine().getStatusCode();
	    if (status >= 200 && status < 300) {
		// return (T) response.getEntity();
		HttpEntity entity = response.getEntity();
		return (String) (entity != null ? EntityUtils.toString(entity)
			: null);
	    } else {
		throw new ClientProtocolException(
			"Unexpected response status: " + status);
	    }
	}
    }

    private static class ArticleMap implements Iterable<Article> {

	private Map<String, Article> siteIdWiseMap = new HashMap<String, Article>();

	private ArticleMap(List<Article> articles) {
	    for (Article a : articles) {
		siteIdWiseMap.put(a.getSiteId(), a);
	    }
	}

	private boolean isEmpty() {
	    return siteIdWiseMap.isEmpty();
	}

	private void remove(List<Article> incArticles) {
	    for (Article ina : incArticles) {
		siteIdWiseMap.remove(ina.getSiteId());
	    }
	}

	@Override
	public Iterator<Article> iterator() {
	    return (new ArrayList<Article>(siteIdWiseMap.values())).iterator();
	}

	public boolean contains(Article a) {
	    boolean result = false;
	    if (a != null) {
		result = siteIdWiseMap.containsKey(a.getSiteId());
	    }
	    return result;
	}

	public Article findEquivalent(Article a) {
	    Article result = null;
	    if (a != null) {
		result = siteIdWiseMap.get(a.getSiteId());
	    }
	    return result;
	}
    }
    
    protected CollectionArg getRecurrentCollectionArgs(){
	return new CollectionArg(UrlType.FIRST_TEN_BLOG_POSTS, (Object[])null);
    }

    public void collect(CollectionArg cArgs) {
	//String url = blogToAccess.getUrl(UrlType.FIRST_TEN_BLOG_POSTS);
	if (cArgs == null){
	    System.out.println("Invalid argument to method collect ");
	}
	// we have valid arguments
	String url = blogToAccess.getUrl(cArgs);
	CloseableHttpClient httpclient = HttpClients.createDefault();
	try {
	    HttpGet httpget = new HttpGet(url);
	    System.out.println("Executing request " + httpget.getRequestLine());
	    // Create a custom response handler
	    ResponseHandler<String> responseHandler = new ResponseHandlerImpl();
	    String responseBody = httpclient.execute(httpget, responseHandler);
	    List<Article> al = blogToAccess.yieldArticles(responseBody);
	    ArticleMap cl = new ArticleMap(al);

	    // since we are fetching most recent 10 + buffer...improve
	    List<Article> mostRecentTen = articleManager.getMostRecent(10 + 5);
	    // this will keep only new ones in the list now
	    // cl.remove(mostRecentTen);

	    ArticleMap existingAls = new ArticleMap(mostRecentTen);

	    if (!cl.isEmpty()) {
		int countOfArticlesAdded = 0;
		int countOfArticlesUpdated = 0;
		for (Article ac : cl) {
		    // set the collection target for the article i.e. source from where it is harvest
		    // we know for sure that collection target is non-null here; 
		    // else harvest would not have been possible
		    ac.setCollectionTarget(collectionTarget.getName());
		    try {
			Article existingArt = existingAls.findEquivalent(ac);
			if (existingArt != null) {
			    // existing, we should simply update
			    existingArt.copy(ac);
			    articleManager.update(existingArt);
			    countOfArticlesUpdated++;
			} else {
			    // it is new
			    articleManager.insert(ac);
			    countOfArticlesAdded++;
			}
		    } catch (Throwable t) {
			// error while persisting, may site id not unique
			t.printStackTrace();
		    }
		}
		System.out.println("Inserted " + countOfArticlesAdded
			+ " and updated " + countOfArticlesUpdated
			+ " article(s).");
	    }
	    // else there is nothing new to be added
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (Throwable e) {
	    // catch TODO - improve.....
	    e.printStackTrace();
	} finally {
	    try {
		httpclient.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    // since it is primitive, expecting no issues when multiple threads
	    // would update it
	    long crtTime = System.currentTimeMillis();
	    if (crtTime > ranLast) {
		ranLast = crtTime;
	    }
	}
    }

    @Override
    public String getId() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public long whenRanLast() {
	return ranLast;
    }
}
