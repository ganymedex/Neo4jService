package com.jane.neo4j.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jane.neo4j.domain.Bider;
import com.jane.neo4j.domain.Entrust;
import com.jane.neo4j.domain.LinkRecords;
import com.jane.neo4j.domain.Person;
import com.jane.neo4j.domain.Propagator;
import com.jane.neo4j.eum.BidStatusEnum;
import com.jane.neo4j.eum.PersonTypeEnum;
import com.jane.neo4j.eum.ServiceStatusEnum;
import com.jane.neo4j.model.BiderVO;
import com.jane.neo4j.model.ConsumerVO;
import com.jane.neo4j.model.PropagatorVO;
import com.jane.neo4j.model.ReqParm;
import com.jane.neo4j.model.RespParm;
import com.jane.neo4j.services.BiderService;
import com.jane.neo4j.services.EntrustService;
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
public class PropagatorController {

	@Value("${url.base}")
	private String URL;

	@Autowired
	private PropagatorService propagatorService;

	@Autowired
	private PersonService userService;

	@Autowired
	private LinkRecordService linkRecordService;

	@Autowired
	private EntrustService entrustService;

	@Autowired
	private BiderService biderService;

	@ApiOperation(value = "创建自己的传播链接", notes = "检查事件状态，不能重复创建已经存在的")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "PropagatorVO", value = "传播信息VO", required = true, dataType = "PropagatorVO") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Pet not found") })
	@RequestMapping(value = "/create-propagator-url", method = RequestMethod.POST)
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
		// TODO 检查 事件是否结束
		Entrust OneByEntrust = entrustService.queryOneByEntrustId(entrustId);
		ServiceStatusEnum entrustStatus = OneByEntrust.getEntrustStatus();
		int s = entrustStatus.getIndex();
		if (ServiceStatusEnum.主动结束.getIndex() == s) {

			return ServiceStatusEnum.主动结束.getName();
		}
		if (ServiceStatusEnum.传播结束.getIndex() == s) {

			return ServiceStatusEnum.传播结束.getName();
		}
		if (ServiceStatusEnum.揭榜结束.getIndex() == s) {

			return ServiceStatusEnum.揭榜结束.getName();
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

		// 生成自己的URL 事件ID 连接上家ID

		String url = URL + "";

		LinkRecords entity = new LinkRecords();
		entity.setCreateNodeId(endNodeId);
		entity.setUrl(url);
		entity.setEntrustId(entrustId);
		entity.setRecordId(StringUtils.getUUID());
		linkRecordService.saveLinkRecords(entity);

		RespParm resp = new RespParm();
		resp.setBodys(url);
		return JsonTools.toJson(resp);

	}

	// 创建揭榜者 信息

	/**
	 * 1存储传播路径 传播类型 揭榜者 2修改事件 状态 3生产消费码
	 */
	@ApiOperation(value = "揭榜事件", notes = "检查事件状态，不能重复创建已经存在的,创建消费码 消息通知TODO", response = Bider.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "BiderVO", value = "揭榜VO", required = true, dataType = "BiderVO") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Bider.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Pet not found") })
	@RequestMapping(value = "/create-bider", method = RequestMethod.POST)
	public String createBider(@RequestBody ReqParm req) {
		String jsonStr = req.getBodys();
		BiderVO bean = (BiderVO) JsonTools.parserStrToBean(jsonStr, BiderVO.class);

		String startNodeId = bean.getStartNodeId();
		String checkName = bean.getCheckName();
		String biderId = bean.getBiderId();
		String entrustId = bean.getEntrustId();
		Propagator propagatorStart = propagatorService.queryOnePropagator(entrustId, startNodeId);

		// TODO 检查姓名
		if (!propagatorStart.getName().equals(checkName)) {
			return "FILE";
		}
		// TODO 检查 事件是否结束
		Entrust OneByEntrust = entrustService.queryOneByEntrustId(entrustId);
		ServiceStatusEnum entrustStatus = OneByEntrust.getEntrustStatus();
		int s = entrustStatus.getIndex();
		if (ServiceStatusEnum.主动结束.getIndex() == s) {

			return ServiceStatusEnum.主动结束.getName();
		}
		if (ServiceStatusEnum.传播结束.getIndex() == s) {

			return ServiceStatusEnum.传播结束.getName();
		}
		if (ServiceStatusEnum.揭榜结束.getIndex() == s) {

			return ServiceStatusEnum.揭榜结束.getName();
		}

		Propagator propagatorEnd = propagatorService.queryOnePropagator(entrustId, biderId);
		Person personBider = userService.queryPersonInfo(biderId);
		if (null != propagatorEnd) {
			// TODO 事件ID 和 人 ID 已经存在 表示该人已经参与过 传播路径 需要判断节点是否是父节点 ，暂时简单处理 不允许参加活动
			return "FILE";
		} else {
			// 创建新的的 子节点信息
			String parentId = propagatorStart.getPropagatorId();
			Propagator propagator = new Propagator();
			propagator.setEntrustId(entrustId);
			propagator.setName(personBider.getName());
			propagator.setPhoneNumber(personBider.getPhoneNumber());
			propagator.setParentId(parentId);
			propagator.setUserId(biderId);
			propagator.setPropagatorId(StringUtils.getUUID());
			propagator.setType(PersonTypeEnum.揭榜者);
			final Propagator savePropagator = propagatorService.savePropagator(propagator);
			List<Propagator> propagatorList = propagatorStart.getPropagator();
			propagatorList.add(savePropagator);
			propagatorStart.setPropagator(propagatorList);
			propagatorService.savePropagator(propagatorStart);
		}

		// 生成自己的F码 TODO 后期通过构造方法 copy一致名称数据
		Bider bider = new Bider();
		bider.setBiderCode(StringUtils.getUUID());
		bider.setBiderName(personBider.getName());
		bider.setPhoneNumber(personBider.getPhoneNumber());
		bider.setBiderUserId(biderId);
		bider.setEntrustId(entrustId);
		bider.setBidStatus(BidStatusEnum.未读);

		Bider saveBider = biderService.saveBider(bider);

		RespParm resp = new RespParm();
		resp.setBodys(JsonTools.toJson(saveBider));
		return JsonTools.toJson(resp);

	}
	
	
	@ApiOperation(value = "消费事件", notes = "消费事件修改 事件结束状态   修改揭榜状态")
	@ApiImplicitParams({ @ApiImplicitParam(name = "ConsumerVO", value = "揭榜VO", required = true, dataType = "ConsumerVO") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SUC"),
			@ApiResponse(code = 400, message = "Invalid ID supplied"),
			@ApiResponse(code = 404, message = "Pet not found") })
	@RequestMapping(value = "/consumer", method = RequestMethod.POST)
	public String consumerEntust(@RequestBody ReqParm req){
		String jsonStr =req.getBodys();
		ConsumerVO ben=(ConsumerVO) JsonTools.parserStrToBean(jsonStr, ConsumerVO.class);
		
		String bidCode = ben.getBidCode();
		String biderId = ben.getBiderId();
		String entrustId = ben.getEntrusId();
		//1 修改消费码状态 
		Bider bider=biderService.queryBidByBidCode(bidCode);
		String biderUserId = bider.getBiderUserId();
		if(!biderUserId.equals(biderId)){
			return "FIL";
		}
		bider.setBidStatus(BidStatusEnum.已读);
		biderService.saveBider(bider);
		//2 修改事件状态
		Entrust entiry = entrustService.queryOneByEntrustId(entrustId);
		entiry.setEntrustStatus(ServiceStatusEnum.揭榜结束);
		entrustService.save(entiry);
		return "SUC";
	}

}
