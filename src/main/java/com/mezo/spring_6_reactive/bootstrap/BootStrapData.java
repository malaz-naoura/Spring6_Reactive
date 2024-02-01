package com.mezo.spring_6_reactive.bootstrap;

import com.mezo.spring_6_reactive.domain.Juice;
import com.mezo.spring_6_reactive.repository.JuiceRepo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    final JuiceRepo juiceRepo;


    @Override
    public void run(String... args) {
        juiceRepo.count()
                 .subscribe(count -> {
                     if (count == 0) {
                         loadJuiceData();
                     }
                 });

        juiceRepo.count()
                 .subscribe(System.out::println);

    }

    private void loadJuiceData() {

        Juice beer1 = Juice.builder()
                           .name("Galaxy Cat")
                           .style("STRAWBERRY")
                           .upc("12356")
                           .price(new BigDecimal("12.99"))
                           .quantityOnHand(122)
                           .createdDate(LocalDateTime.now())
                           .lastModifiedDate(LocalDateTime.now())
                           .build();

        Juice beer2 = Juice.builder()
                           .name("Crank")
                           .style("LEMON")
                           .upc("12356222")
                           .price(new BigDecimal("11.99"))
                           .quantityOnHand(392)
                           .createdDate(LocalDateTime.now())
                           .lastModifiedDate(LocalDateTime.now())
                           .build();

        Juice beer3 = Juice.builder()
                           .name("Sunshine City")
                           .style("ORANGE")
                           .upc("12356")
                           .price(new BigDecimal("13.99"))
                           .quantityOnHand(144)
                           .createdDate(LocalDateTime.now())
                           .lastModifiedDate(LocalDateTime.now())
                           .build();

        juiceRepo.save(beer1)
                 .subscribe();
        juiceRepo.save(beer2)
                 .subscribe();
        juiceRepo.save(beer3)
                 .subscribe();

//        for(Integer i=0;i<100;i++) {
//            Juice rand = Juice.builder()
//                              .name(RandomStringUtils.randomAlphanumeric(10))
//                              .build();
//            juiceRepo.save(rand).subscribe();
//        }


    }
}
