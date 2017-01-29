package com.jane.neo4j.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jane.neo4j.domain.Person;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonResitory extends GraphRepository<Person>{
	
	@Query("match (p: PERSON {userId:{uId}}) return p;")
	Person queryPersonByuId(@Param("uId")String uId);

}


//@RepositoryRestController
//public interface GroupRepository extends GraphRepository<Group> {
//
//    @Query("MATCH (member:Member)-[:INTERESTED_IN]->(topic), (member)-[:MEMBER_OF]->(group)-[:HAS_TOPIC]->(topic) " +
//            "WHERE id(member) = {member} " +
//            "WITH member, topic, COUNT(*) AS score " +
//            "MATCH (topic)<-[:HAS_TOPIC]-(otherGroup) WHERE NOT (member)-[:MEMBER_OF]->(otherGroup) " +
//            "RETURN otherGroup, COLLECT(topic.name) as topics, SUM(score) as score " +
//            "ORDER BY score DESC")
//    List<GroupRecommendation> recommendGroupsByTopic(@Param("member") Member member);
//
//    class GroupRecommendation {
//        Group group;
//        List<String> topics;
//        long score;
//    }
//}