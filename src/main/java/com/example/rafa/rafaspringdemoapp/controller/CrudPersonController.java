package com.example.rafa.rafaspringdemoapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.rafa.rafaspringdemoapp.db.PersonCrudRepository;
import com.example.rafa.rafaspringdemoapp.entity.Person;

@Controller
public class CrudPersonController {

	@Autowired
	private PersonCrudRepository personRepository;

	@GetMapping(path = "/all")
	public List<Person> findAll(){
		
		ArrayList<Person> people = new ArrayList<>();
		personRepository.findAll().forEach(people::add);;
		return people;
	}
	
	@RequestMapping("/people")
	public String people(Model model, RedirectAttributes redir) {
		Iterable<Person> peopleIter = personRepository.findAll();
		int startid = 0;

		System.out.println("StartIdModel: " + model.getAttribute("startid"));
		if (model.getAttribute("startid")!= null) {
			startid = (int) model.getAttribute("startid");
		}
		model.addAttribute("startid", startid);
		
		ArrayList<Person> people = new ArrayList<>();
		peopleIter.forEach(people::add);
		
		model.addAttribute("people", people);
		
		return "people";	
	}
	
	
	@RequestMapping("/person")
	public String person(Model model) {
		Person person = personRepository.findById(1).get();
		model.addAttribute("person", person);
		return "person";	
	}
	
	@RequestMapping("/deleteall")
	public String deleteAll() {
		personRepository.deleteAll();
		return "home";
	}
	
	
	@RequestMapping(value = "/deleteperson/{instanceId}")
	public RedirectView  deletePerson(@RequestParam int startid, @PathVariable int instanceId, Model model, HttpServletRequest req, RedirectAttributes redir) {
		personRepository.deleteById(instanceId);
		System.out.println("Startid1: " + startid);
		model.addAttribute("startid", startid);
		System.out.println(model.getAttribute("startid"));
		
		
		RedirectView redirectView;
	    redirectView = new RedirectView("/people", true);
	    redir.addFlashAttribute("startid", startid);
	    
	    return redirectView;
	}
	
	@RequestMapping("/addpersonform")
	public String addPersonForm() {
		
		return "addpersonform";
	}
	
	@RequestMapping("/addperson")
	public String save(	@RequestParam("firstName") String firstName, 
						@RequestParam("lastName") String lastName, 
						@RequestParam("lookingFor") String lookingFor, 
						@RequestParam("pictureUrl") String pictureUrl,  
						Model model) {
		
		personRepository.save(new Person(firstName, lastName, pictureUrl, lookingFor));
		return "addperson";
	}
	
	
	
}
