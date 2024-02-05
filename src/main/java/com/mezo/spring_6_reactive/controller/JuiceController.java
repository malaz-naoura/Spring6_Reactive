package com.mezo.spring_6_reactive.controller;

import com.mezo.spring_6_reactive.domain.Juice;
import com.mezo.spring_6_reactive.model.JuiceDTO;
import com.mezo.spring_6_reactive.services.JuiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/juices")
@RequiredArgsConstructor
public class JuiceController {

    public static final String JUICE_PATH = "/api/v2/juices";
    public static final String JUICE_PATH_ID = "/{juiceId}";

    private final JuiceService juiceService;

    @GetMapping
    Flux<JuiceDTO> listJuices() {
        return juiceService.listJuices();
    }

    @GetMapping(JUICE_PATH_ID)
    Mono<JuiceDTO> getById(@PathVariable Integer juiceId) {
        return juiceService.getById(juiceId);
    }

    @PostMapping
    Mono<ResponseEntity<Void>> createNewJuice(@Validated @RequestBody JuiceDTO juiceDTO) {
        return juiceService.saveNewJuice(juiceDTO)
                           .map(savedJuiceDTO -> ResponseEntity.created(UriComponentsBuilder.fromHttpUrl(
                                                                                                    "http://localhost:8080/api/v2/juices/" + savedJuiceDTO.getId()
                                                                                                                                                          .toString())
                                                                                            .build()
                                                                                            .toUri())
                                                               .build());
    }

    @PutMapping(JUICE_PATH_ID)
    Mono<ResponseEntity<Void>> updateJuice(@PathVariable Integer juiceId, @Validated @RequestBody JuiceDTO juiceDTO) {
        return juiceService.updateJuice(juiceId, juiceDTO)
                           .map(updateJuice -> ResponseEntity.noContent()
                                                             .build());
    }

    @PatchMapping(JUICE_PATH_ID)
    Mono<ResponseEntity<Void>> patchJuice(@PathVariable Integer juiceId, @Validated @RequestBody JuiceDTO juiceDTO) {
        return juiceService.patchJuice(juiceId, juiceDTO)
                           .map(patchedJuiceDto -> ResponseEntity.ok()
                                                                 .build());
    }

    @DeleteMapping(JUICE_PATH_ID)
    Mono<ResponseEntity<Void>> deleteJuice(@PathVariable Integer juiceId) {
        return juiceService.deleteJuice(juiceId)
                           .thenReturn(ResponseEntity.noContent()
                                                     .build());
    }


}
