// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector.impl.blog;

import java.util.List;

import com.neosemantix.newsite.collector.CollectionArg;
import com.neosemantix.newsite.collector.Collector;
import com.neosemantix.newsite.model.Article;

/**
 * @author umesh
 *
 */
public interface AccessibleBlog {

    /**
     * @param arg
     * @return
     */
    String getUrl(CollectionArg arg);

    /**
     * Parse the incoming JSON Blog Server output into 'article list'.
     * 
     * @param json
     *            which is obtained from the blog server
     * @return List<Article> List articles which are to be 'harvested' from the
     *         blog.
     */
    List<Article> yieldArticles(String json);
    
    /**
     * Return the collector which is accessing or harvesting this blog for articles / content.
     * @return Collector
     */
    Collector getHarvester();
}
