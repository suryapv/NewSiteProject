// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.dao;

import java.util.List;

import com.neosemantix.newsite.model.Article;

/**
 * @author umesh
 *
 */
public interface ArticleDao {
	
	/**
	 * @param art
	 * @return Internally generated id.
	 */
	int save(Article art);
	
	/**
	 * @param art
	 * @return Article
	 */
	Article update(Article art);
	
	// no delete for now
	
	/**
	 * @param id
	 * @return Article Entire article with content
	 */
	Article get(int id);
	
	/**
	 * @param howMany
	 * @return Return the list of specified number of latest articles
	 */
	List<Article> getLatest(int howMany);

}
