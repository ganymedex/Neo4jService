package com.jane.neo4j.model;

public class ReqParm {
	private String token;
	private String bodys;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getBodys() {
		return bodys;
	}
	public void setBodys(String bodys) {
		this.bodys = bodys;
	}
	@Override
	public String toString() {
		return "ReqParm [token=" + token + ", bodys=" + bodys + "]";
	}
	
	

}
