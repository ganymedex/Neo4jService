package com.jane.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jane.neo4j.domain.Bider;
import com.jane.neo4j.repositories.BiderRespository;

@Service(version = "1.0.0")
public class BiderServiceImpl implements BiderService{

	@Autowired
	private BiderRespository biderRespository;
	
	@Override
	public Bider saveBider(Bider bider) {
		return biderRespository.save(bider);
	}

	@Override
	public Bider queryBidByBidCode(String bidCode) {
		return biderRespository.queryBidByBidCode(bidCode);
	}

}
