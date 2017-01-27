package com.jane.neo4j.domain;

import java.util.Date;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "OWNER")
public class Link  {


	public Link() {
	}

	@GraphId
	Long id;

	private Date stDate;
	private Date endDate;
	private String entrustStatus;
	private String biderId;

	@StartNode
	private Entrust startNode;
	@EndNode
	private Person endNode;

	public Link(Date stDate, Date endDate, String entrustStatus, String biderId, Entrust startNode, Person endNode) {
		super();
		this.stDate = stDate;
		this.endDate = endDate;
		this.entrustStatus = entrustStatus;
		this.biderId = biderId;
		this.startNode = startNode;
		this.endNode = endNode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStDate() {
		return stDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEntrustStatus() {
		return entrustStatus;
	}

	public void setEntrustStatus(String entrustStatus) {
		this.entrustStatus = entrustStatus;
	}

	public String getBiderId() {
		return biderId;
	}

	public void setBiderId(String biderId) {
		this.biderId = biderId;
	}

	public Entrust getStartNode() {
		return startNode;
	}

	public void setStartNode(Entrust startNode) {
		this.startNode = startNode;
	}

	public Person getEndNode() {
		return endNode;
	}

	public void setEndNode(Person endNode) {
		this.endNode = endNode;
	}

	@Override
	public String toString() {
		return "Link [id=" + id + ", stDate=" + stDate + ", endDate=" + endDate + ", entrustStatus=" + entrustStatus
				+ ", biderId=" + biderId + ", startNode=" + startNode + ", endNode=" + endNode + "]";
	}

}
