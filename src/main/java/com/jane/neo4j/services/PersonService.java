package com.jane.neo4j.services;

import com.jane.neo4j.domain.Person;

public interface PersonService {
  
	
	Person queryPersonInfo(String uId);
	
	Person savePerson(Person p);
	
	
}
