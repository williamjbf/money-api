package com.github.williamjbf.moneyapi.service;

import com.github.williamjbf.moneyapi.model.Address;
import com.github.williamjbf.moneyapi.model.People;
import com.github.williamjbf.moneyapi.repository.PeopleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public ResponseEntity<People> update(Long id,People people){
        People savedPeople = findPeopleById(id);

        BeanUtils.copyProperties(people,savedPeople,"id");
        peopleRepository.save(savedPeople);
        return ResponseEntity.ok(savedPeople);
    }

    public ResponseEntity<People> updateActiveStatus(Long id, Boolean status) {
        People savedPeople = findPeopleById(id);
        savedPeople.setActive(status);
        peopleRepository.save(savedPeople);
        return ResponseEntity.ok(savedPeople);
    }

    public ResponseEntity<People> updatePeopleAddress(Long id, Address address) {
        People savedPeople = findPeopleById(id);
        savedPeople.setAddress(address);
        peopleRepository.save(savedPeople);
        return ResponseEntity.ok(savedPeople);
    }

    private People findPeopleById(Long id) {
        Optional<People> savedPeople = peopleRepository.findById(id);
        if(savedPeople.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return savedPeople.get();
    }
}
