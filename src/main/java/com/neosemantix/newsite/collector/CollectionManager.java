// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector;

import java.util.List;

import com.neosemantix.newsite.model.CollectionTarget;

/**
 * 
 * @author umesh
 *
 */
public interface CollectionManager {

    /**
     * @param collectorId
     * @return Collector Returns the specified collector. Can be null, if not
     *         found.
     */
    BaseTimerTaskCollector getCollector(String collectorId);

    /**
     * @return List<CollectionTarget> List of all available collection targets.
     */
    List<CollectionTarget> getAllCollectionTargets();

}
