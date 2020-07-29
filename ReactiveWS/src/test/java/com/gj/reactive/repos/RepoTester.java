package com.gj.reactive.repos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.gj.reactive.entity.Person;

@SpringBootTest
public class RepoTester {

	@Autowired
	@Qualifier("personMapRepository")
	IMapRepository<Person,Integer> ipersonRepository;
	
	
	@Test
	public void addPersonToRepo() {
		
	Person p1=new Person("ganesh");
	p1=ipersonRepository.createEntity(p1);
	assertThat(p1).isNotNull();
	assertThat(p1.getId()).isNotNull().isGreaterThan(0);
	
	
		
	}
	
	@Test
	public void getPersonFromRepoById() {
		
    Person p1=new Person("ganesh");
    p1.setId(1);
    ipersonRepository.createEntity(p1);		
	Person p2=ipersonRepository.get(1);
	assertThat(p2).isNotNull();
	assertThat(p2.getId()).isNotNull().isEqualTo(1);
	
	
		
	}
	
	
	@Test
	public void getPeople() {
		
    Person p1=new Person("ganesh");
    p1.setId(1);
    ipersonRepository.createEntity(p1);		

    Person p2=new Person("aarvi");
    p2.setId(2);
    ipersonRepository.createEntity(p2);
    
    List<Person> list=ipersonRepository.getAll();

	assertThat(list).isNotNull().isNotEmpty().hasSize(2);
	
		
	}
}
