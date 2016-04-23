// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.neosemantix.newsite.dao.ArticleDao;
import com.neosemantix.newsite.model.Article;

/**
 * @author umesh
 *
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {

    @PersistenceContext
    private EntityManager entityManager;

    /* (non-Javadoc)
     * @see com.neosemantix.newsite.dao.ArticleDao#save(com.neosemantix.newsite.model.Article)
     */
    @Override
    @Transactional
    public int save(Article art) {
	int result = -1;
	if (art != null) {
	    entityManager.persist(art);
	    entityManager.flush();
	    result = art.getId();
	}
	return result;
    }
    
    public Article update(Article art) {
	Article result = null;
	if (art != null){
	    result = entityManager.merge(art);
	}
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.neosemantix.newsite.dao.ArticleDao#get(int)
     */
    @Override
    public Article get(int id) {
	return entityManager.find(Article.class, id);
	// Article result = null;
	// CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	// CriteriaQuery<Article> cq = builder.createQuery(Article.class);
	// Root<Article> root = cq.from(Article.class);
	// cq.select(root);
	// ParameterExpression<Integer> p = builder.parameter(Integer.class);
	// cq.where(builder.equal(root.get("id"), p));
	// List<Article> articleList =
	// entityManager.createQuery(cq).getResultList();
	// if (!CollectionUtils.isEmpty(articleList)){
	// result = articleList.get(0);
	// }
	// return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.neosemantix.newsite.dao.ArticleDao#getLatest(int)
     */
    @Override
    @Transactional
    public List<Article> getLatest(int howMany) {
	int limit = 5; // fixed limit
	if (howMany > 0) {
	    limit = howMany;
	}
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<Article> cq = builder.createQuery(Article.class);
	Root<Article> root = cq.from(Article.class);
	cq.select(root);
	cq.orderBy(builder.desc(root.get("lastUpdate")));
	TypedQuery<Article> query = entityManager.createQuery(cq);
	query.setMaxResults(limit);
	List<Article> result = query.getResultList();
	return result;
    }
}
