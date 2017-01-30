package com.jane.neo4j.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jane.neo4j.domain.LinkRecords;


@RepositoryRestResource(collectionResourceRel = "link-records", path = "link-records")
public interface LinkRecordRepository extends GraphRepository<LinkRecords>{

}
