// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A simple global cache populated at bootstrap time and during life span of the
 * application.
 * 
 * @author upatil
 *
 */
public class Registry {

    private Properties cache;

    private Registry() throws IOException {
	cache = new Properties();
	// the base folder is ./, the root of the newsite.properties file
	String path = "./newsite.properties";
	FileInputStream file;
	file = new FileInputStream(path);
	// load all the properties from this file
	cache.load(file);
	file.close();
    }

    private static Registry singleton;

    public static Registry getInstance() throws IOException {
	if (singleton != null) {
	    return singleton;
	}
	synchronized (Registry.class) {
	    if (singleton == null) {
		singleton = new Registry();
	    }
	}
	return singleton;
    }

    public static String ENCRP_ONE = "String_One";

    public static String ENCRP_TWO = "String_Two";

    public Object getValue(Object key) {
	return cache.get(key);
    }
    
    public String getEncryptionKeyOne(){
	return (String)cache.get(ENCRP_ONE);
    }
    
    public String getEncryptionKeyTwo(){
	return (String)cache.get(ENCRP_TWO);
    }

}
