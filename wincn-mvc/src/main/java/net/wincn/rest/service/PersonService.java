package net.wincn.rest.service;

import net.wincn.rest.dao.PersonRepository;
import net.wincn.rest.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	@Transactional
	public void save(Person person) {
		personRepository.save(person);
	}

	public Person get(long id) {
		return personRepository.findOne(id);
	}
}
