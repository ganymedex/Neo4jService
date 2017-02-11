package com.jane.neo4j.web;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jane.neo4j.domain.Entrust;
import com.jane.neo4j.domain.LinkOwner;
import com.jane.neo4j.domain.LinkRecords;
import com.jane.neo4j.domain.Person;
import com.jane.neo4j.domain.Propagator;
import com.jane.neo4j.eum.PersonTypeEnum;
import com.jane.neo4j.eum.ServiceStatusEnum;
import com.jane.neo4j.model.ReqParm;
import com.jane.neo4j.model.RespParm;
import com.jane.neo4j.services.EntrustService;
import com.jane.neo4j.services.LinkOwnerService;
import com.jane.neo4j.services.LinkRecordService;
import com.jane.neo4j.services.PersonService;
import com.jane.neo4j.services.PropagatorService;
import com.jane.neo4j.utils.JsonTools;
import com.jane.neo4j.utils.StringUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class EntrustController {
	
	@Value("${url.base}")
	private String URL;

	@Autowired
	private EntrustService entrustService;

	@Autowired
	private PersonService personService;

	@Autowired
	private LinkOwnerService linkOwnerService;
	
	@Autowired
	private PropagatorService propagatorService;
	
	@Autowired
	private LinkRecordService linkRecordService;

	@ApiOperation(value = "存储事件信息", notes = "", response = Entrust.class)
	// @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true,
	// dataType = "User")
	//@ApiImplicitParams({ @ApiImplicitParam(name = "Entrust", value = "存储事件信息", required = true, dataType = "Entrust") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Entrust.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Pet not found") })
	@RequestMapping(value = "entrust", method = RequestMethod.POST)
	public String saveEntrustInfo(@RequestBody Entrust entiry) {
		Entrust result = entrustService.save(entiry);
		return JsonTools.toJson(result);
	}

	@ApiOperation(value = "查询事件信息", notes = "", response = Entrust.class)
	// @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true,
	// dataType = "User")
	//@ApiImplicitParams({ @ApiImplicitParam(name = "entrustId", value = "事件ID", required = true, dataType = "String") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Entrust.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Pet not found") })
	@RequestMapping(value = "entrust/{entrust_id}", method = RequestMethod.GET)
	public String queryEntrustInfo(@PathVariable(value = "entrust_id") String entrust_id) {
		Entrust result = entrustService.queryOneByEntrustId(entrust_id);
		return JsonTools.toJson(result);
	}

	

	/**
	 * 保存事件 关联 任务
	 * 
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "事件创建者关联", notes = "返回传播URL")
	@RequestMapping(value = "/create-entrust", method = RequestMethod.POST)
	public String saveEntrust(@RequestBody ReqParm model) {
		RespParm resp = new RespParm();
		String jsonStr = model.getBodys();

		Map<String, Object> beanMap = JsonTools.getBeanMap(jsonStr);

		// Entrust entityEntrust = (Entrust) beanMap.get("entrust");
		// Person entityPerson = (Person) beanMap.get("person");

		String strEntrust = (String) beanMap.get("entrust");
		String strPerson = (String) beanMap.get("person");
		Entrust entityEntrust = (Entrust) JsonTools.parserStrToBean(strEntrust, Entrust.class);
		Person entityPerson = (Person) JsonTools.parserStrToBean(strPerson, Person.class);

		Entrust entrust = new Entrust();
		String userId = entityPerson.getUserId();
		String entrustId = entityEntrust.getEntrustId();
		if (null == entrustId) {
			entrustId = StringUtils.getUUID();
			entityEntrust.setApplId(userId);
			entityEntrust.setEntrustId(entrustId);
			//新创建 状态  TODO 已经支付现金
			entityEntrust.setEntrustStatus(ServiceStatusEnum.已发布);
			entrust = entrustService.save(entityEntrust);
		} else {
				//TODO 检查现金支付状态
			entrust = entrustService.queryOneByEntrustId(entrustId);
		}

		// 查询 人 信息 做更新操作
		final Person queryPersonInfo = personService.queryPersonInfo(userId);
		Set<Entrust> ownerEntrust = queryPersonInfo.getOwnerEntrust();
		if (ownerEntrust.isEmpty()) {
			Set<Entrust> ownerSet = new HashSet<Entrust>();
			ownerSet.add(entrust);
			entityPerson.setOwnerEntrust(ownerSet);
		} else {
			ownerEntrust.add(entrust);
			entityPerson.setOwnerEntrust(ownerEntrust);
		}
		entityPerson.setEntrustId(entrustId);
		// 保存人和 任务
		Person person = personService.savePerson(entityPerson);

		// 生成传播 人信息
		Propagator propagator = new Propagator();
		propagator.setEntrustId(entrustId);
		propagator.setName(person.getName());
		propagator.setPhoneNumber(person.getPhoneNumber());
		propagator.setParentId("ROOT");
		propagator.setUserId(userId);
		propagator.setPropagatorId(StringUtils.getUUID());
		propagator.setType(PersonTypeEnum.创建者);
		propagatorService.savePropagator(propagator);

		// TODO ServiceStatusEnum status = ServiceStatusEnum.已发布;
		LinkOwner link = new LinkOwner(ServiceStatusEnum.已发布, entrust, person);
		linkOwnerService.saveLinkOwner(link);

		String url = URL+ entrust.getApplId() + "/" + entrust.getEntrustId();
		
		LinkRecords entity = new LinkRecords();
		entity.setCreateNodeId(entrust.getApplId());
		entity.setUrl(url);
		entity.setEntrustId(entrustId);
		entity.setRecordId(StringUtils.getUUID());
		linkRecordService.saveLinkRecords(entity);
		
		Map<String, Object> data = new HashedMap<String, Object>();
		data.put("entrust", entrust);
		data.put("url", url);
		resp.setBodys(JsonTools.toJson(data));
		return JsonTools.toJson(resp);
	}

//	@RequestMapping(value = "/query-entrusts", method = RequestMethod.GET)
//	public String queryEntrustList(@RequestBody ReqParm model) {
//		// RespParm resp = new RespParm();
//		// String jsonStr = model.getBodys();
//		// Map<String, Object> beanMap = JsonTools.getBeanMap(jsonStr);
//		//
//		// //TODO 支持查询方式 多样 applId
//		// String applId = (String) beanMap.get("applId");
//		// //String entrustId = (String) beanMap.get("entrustId");
//		//
//		// List<Entrust> listEntrust = entrustService.queryListBy(applId);
//		// resp.setBodys(JsonTools.toJson(listEntrust));
//		// return JsonTools.toJson(resp);
//		return "";
//	}

	// @RequestMapping(value="/test")
	// public String Con(){
	//
	// String applID="12231231212";
	//// Entrust entity = new Entrust();
	//// entity.setApplId(applID);
	//// entity.setEntrustTitle("买鲜花");
	//// entity.setEntrustId(StringUtils.getUUID());
	////
	////
	//// Entrust bean = entrustService.save(entity);
	//// System.out.println(bean);
	// List<Entrust> queryEntrustByName = entrustService.queryListBy(applID);
	// for (int i = 0; i < queryEntrustByName.size(); i++) {
	// Entrust entrust = queryEntrustByName.get(i);
	// System.out.println("lllllll"+entrust);
	// }
	// Collection<Entrust> queryListByTitle =
	// entrustService.queryListByTitle("买鲜花");
	// queryListByTitle.forEach(p -> System.out.println("ccccccc"+p));
	// return "ss";
	// }

}
