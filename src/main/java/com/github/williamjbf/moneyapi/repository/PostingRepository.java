package com.github.williamjbf.moneyapi.repository;

import com.github.williamjbf.moneyapi.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingRepository extends JpaRepository<Posting,Long> {
}
