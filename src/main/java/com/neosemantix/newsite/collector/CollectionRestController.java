// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.collector;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neosemantix.newsite.model.CollectionTarget;

/**
 * @author umesh
 *
 */
@RestController
public class CollectionRestController {

    @Autowired
    private CollectionManager collectionManager;

    @RequestMapping(value = "/collection", method = RequestMethod.GET)
    public List<CollectionTarget> getCollectionInfo() {
	return collectionManager.getAllCollectionTargets();
    }

    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    public void undertakeOneOfCollection(
	    @RequestParam(value = "name") String collectorId) {
	BaseTimerTaskCollector c = collectionManager.getCollector(collectorId);
	// run in this thread only for now
	try {
	    c.run();
	} catch (Throwable t) {
	    // TODO: Throw web exception
	    t.printStackTrace();
	}
    }
}
