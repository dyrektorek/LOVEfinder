package com.example.rafa.rafaspringdemoapp.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.rafa.rafaspringdemoapp.entity.Person;

@Repository
public interface PersonRestRepository extends JpaRepository<Person, Integer> {

}
