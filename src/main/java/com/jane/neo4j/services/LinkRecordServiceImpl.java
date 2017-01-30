package com.jane.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jane.neo4j.domain.LinkRecords;
import com.jane.neo4j.repositories.LinkRecordRepository;

@Service(version = "1.0.0")
public class LinkRecordServiceImpl implements LinkRecordService {

	@Autowired
	private LinkRecordRepository linkRecordRepository;

	
	@Override
	public LinkRecords saveLinkRecords(LinkRecords entity) {
		return linkRecordRepository.save(entity);
	}

}
