// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector.impl.blog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.neosemantix.newsite.collector.CollectionArg;
import com.neosemantix.newsite.collector.Collector;
import com.neosemantix.newsite.model.Article;
import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author umesh
 *
 */
public class GoogleBlog implements AccessibleBlog {

    private String blogId;

    private String apiKey;
    
    private CollectionTarget collectionTarget;
    
    private Collector harvester;
    
    public GoogleBlog(CollectionTarget ct, Collector c){
	if (ct == null){
	    throw new IllegalArgumentException("Cannot instantiate GoogleBlog, argument Collection Target cannot be null.");
	}	
	// TODO - Validation of the argument
	
	collectionTarget = ct;
	blogId = collectionTarget.getSiteOwnerId();
	apiKey = collectionTarget.getApiKey();
	harvester = c;
    }

    public GoogleBlog(String id, String key) {
	blogId = id;
	apiKey = key;
    }
    
    /* (non-Javadoc)
     * @see com.neosemantix.newsite.collector.impl.blog.AccessibleBlog#getHarvester()
     */
    public Collector getHarvester(){
	return this.harvester;
    }

    /**
     * @return the blogId
     */
    public String getBlogId() {
	return blogId;
    }

    /**
     * @return the apiKey
     */
    public String getApiKey() {
	return apiKey;
    }

    public String getUrl(CollectionArg collArgs) {
	String result = null;
	if (collArgs == null){
	    return result;	// return null;
	}
	com.neosemantix.newsite.collector.CollectionArg.UrlType t = collArgs.getUrlType();
	if (t == null){
	    System.out.println("No UrlType found");
	    return result;
	}
	switch (t) {
	case FIRST_TEN_BLOG_POSTS:
	    // https://www.googleapis.com/blogger/v3/blogs/9765509/posts?key=AIzaSyAnXtz5frmKEfzeoGGe5q7cUbkVfswFD8A
	    result = "https://www.googleapis.com/blogger/v3/blogs/" + blogId
		    + "/posts?key=" + apiKey;
	    break;
	    
	case SINGLE_POST:
	    Object[] urlArgs = collArgs.getArgs();
	    if (urlArgs != null && urlArgs.length > 0 && urlArgs[0] != null){
		String postId = urlArgs[0].toString();
		//https://www.googleapis.com/blogger/v3/blogs/9765509/posts/1565264685850125589?key=AIzaSyAnXtz5frmKEfzeoGGe5q7cUbkVfswFD8A
		    result = "https://www.googleapis.com/blogger/v3/blogs/" + blogId + "/posts/" + postId + "?key=" + apiKey;
	    } else {
		System.out.println("No post site id found, cannot make the url.");
	    }
	    break;
	}
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.neosemantix.newsite.collector.impl.AccessibleBlog#yieldArticles(java
     * .lang.String)
     */
    @Override
    public List<Article> yieldArticles(String json) {
	List<Article> cl = new ArrayList<Article>();
	if (json != null) {
	    Gson gson = new Gson();
	    JsonObject jo = gson.fromJson(json, JsonObject.class);
	    // we are expecting it to be an array
	    JsonArray je = (JsonArray) jo.get("items");
	    Iterator<JsonElement> it = je.iterator();
	    while (it.hasNext()) {
		try {
		    JsonObject anItem = (JsonObject) it.next();

		    Article a = new Article();

		    // set incoming id as the siteId, distinct than what DB assigns when we persist in this application
		    JsonPrimitive jsPrimitive = anItem.getAsJsonPrimitive("id");
		    a.setSiteId(jsPrimitive.getAsString());

		    jsPrimitive = anItem.getAsJsonPrimitive("title");
		    a.setTitle(jsPrimitive.getAsString());

		    jsPrimitive = anItem.getAsJsonPrimitive("url");
		    a.setUrl(jsPrimitive.getAsString());
		    
		    jsPrimitive = anItem.getAsJsonPrimitive("content");
		    a.setContent(jsPrimitive.getAsString());	// utf-8 encoded string 

		    JsonObject jsObj = anItem.getAsJsonObject("author");
		    jsPrimitive = jsObj.getAsJsonPrimitive("displayName");
		    a.setAuthors(jsPrimitive.getAsString());

		    // "updated": "2015-06-28T21:19:31-07:00", <--- ISO 8601
		    // format
		    jsPrimitive = anItem.getAsJsonPrimitive("updated");
		    Calendar clr = javax.xml.bind.DatatypeConverter
			    .parseDateTime(jsPrimitive.getAsString());
		    a.setLastUpdate(clr.getTime());

		    cl.add(a);

		} catch (Throwable t) {
		    t.printStackTrace();
		}
	    }
	}
	return cl;
    }
}
