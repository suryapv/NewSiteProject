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

import com.neosemantix.newsite.dao.RoleDao;
import com.neosemantix.newsite.model.Role;

/**
 * @author umesh
 *
 */
@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.dao.RoleDao#save(com.neosemantix.newsite.model.Role)
	 */
	@Override
	public short save(Role r) {
		short result = -1;
		if (r != null){
			entityManager.persist(r);
			entityManager.flush();
			result = r.getId();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.dao.RoleDao#getAllRoles()
	 */
	@Override
	public List<Role> getAllRoles() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Role> cq = builder.createQuery(Role.class);
		Root<Role> root = cq.from(Role.class);		
		cq.select(root);
		TypedQuery<Role> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public void delete(Role r) {
		if (r != null){
			entityManager.remove(r);
			entityManager.flush();
		}		
	}

}
