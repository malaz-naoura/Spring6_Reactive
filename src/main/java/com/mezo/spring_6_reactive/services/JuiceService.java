package com.mezo.spring_6_reactive.services;


import com.mezo.spring_6_reactive.model.JuiceDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JuiceService {

    Flux<JuiceDTO> listJuices();

    Mono<JuiceDTO> getById(Integer beerId);

    Mono<JuiceDTO> saveNewJuice(JuiceDTO juiceDTO);

    Mono<JuiceDTO> updateJuice(Integer juiceId, JuiceDTO juiceDTO);

    Mono<JuiceDTO> patchJuice(Integer juiceId, JuiceDTO juiceDTO);

    Mono<Void> deleteJuice(Integer juiceId);
}
