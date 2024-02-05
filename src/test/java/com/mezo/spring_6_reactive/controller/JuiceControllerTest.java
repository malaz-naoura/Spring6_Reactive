package com.mezo.spring_6_reactive.controller;

import com.mezo.spring_6_reactive.domain.Juice;
import com.mezo.spring_6_reactive.model.JuiceDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JuiceControllerTest {

    @Autowired
    WebTestClient webTestClient;

    Juice dumpjuice = Juice.builder()
                           .name("jucietest")
                           .price(BigDecimal.ONE)
                           .upc("upcccs")
                           .build();


    @Test
    @Order(10)
    void listJuices() {

        webTestClient.get()
                     .uri(JuiceController.JUICE_PATH)
                     .exchange()
                     .expectStatus()
                     .isOk()
                     .expectHeader()
                     .contentType("application/json")
                     .expectBody()
                     .jsonPath("$.size()")
                     .isEqualTo(3);
    }

    @Test
    @Order(10)
    void getById() {
        webTestClient.get()
                     .uri(JuiceController.JUICE_PATH + JuiceController.JUICE_PATH_ID, 1)
                     .exchange()
                     .expectStatus()
                     .isOk()
                     .expectHeader()
                     .contentType("application/json")
                     .expectBody(
                             JuiceDTO.class);
    }

    @Test
    @Order(10)
    void getByIdNotFound() {
        webTestClient.get()
                     .uri(JuiceController.JUICE_PATH + JuiceController.JUICE_PATH_ID, 999)
                     .exchange()
                     .expectStatus()
                     .isNotFound();
    }

    @Test
    @Order(20)
    void testCreateJuice() {
        webTestClient.post()
                     .uri(JuiceController.JUICE_PATH)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(Mono.just(dumpjuice), JuiceDTO.class)
                     .exchange()
                     .expectStatus()
                     .isCreated()
                     .expectHeader()
                     .location("http://localhost:8080/api/v2/juices/4");
    }

    @Test
    @Order(20)
    void testCreateJuiceWithBadData() {
        webTestClient.post()
                     .uri(JuiceController.JUICE_PATH)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(Mono.just(Juice.builder()
                                          .build()), JuiceDTO.class)
                     .exchange()
                     .expectStatus()
                     .isBadRequest();
    }

    @Test
    @Order(30)
    void testUpdateJuice() {

        webTestClient.put()
                     .uri(JuiceController.JUICE_PATH + JuiceController.JUICE_PATH_ID, 1)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(Mono.just(dumpjuice), JuiceDTO.class)
                     .exchange()
                     .expectStatus()
                     .isNoContent();
    }

    @Test
    @Order(30)
    void testUpdateJuiceWithBadRequest() {

        webTestClient.put()
                     .uri(JuiceController.JUICE_PATH + JuiceController.JUICE_PATH_ID, 1)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(Mono.just(Juice.builder()
                                          .build()), JuiceDTO.class)
                     .exchange()
                     .expectStatus()
                     .isBadRequest();
    }

    @Test
    @Order(30)
    void testUpdateJuiceWithIdNotFound() {

        webTestClient.put()
                     .uri(JuiceController.JUICE_PATH + JuiceController.JUICE_PATH_ID, 999)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(Mono.just(dumpjuice), JuiceDTO.class)
                     .exchange()
                     .expectStatus()
                     .isNotFound();
    }


    @Test
    @Order(40)
    void deleteJuice() {
        webTestClient.delete()
                     .uri(JuiceController.JUICE_PATH + JuiceController.JUICE_PATH_ID, 1)
                     .exchange()
                     .expectStatus()
                     .isNoContent();
    }

    @Test
    @Order(40)
    void deleteJuiceWithNotFoundId() {
        webTestClient.delete()
                     .uri(JuiceController.JUICE_PATH + JuiceController.JUICE_PATH_ID, 999)
                     .exchange()
                     .expectStatus()
                     .isNotFound();
    }


}