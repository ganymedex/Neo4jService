package com.jane.neo4j.services;

import java.util.List;

import com.jane.neo4j.domain.Entrust;

public interface EntrustService {
  
	Entrust save(Entrust entiry);

	Entrust queryOneByEntrustId(String entrustId);

	List<Entrust> queryListByUserId(String userId);
	
	
	
}
