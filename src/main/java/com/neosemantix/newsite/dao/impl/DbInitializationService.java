// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.dao.impl;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosemantix.newsite.collector.CollectionManager;
import com.neosemantix.newsite.role.RoleManager;
import com.neosemantix.newsite.user.UserManager;

/**
 * @author umesh
 *
 */
@Service
public class DbInitializationService {

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private CollectionManager collectionManager;

    private AtomicBoolean initialized;

    private static final Logger logger = LogManager.getLogger("ConsoleAndFile");

    public DbInitializationService() {
	logger.info("Constructed.");
	initialized = new AtomicBoolean();
    }

    public boolean isInitialized() {
	if (!initialized.get()) {
	    synchronized (DbInitializationService.class) {
		// check again in case other thread has done it
		if (!initialized.get()) {
		    try {
			initialize();
			this.initialized.compareAndSet(false, true);
		    } catch (Throwable t) {
			logger.error("Failed to initialize DB.", t);
		    }
		}
	    }
	}
	return initialized.get();
    }

    private void initialize() {
	if (!roleManager.fixedRolesPresent()) {
	    roleManager.createFixedRoles();
	}
	userManager.createFixedUsers(roleManager.getAllRoles());
	logger.info("DB initialization complete.");
    }

}
