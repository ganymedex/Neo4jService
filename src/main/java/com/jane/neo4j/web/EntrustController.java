package com.jane.neo4j.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jane.neo4j.domain.Entrust;
import com.jane.neo4j.services.EntrustServiceImpl;

@RestController
public class EntrustController {
 
	
	@Autowired EntrustServiceImpl entrustService;
	 
	
	@RequestMapping(value="/entrust")
	public String Con(){
		
		String applID="12231231212";
//		Entrust entity = new Entrust();
//		entity.setApplId(applID);
//		entity.setEntrustTitle("买鲜花");
//		entity.setEntrustId(StringUtils.getUUID());
//		
//		
//		Entrust bean = entrustService.save(entity);
//		System.out.println(bean);
		List<Entrust> queryEntrustByName = entrustService.queryListBy(applID);
		for (int i = 0; i < queryEntrustByName.size(); i++) {
			Entrust entrust = queryEntrustByName.get(i);
			System.out.println("lllllll"+entrust);
		}
		Collection<Entrust> queryListByTitle = entrustService.queryListByTitle("买鲜花");
		queryListByTitle.forEach(p -> System.out.println("ccccccc"+p));
		return "ss";
	}
	
	
}
