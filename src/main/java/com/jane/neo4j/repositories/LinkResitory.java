package com.jane.neo4j.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jane.neo4j.domain.Link;

@RepositoryRestResource(collectionResourceRel = "links", path = "links")
public interface LinkResitory extends GraphRepository<Link>{
	
	

}
