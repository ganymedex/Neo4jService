package com.jane.neo4j.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jane.neo4j.domain.Entrust;
import com.jane.neo4j.domain.Person;
import com.jane.neo4j.model.ReqParm;
import com.jane.neo4j.model.RespParm;
import com.jane.neo4j.services.EntrustService;
import com.jane.neo4j.services.PersonService;
import com.jane.neo4j.utils.JsonTools;
import com.jane.neo4j.utils.StringUtils;

@RestController
public class EntrustController {
 
	
	@Autowired 
	private EntrustService entrustService;
	
	@Autowired
	private PersonService personService;
	 
	
	
	
	/**
	 * 保存事件  关联 任务
	 * @param model
	 * @return
	 */
	@RequestMapping("/create" )
	public String saveEntrust(@RequestBody ReqParm model){
		RespParm  resp = new RespParm();
		String jsonStr = model.getBodys();
		
		Map<String, Object> beanMap = JsonTools.getBeanMap(jsonStr);
		String strEntrust = (String) beanMap.get("entrust");
		String strPerson=(String)beanMap.get("person");
		Entrust entityEntrust = (Entrust) JsonTools.parserStrToBean(strEntrust, Entrust.class);
		Person entityPerson = (Person) JsonTools.parserStrToBean(strPerson, Person.class);
		
		String entrustId = StringUtils.getUUID();
		String userId = entityPerson.getUserId();
		
		entityEntrust.setApplId(userId);
		entityEntrust.setEntrustId(entrustId);
		Entrust entrust = entrustService.save(entityEntrust);
		
		entityPerson.setOwnerEntrust(entityEntrust);
		entityPerson.setEntrustId(entrustId);
		//保存人和 任务
		Person person = personService.savePerson(entityPerson);
		
		String url ="http://www.jane.com/"+entrust.getApplId()+"/"+entrust.getEntrustId();
		Map<String,Object> data=new HashedMap<String,Object>();
		data.put("entrust", entrust);
		data.put("url", url);
		resp.setBodys(JsonTools.toJson(data));
		return JsonTools.toJson(resp);
	}
	
	@RequestMapping("/queryEntrusts")
	public String queryEntrustList(@RequestBody ReqParm model){
		RespParm  resp = new RespParm();
		String jsonStr = model.getBodys();
		Map<String, Object> beanMap = JsonTools.getBeanMap(jsonStr);
		
		//TODO 支持查询方式  多样 applId
		String applId = (String) beanMap.get("applId");
		//String entrustId = (String) beanMap.get("entrustId");
		
		List<Entrust> listEntrust = entrustService.queryListBy(applId);
		resp.setBodys(JsonTools.toJson(listEntrust));
		return JsonTools.toJson(resp); 
	}
	
	
	
	
	
	
	
	
//	@RequestMapping(value="/test")
//	public String Con(){
//		
//		String applID="12231231212";
////		Entrust entity = new Entrust();
////		entity.setApplId(applID);
////		entity.setEntrustTitle("买鲜花");
////		entity.setEntrustId(StringUtils.getUUID());
////		
////		
////		Entrust bean = entrustService.save(entity);
////		System.out.println(bean);
//		List<Entrust> queryEntrustByName = entrustService.queryListBy(applID);
//		for (int i = 0; i < queryEntrustByName.size(); i++) {
//			Entrust entrust = queryEntrustByName.get(i);
//			System.out.println("lllllll"+entrust);
//		}
//		Collection<Entrust> queryListByTitle = entrustService.queryListByTitle("买鲜花");
//		queryListByTitle.forEach(p -> System.out.println("ccccccc"+p));
//		return "ss";
//	}
	
	
}
