package com.jane.neo4j.domain;

import java.io.Serializable;
import java.util.List;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity(label="PERSON")
public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//id user_phone_number user_name entrust_id user_p_node_id
	@GraphId
	Long id;
	
	@Property(name="name")
	private String name;
	private String userId;
	@Property(name="phoneNumber")
	private String phoneNumber;
	private String entrustId;
	
	//关系直接定义在节点中 假设朋友关系 预留计算二度人脉 很重要
    @Relationship(type = "IS_FRIEND_OF", direction=Relationship.OUTGOING)
    private List<Person> friends;
    
   //关系直接定义在节点中 传播者链路
    @Relationship(type = "PROPAGATOR", direction=Relationship.OUTGOING)
    private List<Person> propagator;
	
    //使用外部定义的关系 拥有者
    @Relationship(type = "OWNER")
    private Entrust ownerEntrust;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}

	public List<Person> getPropagator() {
		return propagator;
	}

	public void setPropagator(List<Person> propagator) {
		this.propagator = propagator;
	}

	public Entrust getOwnerEntrust() {
		return ownerEntrust;
	}

	public void setOwnerEntrust(Entrust ownerEntrust) {
		this.ownerEntrust = ownerEntrust;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", userId=" + userId + ", phoneNumber=" + phoneNumber
				+ ", entrustId=" + entrustId + ", friends=" + friends + ", propagator=" + propagator + ", ownerEntrust="
				+ ownerEntrust + "]";
	}

	

	
    
    
    
    

}
