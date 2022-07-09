package com.github.williamjbf.moneyapi.service;

import com.github.williamjbf.moneyapi.model.Person;
import com.github.williamjbf.moneyapi.model.Posting;
import com.github.williamjbf.moneyapi.repository.PersonRepository;
import com.github.williamjbf.moneyapi.repository.PostingRepository;
import com.github.williamjbf.moneyapi.service.exception.PersonNotExistOrInactiveException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Service
public class PostingService {

    private final PersonRepository personRepository;
    private final PostingRepository postingRepository;

    public PostingService(PersonRepository personRepository, PostingRepository postingRepository) {
        this.personRepository = personRepository;
        this.postingRepository = postingRepository;
    }

    public Posting save(Posting posting){
        Optional<Person> person = personRepository.findById(posting.getPerson().getId());
        if(person.isEmpty() || person.get().isInactive()){
            throw new PersonNotExistOrInactiveException();
        }
        return postingRepository.save(posting);
    }

}
