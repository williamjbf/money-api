package com.github.williamjbf.moneyapi.resource;

import com.github.williamjbf.moneyapi.event.ResourceCreatedEvent;
import com.github.williamjbf.moneyapi.model.People;
import com.github.williamjbf.moneyapi.repository.PeopleRepository;
import com.github.williamjbf.moneyapi.service.PeopleService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PeopleResource {

    private final PeopleRepository peopleRepository;
    private final ApplicationEventPublisher publisher;
    private final PeopleService peopleService;

    public PeopleResource(PeopleRepository peopleRepository, ApplicationEventPublisher publisher, PeopleService peopleService) {
        this.peopleRepository = peopleRepository;
        this.publisher = publisher;
        this.peopleService = peopleService;
    }

    @GetMapping
    public List<People> list(){
        return peopleRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<People> create(@Valid @RequestBody People people, HttpServletResponse response){
        People savedPeople = peopleRepository.save(people);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, savedPeople.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPeople);
    }

    @GetMapping("/{id}")
    public ResponseEntity<People> findById(@PathVariable Long id){
        Optional<People> people = peopleRepository.findById(id);
        return people.isPresent() ? ResponseEntity.ok(people.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        peopleRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<People> update(@PathVariable Long id, @Valid @RequestBody People people){
        return peopleService.update(id, people);
    }

    @PutMapping("/{id}/active")
    public void updateActiveStatus(@PathVariable Long id, @RequestBody Boolean status){
        peopleService.updateActiveStatus(id,status);
    }

}
