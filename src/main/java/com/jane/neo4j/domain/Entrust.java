package com.jane.neo4j.domain;

import java.util.Date;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@NodeEntity(label = "ENTRUST")
public class Entrust {
//	entrust_id appl_id(发起人ID) entrust_title entrust_content entrust_amount st_date end_date entrust_status(赏金收割方式，主动停止，赏金终止，到期终止) bider_id（最终中标人id）
 
	/**
	 * 
	 */
	@GraphId 
	Long id;
	private String entrustId;
	private String applId;
	@Property(name="entrustTitle")
	private String entrustTitle;
	private String entrustContent;
	private String entrustAmount;
	private Date stDate;
	private Date endDate;
	private String entrustStatus;
	private String biderId;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	public String getApplId() {
		return applId;
	}
	public void setApplId(String applId) {
		this.applId = applId;
	}
	public String getEntrustTitle() {
		return entrustTitle;
	}
	public void setEntrustTitle(String entrustTitle) {
		this.entrustTitle = entrustTitle;
	}
	public String getEntrustContent() {
		return entrustContent;
	}
	public void setEntrustContent(String entrustContent) {
		this.entrustContent = entrustContent;
	}
	public String getEntrustAmount() {
		return entrustAmount;
	}
	public void setEntrustAmount(String entrustAmount) {
		this.entrustAmount = entrustAmount;
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
	@Override
	public String toString() {
		return "Entrust [id=" + id + ", entrustId=" + entrustId + ", applId=" + applId + ", entrustTitle="
				+ entrustTitle + ", entrustContent=" + entrustContent + ", entrustAmount=" + entrustAmount + ", stDate="
				+ stDate + ", endDate=" + endDate + ", entrustStatus=" + entrustStatus + ", biderId=" + biderId + "]";
	}
	
	
	
	
}
