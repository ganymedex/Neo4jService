package com.jane.neo4j.repositories;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jane.neo4j.domain.LinkOwner;

@RepositoryRestResource(collectionResourceRel = "linkOwners", path = "linkOwners")
public interface LinkOwnerResitory extends GraphRepository<LinkOwner>{
	
	

}
