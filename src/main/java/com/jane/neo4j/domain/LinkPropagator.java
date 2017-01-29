package com.jane.neo4j.domain;

import java.io.Serializable;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "PROPAGATOR_LINK")
public class LinkPropagator implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LinkPropagator(){}
	
	@GraphId
	Long id; 
	
	@StartNode
	private Person startNode;
	@EndNode
	private Person endNode;

	public LinkPropagator(Person startNode, Person endNode) {
		super();
		this.startNode = startNode;
		this.endNode = endNode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getStartNode() {
		return startNode;
	}

	public void setStartNode(Person startNode) {
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
		return "LinkPropagator [id=" + id + ", startNode=" + startNode + ", endNode=" + endNode + "]";
	}
	
	

}
