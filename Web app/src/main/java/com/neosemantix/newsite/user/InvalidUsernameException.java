// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.user;

import com.neosemantix.newsite.NewsiteRuntimeException;

/**
 * @author umesh
 *
 */
@SuppressWarnings("serial")
public class InvalidUsernameException extends NewsiteRuntimeException {

	/**
	 * @param offending userName
	 */
	public InvalidUsernameException(String userName) {
		super("Invalid user name - " + userName);
	}
}
