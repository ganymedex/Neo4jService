package com.jane.neo4j.repositories;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jane.neo4j.domain.Entrust;

@RepositoryRestResource(collectionResourceRel = "entrusts", path = "entrust")
public interface EntrustResitory extends GraphRepository<Entrust> {

//	@Query("match (p: ENTRUST {entrustTitle:{0}}) return p;")
//	Collection<Entrust> queryByName(String title);
//	
//	@Query("match (p: ENTRUST {applId:{title}}) return p;")
//	List<Entrust> queryEntrustByName(@Param("title") String title);

	@Query("match (p: ENTRUST {applId:{entrustId}}) return p;")
	Entrust queryOneByEntrustId(@Param("entrustId")String entrustId);
	@Query("match (p: ENTRUST {applId:{0}}) return p;")
	List<Entrust> queryListByUserId(String userId);

}
