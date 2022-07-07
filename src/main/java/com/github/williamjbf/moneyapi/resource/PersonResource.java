package com.github.williamjbf.moneyapi.resource;

import com.github.williamjbf.moneyapi.event.ResourceCreatedEvent;
import com.github.williamjbf.moneyapi.model.Address;
import com.github.williamjbf.moneyapi.model.Person;
import com.github.williamjbf.moneyapi.repository.PersonRepository;
import com.github.williamjbf.moneyapi.service.PersonService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonResource {

    private final PersonRepository personRepository;
    private final ApplicationEventPublisher publisher;
    private final PersonService personService;

    public PersonResource(PersonRepository personRepository, ApplicationEventPublisher publisher, PersonService personService) {
        this.personRepository = personRepository;
        this.publisher = publisher;
        this.personService = personService;
    }

    @GetMapping
    public List<Person> list(){
        return personRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Person> create(@Valid @RequestBody Person person, HttpServletResponse response){
        Person savedPerson = personRepository.save(person);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, savedPerson.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id){
        Optional<Person> people = personRepository.findById(id);
        return people.isPresent() ? ResponseEntity.ok(people.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        personRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person){
        return personService.update(id, person);
    }

    @PutMapping("/{id}/active")
    public ResponseEntity<Person> updateActiveStatus(@PathVariable Long id, @RequestBody Boolean status){
        return personService.updateActiveStatus(id,status);
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Person> updatePeopleAddress(@PathVariable Long id, @RequestBody Address address){
        return personService.updatePeopleAddress(id,address);
    }

}
