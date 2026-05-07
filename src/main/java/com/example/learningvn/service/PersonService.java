package com.example.learningvn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningvn.exception.PersonNotFoundException;
import com.example.learningvn.model.entity.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService {
    private List<Person> people;
    
    @Autowired
    public PersonService() {
        this.people = new ArrayList<>();
        this.people.add(new Person(Long.valueOf(1), "Nguyen Khac Le Nhan", Integer.valueOf(20)));
        this.people.add(new Person(Long.valueOf(2), "Dang Nhat Thien Bao", Integer.valueOf(20)));
        this.people.add(new Person(Long.valueOf(3), "Ho Duong Nhat Quang", Integer.valueOf(20)));
        this.people.add(new Person(Long.valueOf(4), "Nguyen Huynh Minh Nhat", Integer.valueOf(20)));
        this.people.add(new Person(Long.valueOf(5), "Do Thi Phuong Nhi", Integer.valueOf(20)));
        this.people.add(new Person(Long.valueOf(6), "Nguyen Thi Thanh Nha", Integer.valueOf(20)));
        this.people.add(new Person(Long.valueOf(7), "Ngo Thi Kim Ngan", Integer.valueOf(20)));
        this.people.add(new Person(Long.valueOf(8), "William Nguyen", Integer.valueOf(20)));
    }

    public Person getPersonById(Long id) {
        log.debug("Find student with id: {}", id);
        return getPeople().stream()
                .filter(person -> person.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id: " + id));
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

}
