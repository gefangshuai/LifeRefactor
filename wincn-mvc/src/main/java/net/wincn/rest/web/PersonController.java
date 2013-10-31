package net.wincn.rest.web;

import net.wincn.rest.model.Person;
import net.wincn.rest.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person/*")
public class PersonController {
	@Autowired
	private PersonService personService;

	@RequestMapping(value = "add")
	@ResponseBody
	public String addPerson(Person person) {
//		personService.save(person);
		return "success";
	}

	@RequestMapping(value = "{id}")
	@ResponseBody
	public Person getPerson(@PathVariable long id) {
		Person person = personService.get(id);

		return person;
	}
}
