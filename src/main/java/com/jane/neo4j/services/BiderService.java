package com.jane.neo4j.services;

import com.jane.neo4j.domain.Bider;

public interface BiderService {
 
	
	public Bider saveBider(Bider bider);

	public Bider queryBidByBidCode(String bidCode);
}
