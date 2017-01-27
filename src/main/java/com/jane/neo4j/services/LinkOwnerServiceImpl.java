package com.jane.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jane.neo4j.domain.LinkOwner;
import com.jane.neo4j.repositories.LinkOwnerResitory;

@Service(version = "1.0.0")
public class LinkOwnerServiceImpl implements LinkOwnerService{
	
	
	@Autowired
	private LinkOwnerResitory linkOwnerResitory;

	@Override
	public LinkOwner saveLinkOwner(LinkOwner link) {
		return linkOwnerResitory.save(link);
	}

}
