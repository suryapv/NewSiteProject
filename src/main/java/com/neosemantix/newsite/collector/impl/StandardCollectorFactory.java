// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.neosemantix.newsite.collector.BaseTimerTaskCollector;
import com.neosemantix.newsite.collector.CollectorFactory;
import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author umesh
 *
 */
public class StandardCollectorFactory implements CollectorFactory {

    private static StandardCollectorFactory singletonInstance;

    public static StandardCollectorFactory getInstance() {
	if (singletonInstance == null) {
	    synchronized (StandardCollectorFactory.class) {
		if (singletonInstance == null) {
		    singletonInstance = new StandardCollectorFactory();
		}
	    }
	}
	return singletonInstance;
    }

    private StandardCollectorFactory() {
	// TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.neosemantix.newsite.collector.CollectorFactory#getInstance(com.neosemantix.newsite.model.CollectionTarget)
     */
    public BaseTimerTaskCollector getInstance(CollectionTarget ct) {
	BaseTimerTaskCollector result = null;
	if (ct != null) {
	    String collectorClassName = ct.getCollectorClass();
	    Class collectorClass;
	    try {
		collectorClass = Class.forName(collectorClassName);
		Class[] argTypes = { CollectionTarget.class };
		Constructor constructor = collectorClass.getDeclaredConstructor(argTypes);
		Object[] arguments = { ct };
		result = (BaseTimerTaskCollector) constructor.newInstance(arguments);
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	return result;
    }

}
