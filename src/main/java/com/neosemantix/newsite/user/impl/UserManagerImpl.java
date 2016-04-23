// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.user.impl;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosemantix.newsite.dao.UserDao;
import com.neosemantix.newsite.model.Role;
import com.neosemantix.newsite.model.User;
import com.neosemantix.newsite.role.impl.RoleManagerImpl.DefinedRoles;
import com.neosemantix.newsite.user.UserManager;

/**
 * @author umesh
 *
 */
@Service
public class UserManagerImpl implements UserManager {
	
	static final Logger logger = LogManager.getLogger("ConsoleAndFile");
	
	@Autowired
	private UserDao userDao;

	public UserManagerImpl() {
		
	}

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.user.UserManager#resetPassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public void resetPassword(String userName, String oldPassword,
			String newPassword) {
		User u = this.getUser(userName);
		if (u != null && u.getPassword().equals(oldPassword)){
			u.setPassword(newPassword);
			userDao.save(u);
		}		
	}

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.user.UserManager#addUser(com.neosemantix.newsite.model.User)
	 */
	@Override
	@Transactional
	public void addUser(User u) {
		if (u != null){
			userDao.save(u);
		}
	}

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.user.UserManager#deleteUser(com.neosemantix.newsite.model.User)
	 */
	@Override
	@Transactional
	public void deleteUser(User u) {
		if (u != null){
			userDao.delete(u);
		}
	}

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.user.UserManager#updateUser(com.neosemantix.newsite.model.User)
	 */
	@Override
	@Transactional
	public void updateUser(User u) {
		if (u != null){
			userDao.save(u);
		}
	}

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.user.UserManager#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String userName) {
		User result = null;
		if (userName != null){
			result = userDao.get(userName);
		}
		return result;
	}


	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.user.UserManager#createFixedUsers(java.util.Map)
	 */
	@Override
	public void createFixedUsers(Map<String, Role> roleMap) {		
		
		if (getUser("umesh") == null){
			addUser(new User("Umesh", "Patil", "umesh", "umesh10281@gmail.com", roleMap.get(DefinedRoles.ADMIN.name())));
			logger.info("Created fixed user Umesh");
		}		
		
		if (getUser("uday") == null){
			addUser(new User("Uday", "Oak", "uday", "udayoak@gmail.com", roleMap.get(DefinedRoles.COMMENTATOR.name())));
			logger.info("Created fixed user Uday");
		}
		
		if (getUser("ratnakar") == null){
			addUser(new User("Ratnakar", "Tripathy", "ratnakar", "tripathy.ratnakar@gmail.com", roleMap.get(DefinedRoles.COMMENTATOR.name())));
			logger.info("Created fixed user Ratnakar");
		}
	}

}
