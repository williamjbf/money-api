package com.github.williamjbf.moneyapi.resource;

import com.github.williamjbf.moneyapi.model.People;
import com.github.williamjbf.moneyapi.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
