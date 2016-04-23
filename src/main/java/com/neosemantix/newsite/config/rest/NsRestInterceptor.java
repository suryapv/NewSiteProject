// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.config.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neosemantix.newsite.dao.impl.DbInitializationService;

/**
 * @author umesh
 *
 */
public class NsRestInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LogManager.getLogger("ConsoleAndFile");
	
	@Autowired
	private DbInitializationService dbInitService;

	// before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean dbServiceInitialization = dbInitService.isInitialized();
		logger.debug("Invoked preHandle, followed by dbServiceInitialization in NsRestInterceptor. "
				+ "Result of dbServiceInitialization is: " + dbServiceInitialization);
		return dbServiceInitialization;
	}

	// after the handler is executed
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("Invoked postHandle in NsRestInterceptor; done.");		
	}

}
