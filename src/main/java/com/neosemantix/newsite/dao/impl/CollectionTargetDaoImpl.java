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

import com.neosemantix.newsite.dao.CollectionTargetDao;
import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author umesh
 *
 */
@Repository
public class CollectionTargetDaoImpl implements CollectionTargetDao {

    @PersistenceContext
    private EntityManager entityManager;

    /*
     * (non-Javadoc)
     * 
     * @see com.neosemantix.newsite.dao.CollectionTargetDao#getAllCollections()
     */
    @Override
    public List<CollectionTarget> getAllCollectionTargets() {
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<CollectionTarget> cq = builder.createQuery(CollectionTarget.class);
	Root<CollectionTarget> root = cq.from(CollectionTarget.class);
	cq.select(root);
	TypedQuery<CollectionTarget> query = entityManager.createQuery(cq);
	return query.getResultList();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.neosemantix.newsite.dao.CollectionTargetDao#update(com.neosemantix
     * .newsite.model.CollectionTarget)
     */
    @Override
    @Transactional
    public void update(CollectionTarget c) {
	if (c != null) {
	    entityManager.persist(c);
	    entityManager.flush();
	}
    }
}
