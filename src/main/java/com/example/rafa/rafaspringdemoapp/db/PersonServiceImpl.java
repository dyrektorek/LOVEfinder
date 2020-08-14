package com.example.rafa.rafaspringdemoapp.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rafa.rafaspringdemoapp.entity.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRestRepository personRepository;
	
	@Override
	public List<Person> findAll(){
		
		ArrayList<Person> people = new ArrayList<>();
		personRepository.findAll().forEach(people::add);;
		return people;
	}
	
	@Override
	public Person findById(int id) {
		Optional<Person> result = personRepository.findById(id);
		System.out.println("RESULT: " + result);
		Person person = null;
		if (result.isPresent()) {
			person = result.get();
		} else {
			throw new RuntimeException("Person with ID: " + id + " not found");
		}	
		return person;
	}

	@Override
	public void save(Person person) {
		Person tempPerson = personRepository.save(person);
		person.setId(tempPerson.getId());
	}

	@Override
	public void deleteById(int id) {
		personRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		personRepository.deleteAll();
		
	}

}
