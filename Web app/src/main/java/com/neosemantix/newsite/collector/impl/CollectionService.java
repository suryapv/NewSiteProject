// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector.impl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosemantix.newsite.collector.CollectionManager;
import com.neosemantix.newsite.collector.BaseTimerTaskCollector;
import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author umesh
 *
 */
@Service
public class CollectionService {

    @Autowired
    private CollectionManager collectionManager;

    private Timer collectionServiceTimer;

    private static final Logger logger = LogManager.getLogger("ConsoleAndFile");

    public CollectionService() {
	collectionServiceTimer = new Timer();
	// set up the bootstrap task once the container is up - exec after 3 sec
	collectionServiceTimer.schedule(new BootstrapTask(), 3 * 1000);
	logger.debug("Scheduled BootstrapTask");
    }

    private class BootstrapTask extends TimerTask {

	@Override
	public void run() {
	    logger.info("Bootstrapping Collection Service.");
	    List<CollectionTarget> targetList = collectionManager.getAllCollectionTargets();
	    if (targetList != null) {
		for (CollectionTarget ct : targetList) {
		    BaseTimerTaskCollector c = collectionManager.getCollector(ct.getName());
		    collectionServiceTimer.schedule(c,
			    ct.getInitialDelayInMilliSeconds(),
			    ct.getFrequencyInMilliSeconds());
		    logger.debug("Scheduled " + c.getClass().getName()
			    + " with frequency: "
			    + ct.getFrequencyInMilliSeconds()
			    + " and initial delay at: "
			    + ct.getInitialDelayInMilliSeconds());
		}
	    }
	}

    }
}
