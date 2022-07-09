package com.github.williamjbf.moneyapi.repository.posting;

import com.github.williamjbf.moneyapi.model.Posting;
import com.github.williamjbf.moneyapi.repository.filter.PostingFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepositoryQuery{

    public List<Posting> filter(PostingFilter postingFilter);

}
