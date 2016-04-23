// Copyright 2014; All rights reserved with NeoSemantix, Inc.
package com.neosemantix.newsite.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author umesh
 *
 */
@Entity
@Table(name = "COLLECTION")
public class CollectionTarget {

    // TODO - Technically the collection target and
    // the last collection run should be considered as two separate entities.

    /**
     * DB generated article number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Short id;

    /**
     * Unique assigned name.
     */
    @Column(name = "NAME", nullable = false, length = 128, unique = true)
    private String name;

    /**
     * Collector class to be used.
     */
    @Column(name = "COLLECTOR_CLASS", nullable = false, length = 1024)
    private String collectorClass;

    /**
     * The id assigned by site hosting company. For example, Umesh's blog is
     * host on Google Blogger and it has some 9xxxxx id.
     */
    @Column(name = "SITE_ID", nullable = false, length = 128, unique = true)
    private String siteOwnerId;

    /**
     * The site access API calls need some key.
     */
    @Column(name = "API_KEY", nullable = false, length = 256)
    private String apiKey;

    /**
     * After how many milliseconds we want to undertake the first collection
     * upon bootstrap.
     */
    @Column(name = "INIT_DELAY_MS", nullable = false)
    private int initialDelayInMilliSeconds;

    /**
     * Frequency in milliseconds
     */
    @Column(name = "FREQUNCEY_MS", nullable = false)
    private int frequencyInMilliSeconds;

    /**
     * When did it ran last successfully.
     */
    @Column(name = "LAST_RAN", nullable = true)
    private Date lastRan;

    public CollectionTarget() {

    }

    public CollectionTarget(short id, long lr) {
	this.id = id;
	this.lastRan = new Date(lr);
    }

    /**
     * @return the id
     */
    public Short getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Short id) {
	this.id = id;
    }

    /**
     * @return the lastRan
     */
    public Date getLastRan() {
	return lastRan;
    }

    /**
     * @param lastRan
     *            the lastRan to set
     */
    public void setLastRan(Date lastRan) {
	this.lastRan = lastRan;
    }

    /**
     * @return the siteOwnerId
     */
    public String getSiteOwnerId() {
	return siteOwnerId;
    }

    /**
     * @param siteOwnerId
     *            the siteOwnerId to set
     */
    public void setSiteOwnerId(String siteOwnerId) {
	this.siteOwnerId = siteOwnerId;
    }

    /**
     * @return the apiKey
     */
    public String getApiKey() {
	return apiKey;
    }

    /**
     * @param apiKey
     *            the apiKey to set
     */
    public void setApiKey(String apiKey) {
	this.apiKey = apiKey;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the collectorClass
     */
    public String getCollectorClass() {
	return collectorClass;
    }

    /**
     * @param collectorClass
     *            the collectorClass to set
     */
    public void setCollectorClass(String collectorClass) {
	this.collectorClass = collectorClass;
    }

    /**
     * @return the initialDelayInMilliSeconds
     */
    public int getInitialDelayInMilliSeconds() {
	return initialDelayInMilliSeconds;
    }

    /**
     * @param initialDelayInMilliSeconds
     *            the initialDelayInMilliSeconds to set
     */
    public void setInitialDelayInMilliSeconds(int initialDelayInMilliSeconds) {
	this.initialDelayInMilliSeconds = initialDelayInMilliSeconds;
    }

    /**
     * @return the frequencyInMilliSeconds
     */
    public int getFrequencyInMilliSeconds() {
	return frequencyInMilliSeconds;
    }

    /**
     * @param frequencyInMilliSeconds
     *            the frequencyInMilliSeconds to set
     */
    public void setFrequencyInMilliSeconds(int frequencyInMilliSeconds) {
	this.frequencyInMilliSeconds = frequencyInMilliSeconds;
    }
}
