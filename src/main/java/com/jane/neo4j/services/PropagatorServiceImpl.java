package com.jane.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jane.neo4j.domain.Propagator;
import com.jane.neo4j.repositories.PropagatorRepository;


@Service(version = "1.0.0")
public class PropagatorServiceImpl implements PropagatorService {
	

	@Autowired
	private PropagatorRepository propagatorRepository;
	@Override
	public Propagator savePropagator(Propagator bean) {
		return	propagatorRepository.save(bean);
	}
	
	
	@Override
	public Propagator queryOnePropagator(String entrustId, String startNodeUserId) {
		return propagatorRepository.queryOnePropagator(entrustId,startNodeUserId);
	}

}
