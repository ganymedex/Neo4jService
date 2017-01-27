package com.jane.neo4j.domain;

import java.io.Serializable;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator = JSOGGenerator.class)
@RelationshipEntity(type = "PROPAGATOR")
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

}
