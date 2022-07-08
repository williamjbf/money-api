package com.github.williamjbf.moneyapi.resource;

import com.github.williamjbf.moneyapi.model.Posting;
import com.github.williamjbf.moneyapi.repository.PostingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postings")
public class PostingResource {

    private final PostingRepository postingRepository;

    public PostingResource(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
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
}
