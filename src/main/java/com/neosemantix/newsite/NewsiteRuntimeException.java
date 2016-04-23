// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite;

/**
 * @author umesh
 *
 */
@SuppressWarnings("serial")
public class NewsiteRuntimeException extends RuntimeException {

	public NewsiteRuntimeException() {
		super();
	}

	/**
	 * @param message
	 */
	public NewsiteRuntimeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NewsiteRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NewsiteRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NewsiteRuntimeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
