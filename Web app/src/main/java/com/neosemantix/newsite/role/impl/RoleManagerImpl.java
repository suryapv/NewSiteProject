// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.role.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosemantix.newsite.dao.RoleDao;
import com.neosemantix.newsite.model.Role;
import com.neosemantix.newsite.role.RoleManager;

/**
 * @author umesh
 *
 */
@Service
public class RoleManagerImpl implements RoleManager {
	
	static final Logger logger = LogManager.getLogger("ConsoleAndFile");
	
	public enum DefinedRoles{
		ADMIN,
		COMMENTATOR;
	}
	
	@Autowired
	private RoleDao roleDao;
	
	public RoleManagerImpl(){
		// debugging purposes
		logger.info("Constructed");
	}

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.role.RoleManager#fixedRolesPresent()
	 */
	@Transactional
	public boolean fixedRolesPresent(){
		List<Role> roles = roleDao.getAllRoles();
		if (roles != null && roles.size() == 2){
			if (isPresent(roles, DefinedRoles.ADMIN) &&
					isPresent(roles, DefinedRoles.COMMENTATOR)){
				logger.info("Found predefined roles, do not do anything.");
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.role.RoleManager#createFixedRoles()
	 */
	@Override
	@Transactional
	public void createFixedRoles() {
		// assumed that there are no such roles already defined, else we will
		// get persistence exception
		roleDao.save(new Role(DefinedRoles.ADMIN.name()));
		logger.info("Created ADMIN Role.");
		roleDao.save(new Role(DefinedRoles.COMMENTATOR.name()));
		logger.info("Created COMMENTATOR Role.");
	}
	
	private boolean isPresent(List<Role> roles, DefinedRoles dfr){
		boolean result = false;
		for(Role r: roles){
			if (r.getName().equals(dfr.name())){
				result = true;
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.neosemantix.newsite.role.RoleManager#getAllRoles()
	 */
	@Override
	public Map<String, Role> getAllRoles() {
		Map<String, Role> roleMap = new HashMap<String, Role>();
		List<Role> roles = roleDao.getAllRoles();
		if (roles != null){
			for (Role r: roles){
				roleMap.put(r.getName(), r);
			}
		}
		return roleMap;
	}

}
