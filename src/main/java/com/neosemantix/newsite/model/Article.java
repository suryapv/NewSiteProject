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
@Table(name = "ARTICLE")
public class Article {
    
    private static String COMMENTARY_TITLE_PREFIX = "Commentary:";

    /**
     * DB generated article number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;

    /**
     * Id assigned by the source site. Expecting it to be unique.
     */
    @Column(name = "SITE_ID", nullable = true, length = 128, unique = true)
    private String siteId;

    /**
     * Article title.
     */
    @Column(name = "TITLE", nullable = false, length = 128)
    private String title;

    /**
     * Comma separated list of author names.
     */
    @Column(name = "AUTHORS", nullable = false, length = 256)
    private String authors;

    /**
     * Date time when the article was updated last.
     */
    @Column(name = "LAST_UPDATE", nullable = false)
    private Date lastUpdate;

    /**
     * Article URL, whether it is owned article or just referenced URL.
     */
    @Column(name = "URL", nullable = false, length = 4096)
    private String url;

    /**
     * Article type. We define following types for now:
     * 0:	-	original article, owned by 21 century politics blog
     * 1:	-	commentary, owned by 21 century politics blog
     */
    @Column(name = "TYPE", nullable = false)
    private Short type = 0;				// default value				

    /**
     * Article summary, optional.
     */
    @Column(name = "SUMMARY", length = 8192)
    private String summary;

    /**
     * Comma separated list of tags assigned to the article. Can be null when
     * there are no tags.
     */
    @Column(name = "TAGS", length = 1024)
    private String tags;

    /**
     * This is the information about where is the article to be displayed.
     */
    @Column(name = "DISP_LOC", nullable = true)
    private Short displayLocation = 0;			// default value 
    
    /**
     * Non-persistent content - HTML with utf-8 encoding
     */
    private String content;
    
    /**
     * Name of the collection target from where this article is harvested. 
     * Note that it is non-persistent.
     */
    private String collectionTarget;

    public Article() {
	// default constructor
    }

    public Article(String title, String authors, String url,
	    boolean referenced, String summary, String tags) {
	this.title = title;
	this.authors = authors;
	this.url = url;
	if (referenced) {
	    this.type = 1;
	}
	this.summary = summary;
	this.tags = tags;
	this.lastUpdate = new Date(); // current date time stamp
    }

    /**
     * @return the id
     */
    public int getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
	this.id = id;
    }

    /**
     * @return the siteId
     */
    public String getSiteId() {
	return siteId;
    }

    /**
     * @param siteId
     *            the siteId to set
     */
    public void setSiteId(String siteId) {
	this.siteId = siteId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
	if (title != null && title.startsWith(COMMENTARY_TITLE_PREFIX)){
	    this.type = 1;
	}
    }

    /**
     * @return the authors
     */
    public String getAuthors() {
	return authors;
    }

    /**
     * @param authors
     *            the authors to set
     */
    public void setAuthors(String authors) {
	this.authors = authors;
    }

    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate() {
	return lastUpdate;
    }

    /**
     * @param lastUpdate
     *            the lastUpdate to set
     */
    public void setLastUpdate(Date lastUpdate) {
	this.lastUpdate = lastUpdate;
    }

    /**
     * @return the tags
     */
    public String getTags() {
	return tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(String tags) {
	this.tags = tags;
    }

    /**
     * @return the url
     */
    public String getUrl() {
	return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
	this.url = url;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
	return summary;
    }

    /**
     * @param summary
     *            the summary to set
     */
    public void setSummary(String summary) {
	this.summary = summary;
    }

    /**
     * @return the type
     */
    public short getType() {
	return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(short type) {
	this.type = type;
    }

    /**
     * @return the displayLocation
     */
    public short getDisplayLocation() {
	return displayLocation;
    }

    /**
     * @param displayLocation
     *            the displayLocation to set
     */
    public void setDisplayLocation(short displayLocation) {
	this.displayLocation = displayLocation;
    }
    
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the collectionTarget
     */
    public String getCollectionTarget() {
        return collectionTarget;
    }

    /**
     * @param collectionTarget the collectionTarget to set
     */
    public void setCollectionTarget(String collectionTarget) {
        this.collectionTarget = collectionTarget;
    }

    /**
     * Copies incoming argument properties except id and site id.
     * @param a
     */
    public void copy(Article a){
	if (a != null){
	    this.title = a.title;
	    this.authors = a.authors;
	    this.displayLocation = a.displayLocation;
	    this.lastUpdate = a.lastUpdate;
	    this.summary = a.summary;
	    this.tags = a.tags;
	    this.title = a.title;
	    this.type = a.type;
	    this.url = a.url;
	    this.content = a.content;
	    this.collectionTarget = a.collectionTarget;
	}
    }
}
