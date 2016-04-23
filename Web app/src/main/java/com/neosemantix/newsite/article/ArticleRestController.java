// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosemantix.newsite.model.Article;

/**
 * @author umesh
 *
 */
@RestController
public class ArticleRestController {

    @Autowired
    private ArticleManager articleManager;

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public List<Article> getArticles() {
	// let us fix for 5 now
	List<Article> result = articleManager.getMostRecent(5); 
	return result;
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public int createArticle(@RequestParam(value = "title") String title,
	    @RequestParam(value = "authors") String authors) {
	Article art = articleManager.create(title, authors, "", false, "", "");
	return art.getId();
    }

}
