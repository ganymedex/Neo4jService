package com.jane.neo4j.web;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.jane.neo4j.domain.Entrust;
import com.jane.neo4j.domain.Person;
import com.jane.neo4j.model.ReqParm;
import com.jane.neo4j.utils.JsonTools;

public class TestMain {

	
public static void main(String[] args) {
	createPtoP();
		
		
		
	}
  

public static void createPtoP(){
	
//	{"phoneNumber":"13705518743","│
//		│name":"杨妹凝","userId":"86c7c956│
//		│d3884d0692e4446372e37ae2"}
	
//	{"phoneNumber":"13406602513","│
//		│name":"相若","userId":"302c57cf4│
//		│26f48bea7d5e974daa5dea8"} 
	Person  p =  new Person();
	p.setName("相若");
	p.setPhoneNumber("13406602513");
	p.setUserId("302c57cf426f48bea7d5e974daa5dea8");
	
	Person  s =  new Person();
	s.setName("卫华枫");
	s.setPhoneNumber("13104269091");
	s.setUserId("d507e06a929f42f6b116ff0620ac68f2");
	
	Map<String,Object> data=new HashedMap<String,Object>();
	data.put("startNode",JsonTools.toJson(s));
	data.put("endNode", JsonTools.toJson(p));
	
	ReqParm req = new ReqParm();
	req.setBodys(JsonTools.toJson(data));
	System.out.println(JsonTools.toJson(req).toString());
	
}


   public void creatEntrust(){
	   Entrust ee = new Entrust();
		ee.setEntrustTitle("咋了，兄弟");
		ee.setEntrustContent("【类型】车找人 【事件】巴拉巴拉");
		ee.setEntrustAmount("200元");
		
		Person p = new Person();
		p.setName("蓝启");
		p.setPhoneNumber("15603780882");
		p.setUserId("f63c4071bce04b4697cf5ba81f66866b");
		Map<String,Object> data=new HashedMap<String,Object>();
		data.put("entrust",JsonTools.toJson(ee));
		data.put("person", JsonTools.toJson(p));
		ReqParm req = new ReqParm();
		req.setBodys(JsonTools.toJson(data));
		System.out.println(JsonTools.toJson(req).toString());
		
		String bodys = req.getBodys();
		Map<String, Object> beanMap = JsonTools.getBeanMap(bodys);
		String jsonStr = (String) beanMap.get("entrust");
		
		Entrust parserStrToBean = (Entrust) JsonTools.parserStrToBean(jsonStr, Entrust.class);
		System.out.println(parserStrToBean);
   }
}
