// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.article;

import java.util.List;

import com.neosemantix.newsite.model.Article;

/**
 * @author umesh
 *
 */
public interface ArticleManager {

    /**
     * Creates a new article. Assign internally generated id.
     * 
     * @param title
     * @param authors
     * @param url
     * @param referenced
     * @param summary
     * @param tags
     * @return
     */
    Article create(String title, String authors, String url,
	    boolean referenced, String summary, String tags);

    /**
     * @param newArticle
     *            New article to be inserted.
     * @return int Id of the article inserted successfully.
     */
    int insert(Article newArticle);

    /**
     * Update the article. TODO - throw invalid exception if incorrect id
     * 
     * @param newArticle
     *            with the same article id.
     */
    void update(Article newArticle);

    /**
     * @param id
     * @return Article
     */
    Article get(int id);

    /**
     * @param howMany
     * @return List<Article>
     */
    List<Article> getMostRecent(int howMany);

    List<Article> searchByTags(List<String> tagList);

}
