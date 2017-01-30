package com.jane.neo4j.model;

/**
 * 消费事件
 * @author ganymedex
 *
 */
public class ConsumerVO {
	
	
	
	private String entrusId;
	private String bidCode;
	private String biderId;
	public String getEntrusId() {
		return entrusId;
	}
	public void setEntrusId(String entrusId) {
		this.entrusId = entrusId;
	}
	public String getBidCode() {
		return bidCode;
	}
	public void setBidCode(String bidCode) {
		this.bidCode = bidCode;
	}
	public String getBiderId() {
		return biderId;
	}
	public void setBiderId(String biderId) {
		this.biderId = biderId;
	}
	@Override
	public String toString() {
		return "ConsumerVO [entrusId=" + entrusId + ", bidCode=" + bidCode + ", biderId=" + biderId + "]";
	}
	
	

}
