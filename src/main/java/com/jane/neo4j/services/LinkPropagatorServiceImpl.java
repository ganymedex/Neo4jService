package com.jane.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jane.neo4j.domain.LinkPropagator;
import com.jane.neo4j.repositories.LinkPropagatorResitory;


@Service
public class LinkPropagatorServiceImpl implements LinkPropagatorService {
 
	@Autowired
	private LinkPropagatorResitory linkPropagatorResitory;
	
	
	@Override
	public LinkPropagator saveLinkPropagator(LinkPropagator link) {
		return linkPropagatorResitory.save(link);
	}

}
