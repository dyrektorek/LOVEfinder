package com.example.rafa.rafaspringdemoapp.db;

import java.util.List;

import com.example.rafa.rafaspringdemoapp.entity.Person;

public interface PersonService {
	public List<Person> findAll();

	public Person findById(int id);

	public void save(Person person);

	public void deleteById(int id);
	
	public void deleteAll();

}
