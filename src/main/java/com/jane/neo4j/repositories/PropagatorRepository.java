package com.jane.neo4j.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jane.neo4j.domain.Propagator;


@RepositoryRestResource(collectionResourceRel = "propagatoers", path = "propagatoers")
public interface PropagatorRepository extends GraphRepository<Propagator>{

	@Query("match (p: PROPAGATORER {entrustId:{0},userId:{1}}) return p;")
	Propagator queryOnePropagator(String entrustId, String startNodeUserId);

}
