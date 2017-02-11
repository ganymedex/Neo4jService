package com.jane.neo4j.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jane.neo4j.domain.Entrust;
import com.jane.neo4j.domain.Person;
import com.jane.neo4j.services.EntrustService;
import com.jane.neo4j.services.PersonService;
import com.jane.neo4j.utils.JsonTools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserController {

	@Autowired
	private PersonService userService;

	@Autowired
	private EntrustService entrustService;

	
	@ApiOperation(value="存储用户信息", notes="非传播者",response=Entrust.class)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "Person", value = "用户基本信息", required = true, dataType = "Person" )
	})
	@ApiResponses(value = { 
			  @ApiResponse(code = 200, message = "Success",response=Person.class),
			  @ApiResponse(code = 400, message = "Invalid ID supplied"),
		      @ApiResponse(code = 404, message = "Pet not found") })
	@RequestMapping(value="person",method=RequestMethod.POST)
	public String saveUserInfo(@RequestBody Person p){
		Person result = userService.savePerson(p);
		return JsonTools.toJson(result);
	}
	
	@ApiOperation(value="查询用户信息", notes="非传播者",response=Entrust.class)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String" )
	})
	@ApiResponses(value = { 
			  @ApiResponse(code = 200, message = "Success",response=Person.class),
			  @ApiResponse(code = 400, message = "Invalid ID supplied"),
		      @ApiResponse(code = 404, message = "Pet not found") })
	@RequestMapping(value="person/{user_id}",method=RequestMethod.GET)
	public String queryUserInfoByUserId(@PathVariable(value="user_id") String  userId){
		Person result = userService.queryPersonInfo(userId);
		return JsonTools.toJson(result);
	}
	
	
	@ApiOperation(value = "查询该用户事件信息集合", notes = "返回集合信息", response = Entrust.class)
	// @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true,
	// dataType = "User")
	@ApiImplicitParams({ @ApiImplicitParam(name = "user_id", value = "创建人ID", required = true, dataType = "String") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Entrust.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Pet not found") })
	@RequestMapping(value = "/user/entrusts/{user_id}", method = RequestMethod.GET)
	public String queryEntrustInfoList(@PathVariable(value = "user_id") @RequestParam String userId) {
		List<Entrust> result = entrustService.queryListByUserId(userId);
		return JsonTools.toJson(result);
	}
	
	
	
	
	
//	/**
//	 * 保存传播路径
//	 * 
//	 * @param req
//	 * @return
//	 */
//	@RequestMapping("/create-propagator")
//	public String personPropagator(@RequestBody ReqParm req) {
//
//		String jsonStr = req.getBodys();
//		Map<String, Object> beanMap = JsonTools.getBeanMap(jsonStr);
//
//		
//		String strStarNode = (String) beanMap.get("startNode");
//		String strEndNode = (String) beanMap.get("endNode");
//		Person startNode = (Person) JsonTools.parserStrToBean(strStarNode, Person.class);
//		Person endNode = (Person) JsonTools.parserStrToBean(strEndNode, Person.class);
//		
//		
////		Person querystartNode = userService.queryPersonInfo(startNode.getUserId());
////		List<Person> propagator = querystartNode.getPropagator();
////		Person queryendNode = userService.queryPersonInfo(endNode.getUserId());
////		if(propagator.isEmpty()){
////			List<Person> propagatorList = new ArrayList<Person>();
////			propagatorList.add(queryendNode);
////			querystartNode.setPropagator(propagatorList);
////		}else{
////			propagator.add(queryendNode);
////			querystartNode.setPropagator(propagator);
////		}
////		userService.savePerson(querystartNode);
//		
////		System.err.println(querystartNode+"==="+queryendNode);
////		LinkPropagator link = new LinkPropagator(querystartNode, queryendNode);
////		LinkPropagator linkPropagator = linkPropagatorService.saveLinkPropagator(link);
////		System.err.println(linkPropagator);
//		
//		return "SUC";
//	}
	
	
	

//	@RequestMapping("/save-person-test")
//	public String save() {
//
//		List<Person> listdata = new ArrayList<Person>();
//		for (int i = 0; i < 30; i++) {
//			Person person = Manufacture.Person();
//			listdata.add(person);
//		}
//		List<Person> list = userService.savePersonList(listdata);
//
//		for (int i = 1; i <= 7; i++) {
//			// 获取随机数 取朋友
//			Person person = Manufacture.Person();
//			list.add(person);
//			List<Person> friends = new ArrayList<Person>();
//			int[] randomArray = RandomUtils.randomCommon(0, 10 + i, 3 + i);
//			for (int j = 0; j < randomArray.length; j++) {
//				Person f = list.get(randomArray[j]);
//				friends.add(f);
//			}
//			System.out.println("person:" + person);
//			System.out.println("friends:" + friends);
//			person.setFriends(friends);
//			userService.savePerson(person);
//		}
//
//		return "SUC";
//	}

	// public static void main(String[] args) {
	// List<Person> list = new ArrayList<Person>();
	// for(int i=0;i<30;i++){
	// Person person = Manufacture.Person();
	// list.add(person);
	// }
	// //userService.savePerson(person);
	//
	// for (int i = 1; i <= 7; i++) {
	// //获取随机数 取朋友
	// Person person = Manufacture.Person();
	// list.add(person);
	// List<Person> friends = new ArrayList<Person>();
	// int[] randomArray = RandomUtils.randomCommon(0, 10+i, 3+i);
	// for (int j = 0; j < randomArray.length; j++) {
	// Person f = list.get(randomArray[j]);
	// friends.add(f);
	// }
	// System.out.println("person:"+person);
	// System.out.println("friends:"+friends);
	// person.setFriends(friends);
	// //userService.savePerson(person);
	// }
	//
	//
	// System.out.println("=======================");
	// //list.forEach(p->{int m=0;m++;System.out.println(m+">>person:"+p);});
	// System.out.println("=======================");
	// }

}
