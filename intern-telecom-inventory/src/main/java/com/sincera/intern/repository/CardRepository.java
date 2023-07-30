package com.sincera.intern.repository;

import com.sincera.intern.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {
}
