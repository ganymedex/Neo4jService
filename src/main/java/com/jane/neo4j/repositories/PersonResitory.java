package com.jane.neo4j.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jane.neo4j.domain.Person;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonResitory extends GraphRepository<Person>{
	
	@Query("match (p: PERSON {userId:{uId}}) return p;")
	Person queryPersonByuId(String uId);

}
