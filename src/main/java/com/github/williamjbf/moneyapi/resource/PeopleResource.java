package com.github.williamjbf.moneyapi.resource;

import com.github.williamjbf.moneyapi.model.Category;
import com.github.williamjbf.moneyapi.model.People;
import com.github.williamjbf.moneyapi.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleResource {

    private final PeopleRepository peopleRepository;

    public PeopleResource(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @GetMapping
    public List<People> list(){
        return peopleRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<People> create(@Valid @RequestBody People people, HttpServletResponse response){
        People savedPeople = peopleRepository.save(people);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(savedPeople.getId()).toUri();
        return ResponseEntity.created(uri).body(savedPeople);
    }

}
