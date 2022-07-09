package com.github.williamjbf.moneyapi.repository.posting;

import com.github.williamjbf.moneyapi.model.Posting;
import com.github.williamjbf.moneyapi.repository.filter.PostingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostingRepositoryQuery{

    public Page<Posting> filter(PostingFilter postingFilter, Pageable pageable);

}
