package com.github.williamjbf.moneyapi.resource;

import com.github.williamjbf.moneyapi.event.ResourceCreatedEvent;
import com.github.williamjbf.moneyapi.model.Person;
import com.github.williamjbf.moneyapi.model.Posting;
import com.github.williamjbf.moneyapi.repository.PostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postings")
public class PostingResource {

    private final PostingRepository postingRepository;

    private final ApplicationEventPublisher publisher;

    public PostingResource(PostingRepository postingRepository, ApplicationEventPublisher publisher) {
        this.postingRepository = postingRepository;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Posting> list(){
        return postingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Posting> findById(@PathVariable Long id){
        Optional<Posting> posting = postingRepository.findById(id);
        return posting.isPresent() ? ResponseEntity.ok(posting.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Posting> create(@Valid @RequestBody Posting posting, HttpServletResponse response){
        Posting savedPosting = postingRepository.save(posting);

        publisher.publishEvent(new ResourceCreatedEvent(this,response, savedPosting.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPosting);
    }
}
