package com.jane.neo4j.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jane.neo4j.domain.Person;
import com.jane.neo4j.domain.Propagator;
import com.jane.neo4j.eum.PersonTypeEnum;
import com.jane.neo4j.model.PropagatorVO;
import com.jane.neo4j.model.ReqParm;
import com.jane.neo4j.model.RespParm;
import com.jane.neo4j.services.PersonService;
import com.jane.neo4j.services.PropagatorService;
import com.jane.neo4j.utils.JsonTools;
import com.jane.neo4j.utils.StringUtils;

@RestController
public class PropagatorController {

	@Autowired
	private PropagatorService propagatorService;

	@Autowired
	private PersonService userService;

	@RequestMapping("/create-propagator")
	public String personPropagator(@RequestBody ReqParm req) {
		String jsonStr = req.getBodys();

		PropagatorVO PropagatorVO = (PropagatorVO) JsonTools.parserStrToBean(jsonStr, PropagatorVO.class);
		String entrustId = PropagatorVO.getEntrustId();
		String nameChecked = PropagatorVO.getNameChecked();
		String startNodeId = PropagatorVO.getStartNodeId();
		Person startPersonInfo = userService.queryPersonInfo(startNodeId);
		// TODO 检查姓名
		if (!startPersonInfo.getName().equals(nameChecked)) {
			return "FILE";
		}
		Propagator propagatorStart = propagatorService.queryOnePropagator(entrustId, startNodeId);
		// TODO 理论上 传播者的信息 开始节点 一定存在
		String endNodeId = PropagatorVO.getEndNodeId();
		Propagator propagatorEnd = propagatorService.queryOnePropagator(entrustId, endNodeId);
		if (null != propagatorEnd) {
			// TODO 事件ID 和 人 ID 已经存在 表示该人已经参与过 传播路径 需要判断节点是否是父节点 ，暂时简单处理 不允许参加活动
			return "FILE";
		} else {
			// 创建新的的 子节点信息
			String parentId = propagatorStart.getPropagatorId();
			Person person = userService.queryPersonInfo(endNodeId);
			Propagator propagator = new Propagator();
			propagator.setEntrustId(entrustId);
			propagator.setName(person.getName());
			propagator.setPhoneNumber(person.getPhoneNumber());
			propagator.setParentId(parentId);
			propagator.setUserId(endNodeId);
			propagator.setPropagatorId(StringUtils.getUUID());
			propagator.setType(PersonTypeEnum.转播者);
			final Propagator savePropagator = propagatorService.savePropagator(propagator);
			List<Propagator> propagatorList = propagatorStart.getPropagator();
			propagatorList.add(savePropagator);
			propagatorStart.setPropagator(propagatorList);
			propagatorService.savePropagator(propagatorStart);
		}

		// 生成自己的URL
		
		String url = "http://www/jane/!!!!!!!";
		RespParm resp = new RespParm(); 
		resp.setBodys(url);
		return JsonTools.toJson(resp);

		// Map<String, Object> beanMap = JsonTools.getBeanMap(jsonStr);
		// String strStarNode = (String) beanMap.get("startNode");
		// String strEndNode = (String) beanMap.get("endNode");
		// Person startNode = (Person) JsonTools.parserStrToBean(strStarNode,
		// Person.class);
		// Person endNode = (Person) JsonTools.parserStrToBean(strEndNode,
		// Person.class);
		//
		// String entrustId = startNode.getEntrustId();
		// String startNodeUserId = startNode.getUserId();
		// String endNodeUserId = endNode.getUserId();
		//
		// Propagator propagator =
		// propagatorService.queryOnePropagator(entrustId,startNodeUserId);
		// if(null==propagator){
		// //如为空 创建
		// Propagator
		// }else{
		// //新增数据
		//
		// }
		//
		// Person querystartNode = userService.queryPersonInfo(userId);
		// List<Person> propagator = querystartNode.getPropagator();
		// Person queryendNode = userService.queryPersonInfo(userId2);
		// if(propagator.isEmpty()){
		// List<Person> propagatorList = new ArrayList<Person>();
		// propagatorList.add(queryendNode);
		// querystartNode.setPropagator(propagatorList);
		// }else{
		// propagator.add(queryendNode);
		// querystartNode.setPropagator(propagator);
		// }
		// userService.savePerson(querystartNode);

	}
}
