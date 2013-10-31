package net.wincn.base.utils.velocity.service;

import net.wincn.rest.model.Person;

import org.junit.Test;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestTest {

	@Test
	public void testPost() {
		Person person = new Person();
		person.setName("张三");
		person.setAge(23);
		person.setEmail("zhangsan@163.com");

		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		restTemplate.postForObject("http://localhost:8888/person/add", person, Person.class);
	}

	public void testGet() {
		RestTemplate template = new RestTemplate();
		Person person = template.getForObject("http://localhost:8888/person/1", Person.class);

		System.out.println(person.toString());
	}
}
