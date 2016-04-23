// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.user;

import java.util.Map;

import com.neosemantix.newsite.model.Role;
import com.neosemantix.newsite.model.User;

/**
 * @author umesh
 *
 */
public interface UserManager {
	
	/**
	 * Resets the password for the given user.
	 * 
	 * @param userName
	 * @param oldPassword
	 * @param newPassword
	 */
	void resetPassword(String userName, String oldPassword, String newPassword);
	
	/**
	 * Adds a new user.
	 * 
	 * @param u
	 */
	void addUser(User u);
	
	/**
	 * Deletes an existing user.
	 * 
	 * @param u
	 */
	void deleteUser(User u);
	
	/**
	 * Updates properties of an existin user.
	 * 
	 * @param u
	 */
	void updateUser(User u);
	
	/**
	 * Accessor for user by user name.
	 * 
	 * @param userName
	 * @return User
	 */
	User getUser(String userName);
	
	/**
	 * @param roleMap Map of role names and roles used to create users of appropriate roles.
	 */
	void createFixedUsers(Map<String, Role> roleList);

}
