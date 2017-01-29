package com.jane.neo4j.services;

import com.jane.neo4j.domain.Propagator;

/**
 * 
 * 保存传播子类
 * @author ganymedex
 *
 */
public interface PropagatorService {
  
	Propagator savePropagator(Propagator bean);

	Propagator queryOnePropagator(String entrustId, String startNodeUserId);
	
}
