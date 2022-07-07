package com.github.williamjbf.moneyapi.service;

import com.github.williamjbf.moneyapi.model.Address;
import com.github.williamjbf.moneyapi.model.Person;
import com.github.williamjbf.moneyapi.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseEntity<Person> update(Long id, Person person){
        Person savedPerson = findPeopleById(id);

        BeanUtils.copyProperties(person, savedPerson,"id");
        personRepository.save(savedPerson);
        return ResponseEntity.ok(savedPerson);
    }

    public ResponseEntity<Person> updateActiveStatus(Long id, Boolean status) {
        Person savedPerson = findPeopleById(id);
        savedPerson.setActive(status);
        personRepository.save(savedPerson);
        return ResponseEntity.ok(savedPerson);
    }

    public ResponseEntity<Person> updatePeopleAddress(Long id, Address address) {
        Person savedPerson = findPeopleById(id);
        savedPerson.setAddress(address);
        personRepository.save(savedPerson);
        return ResponseEntity.ok(savedPerson);
    }

    private Person findPeopleById(Long id) {
        Optional<Person> savedPeople = personRepository.findById(id);
        if(savedPeople.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return savedPeople.get();
    }
}
