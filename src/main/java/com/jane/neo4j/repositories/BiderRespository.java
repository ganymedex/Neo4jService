package com.jane.neo4j.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jane.neo4j.domain.Bider;


@RepositoryRestResource(collectionResourceRel = "bider", path = "bider")
public interface BiderRespository extends GraphRepository<Bider>{

	@Query("match (p: BIDER {biderCode:{0}}) return p;")
	Bider queryBidByBidCode(String bidCode);

}
