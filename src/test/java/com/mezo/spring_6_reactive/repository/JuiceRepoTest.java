package com.mezo.spring_6_reactive.repository;

import com.mezo.spring_6_reactive.config.DatabaseConfig;
import com.mezo.spring_6_reactive.domain.Juice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class JuiceRepoTest {

    @Autowired
    JuiceRepo juiceRepo;

    @Test
    void saveJuice() {
        juiceRepo.save(Juice.builder()
                            .name("juice1")
                            .build())
                 .subscribe(System.out::println);
    }
}