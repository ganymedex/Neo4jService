package com.jane.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jane.neo4j.domain.Person;
import com.jane.neo4j.repositories.PersonResitory;


@Service(version = "1.0.0")
public class PersonServiceImpl implements PersonService {
	
	
	@Autowired
	private PersonResitory personResitory;


	@Override
	public Person queryPersonInfo(String uId) {
		return personResitory.queryPersonByuId(uId);
	}


	@Override
	public Person savePerson(Person p) {
		return personResitory.save(p);
	}

}
