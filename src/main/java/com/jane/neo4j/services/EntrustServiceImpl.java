package com.jane.neo4j.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jane.neo4j.domain.Entrust;
import com.jane.neo4j.repositories.EntrustResitory;

@Service(version = "1.0.0")
public class EntrustServiceImpl implements EntrustService {

	@Autowired(required = true)
	private EntrustResitory entrustResitory;

	@Override
	public Entrust save(Entrust entiry) {
		return entrustResitory.save(entiry);
	}

	@Override
	public Entrust queryOneByEntrustId(String entrustId) {
		return entrustResitory.queryOneByEntrustId(entrustId);
	}

	@Override
	public List<Entrust> queryListByUserId(String userId) {
		return entrustResitory.queryListByUserId(userId);
	}


}
