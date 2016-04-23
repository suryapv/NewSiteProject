// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.role;

import java.util.Map;

import com.neosemantix.newsite.model.Role;

/**
 * @author umesh
 *
 */
public interface RoleManager {
	
	boolean fixedRolesPresent();
	
	void createFixedRoles();

	Map<String, Role> getAllRoles();
}
