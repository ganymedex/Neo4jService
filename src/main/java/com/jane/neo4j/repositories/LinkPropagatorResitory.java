package com.jane.neo4j.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jane.neo4j.domain.LinkPropagator;

@RepositoryRestResource(collectionResourceRel = "link-propagators", path = "link-propagators")
public interface LinkPropagatorResitory extends GraphRepository<LinkPropagator>{
	
	

}
