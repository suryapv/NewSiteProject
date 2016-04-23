// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector;

import java.util.TimerTask;

import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author umesh
 *
 */
public abstract class BaseTimerTaskCollector extends TimerTask implements Collector, Runnable {

    protected CollectionTarget collectionTarget;

    public BaseTimerTaskCollector(CollectionTarget ct) {
	collectionTarget = ct;
    }
    
    /* (non-Javadoc)
     * @see com.neosemantix.newsite.collector.Collector#getCollectionTarget()
     */
    public final CollectionTarget getCollectionTarget(){
	return collectionTarget;
    }
    
    protected abstract CollectionArg getRecurrentCollectionArgs();
    
    public void run(){
	collect(getRecurrentCollectionArgs());
    }

}
