// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.neosemantix.newsite.dao.UserDao;
import com.neosemantix.newsite.model.User;
import com.neosemantix.newsite.utility.Encryptor;

/**
 * @author umesh
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.neosemantix.newsite.dao.UserDao#save(com.neosemantix.newsite.model
     * .User)
     */
    @Override
    @Transactional
    public int save(User u) {
	int result = -1;
	if (u != null) {
	    // just before persistence, we encrypt the password
	    encryptPassword(u);
	    entityManager.persist(u);
	    entityManager.flush();
	}
	return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.neosemantix.newsite.dao.UserDao#get(int)
     */
    @Override
    public User get(int id) {
	User u = entityManager.find(User.class, id);
	if (u != null) {
	    decryptPassword(u);
	}
	return u;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.neosemantix.newsite.dao.UserDao#delete(com.neosemantix.newsite.model
     * .User)
     */
    @Override
    public void delete(User u) {
	if (u != null) {
	    entityManager.remove(u);
	}
    }

    /* (non-Javadoc)
     * @see com.neosemantix.newsite.dao.UserDao#get(java.lang.String)
     */
    @Override
    public User get(String userName) {
	User result = null;
	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	CriteriaQuery<User> cq = builder.createQuery(User.class);
	Root<User> root = cq.from(User.class);
	cq.select(root);
	cq.where(builder.equal(root.get("username"), userName));
	List<User> userList = entityManager.createQuery(cq).getResultList();
	if (!CollectionUtils.isEmpty(userList)) {
	    result = userList.get(0);
	}
//	if (result != null) {
//	    // decrypt the password
//	    decryptPassword(result);
//	}
	return result;
    }

    private void encryptPassword(User u) {
	u.setPassword((new BCryptPasswordEncoder()).encode(u.getPassword()));
    }

    private void decryptPassword(User u) {
	u.setPassword(Encryptor.decrypt(u.getPassword()));
    }
}
