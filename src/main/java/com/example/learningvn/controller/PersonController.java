package com.example.learningvn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningvn.model.entity.Person;
import com.example.learningvn.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/people")
public class PersonController {
    
    private PersonService personService;

    public PersonController() {

    }

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPeople() {
        log.debug("REST request to get all people");
        List<Person> people = personService.getPeople();

        log.info("Successfully fetched {} people", people.size());
        return ResponseEntity.ok(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        log.debug("REST request to get a person using id");
        Person person = personService.getPersonById(id);

        log.info("Successfully fetched a person: {}", person.getName());
        return ResponseEntity.ok(person);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
