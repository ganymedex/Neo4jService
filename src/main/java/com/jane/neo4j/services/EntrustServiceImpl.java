package com.jane.neo4j.services;

import java.util.Collection;
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
		Entrust bean = entrustResitory.save(entiry);
		System.out.println(bean);
		return bean;
	}

	@Override
	public List<Entrust> queryListBy(String name) {
		List<Entrust> list = entrustResitory.queryEntrustByName(name);
		return list;
	}

	public Collection<Entrust> queryListByTitle(String name) {
		Collection<Entrust> queryByName = entrustResitory.queryByName(name);
		return queryByName;
	}

}
