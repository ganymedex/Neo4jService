package com.jane.neo4j.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

/**
 * 链接记录
 * @author ganymedex
 *
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "LINKRECORDS")
public class LinkRecords {

	@GraphId
	Long id;
	
	@Property
	private String recordId;
	private String entrustId;
	private String createNodeId;
	private String url;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getCreateNodeId() {
		return createNodeId;
	}

	public void setCreateNodeId(String createNodeId) {
		this.createNodeId = createNodeId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "LinkRecords [id=" + id + ", recordId=" + recordId + ", entrustId=" + entrustId + ", createNodeId="
				+ createNodeId + ", url=" + url + "]";
	}
	
	
	
	
}
