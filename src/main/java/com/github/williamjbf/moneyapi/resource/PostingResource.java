package com.github.williamjbf.moneyapi.resource;

import com.github.williamjbf.moneyapi.model.Posting;
import com.github.williamjbf.moneyapi.repository.PostingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
