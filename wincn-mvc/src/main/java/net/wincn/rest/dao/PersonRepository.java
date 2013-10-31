package net.wincn.rest.dao;

import net.wincn.rest.model.Person;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
	
}
