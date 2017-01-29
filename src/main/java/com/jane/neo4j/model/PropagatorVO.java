package com.jane.neo4j.model;

public class PropagatorVO {
	
	private String startNodeId;
	private String endNodeId;
	private String nameChecked;
	
	private String entrustId;

	public String getStartNodeId() {
		return startNodeId;
	}

	public void setStartNodeId(String startNodeId) {
		this.startNodeId = startNodeId;
	}

	public String getEndNodeId() {
		return endNodeId;
	}

	public void setEndNodeId(String endNodeId) {
		this.endNodeId = endNodeId;
	}

	public String getNameChecked() {
		return nameChecked;
	}

	public void setNameChecked(String nameChecked) {
		this.nameChecked = nameChecked;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	@Override
	public String toString() {
		return "PropagatorVO [startNodeId=" + startNodeId + ", endNodeId=" + endNodeId + ", nameChecked=" + nameChecked
				+ ", entrustId=" + entrustId + "]";
	}
	
	

}
