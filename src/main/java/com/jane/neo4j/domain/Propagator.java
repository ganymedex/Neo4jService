package com.jane.neo4j.domain;

import java.io.Serializable;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.jane.neo4j.eum.PersonTypeEnum;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "PROPAGATORER")
public class Propagator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GraphId
	Long id;

	private String propagatorId;
	@Property(name = "name")
	private String name;
	private String userId;
	@Property(name = "phoneNumber")
	private String phoneNumber;
	private String entrustId;
	private String parentId;

	private PersonTypeEnum type;
	// 关系直接定义在节点中 传播者链路
	@Relationship(type = "PROPAGATOR_SON", direction = Relationship.OUTGOING)
	private List<Propagator> propagator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPropagatorId() {
		return propagatorId;
	}

	public void setPropagatorId(String propagatorId) {
		this.propagatorId = propagatorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<Propagator> getPropagator() {
		return propagator;
	}

	public void setPropagator(List<Propagator> propagator) {
		this.propagator = propagator;
	}

	public PersonTypeEnum getType() {
		return type;
	}

	public void setType(PersonTypeEnum type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Propagator [id=" + id + ", propagatorId=" + propagatorId + ", name=" + name + ", userId=" + userId
				+ ", phoneNumber=" + phoneNumber + ", entrustId=" + entrustId + ", parentId=" + parentId + ", type="
				+ type + ", propagator=" + propagator + "]";
	}

}
