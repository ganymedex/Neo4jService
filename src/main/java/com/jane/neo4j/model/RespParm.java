package com.jane.neo4j.model;

public class RespParm {
  
	private int code=200;
	private String msg;
	private String Bodys;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getBodys() {
		return Bodys;
	}
	public void setBodys(String bodys) {
		Bodys = bodys;
	}
	@Override
	public String toString() {
		return "RespParm [code=" + code + ", msg=" + msg + ", Bodys=" + Bodys + "]";
	}
	
}
