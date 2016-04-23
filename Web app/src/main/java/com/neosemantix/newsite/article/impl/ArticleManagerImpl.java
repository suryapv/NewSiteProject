// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.article.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosemantix.newsite.article.ArticleManager;
import com.neosemantix.newsite.collector.CollectionArg;
import com.neosemantix.newsite.collector.CollectionArg.UrlType;
import com.neosemantix.newsite.collector.CollectionManager;
import com.neosemantix.newsite.collector.Collector;
import com.neosemantix.newsite.dao.ArticleDao;
import com.neosemantix.newsite.model.Article;

/**
 * @author umesh
 *
 */
@Service
public class ArticleManagerImpl implements ArticleManager {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CollectionManager collectionMgr;

    private static ArticleCache contentCache;

    public ArticleManagerImpl() {
	if (contentCache == null) {
	    synchronized (ArticleManagerImpl.class) {
		if (contentCache == null) {
		    // it is still null, instantiate now
		    contentCache = new ArticleCache();
		}
		// else another thread already did that
	    }
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.neosemantix.newsite.article.ArticleManager#create(java.lang.String,
     * java.lang.String, java.lang.String, boolean, java.lang.String,
     * java.lang.String)
     */
    @Override
    @Transactional
    public Article create(String title, String authors, String url, boolean referenced, String summary, String tags) {
	Article result = new Article(title, authors, url, referenced, summary, tags);
	articleDao.save(result);
	contentCache.stash(result);
	return result;
    }

    @Override
    @Transactional
    public int insert(Article newArticle) {
	int id = -1;
	if (newArticle != null) {
	    id = articleDao.save(newArticle);
	    contentCache.stash(newArticle);
	}
	return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.neosemantix.newsite.article.ArticleManager#update(com.neosemantix
     * .newsite.model.Article)
     */
    @Override
    @Transactional
    public void update(Article newArticle) {
	if (newArticle != null) {
	    articleDao.update(newArticle);
	    contentCache.stash(newArticle);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.neosemantix.newsite.article.ArticleManager#get(int)
     */
    @Override
    public Article get(int id) {
	Article a = articleDao.get(id);
	if (a != null && a.getContent() == null) {
	    fetchArticle(a); // this inserts or updates an article
	    a = contentCache.get(a.getSiteId());
	}
	// no option to refresh content?
	return a;
    }

    private void fetchArticle(Article a) {
	// we have the article but do not have contents, let us try to fetch it
	// we need to get collector first, we get based on collection target
	// name which is associated with every article
	String collectionTarget = a.getCollectionTarget();
	Collector col = this.collectionMgr.getCollector(collectionTarget);
	if (col != null) {
	    Object[] args = new Object[1];
	    args[0] = a.getSiteId(); // note that we are using site id
	    CollectionArg cArg = new CollectionArg(UrlType.SINGLE_POST, args);
	    col.collect(cArg);
	} else {
	    System.out.println("Failed to obtain collector for target: " + collectionTarget + " for article: "
		    + a.getId());
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.neosemantix.newsite.article.ArticleManager#getMostRecent(int)
     */
    @Override
    public List<Article> getMostRecent(int howMany) {
	return articleDao.getLatest(howMany);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.neosemantix.newsite.article.ArticleManager#searchByTags(java.util
     * .List)
     */
    @Override
    public List<Article> searchByTags(List<String> tagList) {
	// TODO Auto-generated method stub
	return null;
    }

    private static class ArticleCache {

	private Map<String, Article> contentMap;

	private ArticleCache() {
	    contentMap = new HashMap<String, Article>();
	}

	private synchronized void stash(Article a) {
	    // a is expected to be non-null
	    contentMap.put(a.getSiteId(), a);
	}

	private synchronized Article get(String siteId) {
	    return contentMap.get(siteId);
	}

    }
}
