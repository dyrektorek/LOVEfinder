package com.example.rafa.rafaspringdemoapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rafa.rafaspringdemoapp.db.PersonCrudRepository;
import com.example.rafa.rafaspringdemoapp.db.PersonRestRepository;
import com.example.rafa.rafaspringdemoapp.entity.Person;

@RestController
@RequestMapping("/api")
public class RestPersonController {

	@Autowired
	private PersonRestRepository personRepository;

	@GetMapping(path = "/people")
	public List<Person> findAll(){
		
		ArrayList<Person> people = new ArrayList<>();
		personRepository.findAll().forEach(people::add);;
		return people;	
	}
	
	@GetMapping(path = "/people/{instanceId}")
	public Person findById(@PathVariable int instanceId){
		return personRepository.findById(instanceId).get();
	}
}
