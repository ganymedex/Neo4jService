package com.jane.neo4j.domain;

import java.io.Serializable;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.jane.neo4j.eum.BidStatusEnum;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity(label = "BIDER")
public class Bider implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GraphId 
	Long id;
	
	private String biderCode;
	private String biderUserId; //中标 揭榜人id
	private String entrustId;
	
	private String biderName;
	private String phoneNumber;
	private BidStatusEnum bidStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBiderCode() {
		return biderCode;
	}
	public void setBiderCode(String biderCode) {
		biderCode = biderCode;
	}
	public String getBiderUserId() {
		return biderUserId;
	}
	public void setBiderUserId(String biderUserId) {
		this.biderUserId = biderUserId;
	}
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	public String getBiderName() {
		return biderName;
	}
	public void setBiderName(String biderName) {
		this.biderName = biderName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public BidStatusEnum getBidStatus() {
		return bidStatus;
	}
	public void setBidStatus(BidStatusEnum bidStatus) {
		this.bidStatus = bidStatus;
	}
	@Override
	public String toString() {
		return "Bider [id=" + id + ", biderCode=" + biderCode + ", biderUserId=" + biderUserId + ", entrustId="
				+ entrustId + ", biderName=" + biderName + ", phoneNumber=" + phoneNumber + ", bidStatus=" + bidStatus
				+ "]";
	}
	
	
	
	
	
	

}
