// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.dao;

import java.util.List;

import com.neosemantix.newsite.model.Role;

/**
 * @author umesh
 *
 */
public interface RoleDao {
	
	// we will not any role deletion, 
	// for now actually want to create 2 pre-defined roles only
	
	/**
	 * @param r
	 * @return short Role id, internally generated
	 */
	short save(Role r);
	
	/**
	 * @return List<Role> List of all defined roles
	 */
	List<Role> getAllRoles();
	
	/**
	 * Delete a defined role. This can happen only when there are no users
	 * referring to this role.
	 */
	void delete(Role r);

}
