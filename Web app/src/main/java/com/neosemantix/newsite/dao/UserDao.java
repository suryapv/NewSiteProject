// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.dao;

import com.neosemantix.newsite.model.User;

/**
 * @author umesh
 *
 */
public interface UserDao {
	
	/**
	 * Insert or update user.
	 * 
	 * @param u
	 * @return int User id, internally generated
	 */
	int save(User u);
	
	/**
	 * @param id
	 * @return User return the specified user.
	 */
	User get(int id);
	
	/**
	 * @param userName
	 * @return User which matches to the user name
	 */
	User get(String userName);
	
	/**
	 * @param u Delete specified user.
	 */
	void delete(User u);

}
