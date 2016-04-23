// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector;

import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author upatil
 *
 */
public interface CollectorFactory {
    
    BaseTimerTaskCollector getInstance(CollectionTarget ct);

}
