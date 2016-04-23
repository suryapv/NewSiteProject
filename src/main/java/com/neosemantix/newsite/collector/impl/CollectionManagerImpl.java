// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosemantix.newsite.collector.BaseTimerTaskCollector;
import com.neosemantix.newsite.collector.CollectionManager;
import com.neosemantix.newsite.dao.CollectionTargetDao;
import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author umesh
 *
 */
@Service
public class CollectionManagerImpl implements CollectionManager {

    @Autowired
    private CollectionTargetDao collectionTargetDao;

    private StandardCollectorFactory factory;

    private Map<String, CollectionTarget> collectionTargets;

    public CollectionManagerImpl() {
	factory = StandardCollectorFactory.getInstance();
	collectionTargets = new HashMap<String, CollectionTarget>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.neosemantix.newsite.collector.CollectionManager#getCollector(java
     * .lang.String)
     */
    @Override
    public BaseTimerTaskCollector getCollector(String name) {
	BaseTimerTaskCollector result = null;
	if (name != null) {
	    updateMap();
	    CollectionTarget ct = collectionTargets.get(name);
	    if (ct != null) {
		result = factory.getInstance(ct);
	    }
	}
	return result;
    }
    
    private void updateMap() {
	if (collectionTargets.isEmpty()) {
	    synchronized (this) {
		List<CollectionTarget> targetList = collectionTargetDao.getAllCollectionTargets();
		if (targetList != null) {
		    for (CollectionTarget ct : targetList) {
			collectionTargets.put(ct.getName(), ct);
		    }
		}
	    }
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.neosemantix.newsite.collector.CollectionManager#getCollections()
     */
    @Override
    public List<CollectionTarget> getAllCollectionTargets() {
	updateMap();
	return new ArrayList<CollectionTarget>(collectionTargets.values());
    }

//    /*
//     * (non-Javadoc)
//     * 
//     * @see com.neosemantix.newsite.collector.CollectionManager#
//     * createWellKnownCollectionTargets()
//     */
//    public void createWellKnownCollectionTargets() {
//	// let us see whether 'one' fixes target is to be created or not
//	CollectionTarget gb = GoogleBlog.get21CpTarget();
//	if (gb != null) {
//	    CollectionTarget ct = collectionTargets.get(gb.getName());
//	    if (ct == null) {
//		// no target is created, we need to do that
//		collectionTargetDao.update(gb);
//		// and update the cache as well
//		collectionTargets.put(gb.getName(), gb);
//	    }
//	    // else it is already present, no need to create
//	}
//	// else nothing to do here for now; no fixed targets
//    }
}
