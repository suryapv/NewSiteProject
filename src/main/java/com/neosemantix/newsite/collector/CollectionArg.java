// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector;

/**
 * @author upatil
 *
 */
public class CollectionArg {
    
    public enum UrlType {

	SINGLE_POST,

	FIRST_TEN_BLOG_POSTS;

    }
    
    private UrlType urlType;
    
    private Object[] args;
    
    /**
     * @param t
     * @param as
     */
    public CollectionArg(UrlType t, Object...as){
	urlType = t;
	args = as;
    }

    /**
     * @return the urlType
     */
    public UrlType getUrlType() {
        return urlType;
    }

    /**
     * @return the args
     */
    public Object[] getArgs() {
        return args;
    }       
}
