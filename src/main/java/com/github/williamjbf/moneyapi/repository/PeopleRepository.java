package com.github.williamjbf.moneyapi.repository;

import com.github.williamjbf.moneyapi.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
}
