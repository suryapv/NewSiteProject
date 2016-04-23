// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.dao;

import java.util.List;

import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author umesh
 *
 */
public interface CollectionTargetDao {

    /**
     * @return List<CollectionTarget> Returns list of all targets to be collected.
     */
    List<CollectionTarget> getAllCollectionTargets();

    /**
     * @param c
     *            Update the given target.
     */
    void update(CollectionTarget c);
}
