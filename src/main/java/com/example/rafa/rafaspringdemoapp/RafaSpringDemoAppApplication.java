package com.example.rafa.rafaspringdemoapp;

import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.rafa.rafaspringdemoapp.db.PersonCrudRepository;
import com.example.rafa.rafaspringdemoapp.entity.Person;

@SpringBootApplication
public class RafaSpringDemoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RafaSpringDemoAppApplication.class, args);
	}
	
	
	
	@Bean
	public Person addPerson(PersonCrudRepository repo){
		for (int i = 0; i < 10; i++) {
			Person man = new Person("Jan" + i, "Dzban" + i, "https://randomuser.me/api/portraits/men/" + i + ".jpg", "male");
			Person woman = new Person("Janina" + i, "Dzbanica" + i, "https://randomuser.me/api/portraits/women/" + i + ".jpg", "female");
			repo.save(man);
			repo.save(woman);
		}
		
		ArrayList<Person> people = new ArrayList<>();
		repo.findAll().forEach(people::add);
		System.out.println(people);
//		repo.delete(rafa);

		return new Person("Rafa", "Dyrek", "http://dupa.pl", "male");
	}

	@Bean
	public void getPeople(){
		//PersonServiceImpl personServiceImpl = new PersonServiceImpl();
//		if (personServiceImpl.findAll() != null) {
//			List<Person> people = personServiceImpl.findAll();
//			for (Person person : people) {
//				System.out.println(person.getFirstName());
//			}
//		} else {
//			System.out.println("NNNNNNNNNNUUUUUUUUUUULLLLLLLLLLL");
//		}
		
		
	}
	
	
//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println("RAFA" + beanName);
//			}
//
//		};
//	}
}
