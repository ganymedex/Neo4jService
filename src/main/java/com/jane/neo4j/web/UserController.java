package com.jane.neo4j.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jane.neo4j.domain.Person;
import com.jane.neo4j.model.ReqParm;
import com.jane.neo4j.model.RespParm;
import com.jane.neo4j.services.PersonService;
import com.jane.neo4j.utils.replase.Manufacture;
import com.jane.neo4j.utils.replase.RandomUtils;

@RestController
public class UserController {
	
	
	@Autowired
	private PersonService userService;
	
	
	
	/**
	 * 保存传播路径
	 * @param req
	 * @return
	 */
	public String personPropagator(@RequestBody ReqParm req){
		RespParm resp = new RespParm();
		
		req.getBodys();
		
		
		return "";
	}
	
	
	
	
	
	
	@RequestMapping("/save-person-test")
	public String save(){
		
		
		List<Person> listdata = new ArrayList<Person>();
		for(int i=0;i<30;i++){
			Person person = Manufacture.Person();
			listdata.add(person);
		}
		List<Person> list = userService.savePersonList(listdata);
	
		for (int i = 1; i <= 7; i++) {
			//获取随机数 取朋友
			Person person = Manufacture.Person();
			list.add(person);
			List<Person> friends = new ArrayList<Person>();
			int[] randomArray = RandomUtils.randomCommon(0, 10+i, 3+i);
			for (int j = 0; j < randomArray.length; j++) {
				Person f = list.get(randomArray[j]);
				friends.add(f);
			}
			System.out.println("person:"+person);
			System.out.println("friends:"+friends);
			person.setFriends(friends);
			userService.savePerson(person);
		}
		
		return "SUC";
	}
	
	
	
	
	
	
	
	
//	public static void main(String[] args) {
//		List<Person> list = new ArrayList<Person>();
//		for(int i=0;i<30;i++){
//			Person person = Manufacture.Person();
//			list.add(person);
//		}
//		//userService.savePerson(person);
//	
//		for (int i = 1; i <= 7; i++) {
//			//获取随机数 取朋友
//			Person person = Manufacture.Person();
//			list.add(person);
//			List<Person> friends = new ArrayList<Person>();
//			int[] randomArray = RandomUtils.randomCommon(0, 10+i, 3+i);
//			for (int j = 0; j < randomArray.length; j++) {
//				Person f = list.get(randomArray[j]);
//				friends.add(f);
//			}
//			System.out.println("person:"+person);
//			System.out.println("friends:"+friends);
//			person.setFriends(friends);
//			//userService.savePerson(person);
//		}
//		
//		
//		System.out.println("=======================");
//		//list.forEach(p->{int m=0;m++;System.out.println(m+">>person:"+p);});
//		System.out.println("=======================");
//	}

}
