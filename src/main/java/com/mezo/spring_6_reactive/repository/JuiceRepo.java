package com.mezo.spring_6_reactive.repository;

import com.mezo.spring_6_reactive.domain.Juice;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface JuiceRepo extends ReactiveCrudRepository<Juice,Integer> {
}
