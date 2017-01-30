package com.jane.neo4j.model;

public class BiderVO {

	private String startNodeId;
	private String biderId;

	private String checkName;
	private String entrustId;

	public String getStartNodeId() {
		return startNodeId;
	}

	public void setStartNodeId(String startNodeId) {
		this.startNodeId = startNodeId;
	}

	public String getBiderId() {
		return biderId;
	}

	public void setBiderId(String biderId) {
		this.biderId = biderId;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	@Override
	public String toString() {
		return "BiderVO [startNodeId=" + startNodeId + ", biderId=" + biderId + ", checkName=" + checkName
				+ ", entrustId=" + entrustId + "]";
	}

}
