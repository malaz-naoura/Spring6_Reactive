package com.mezo.spring_6_reactive.services;

import com.mezo.spring_6_reactive.domain.Juice;
import com.mezo.spring_6_reactive.mappers.JuiceMapper;
import com.mezo.spring_6_reactive.model.JuiceDTO;
import com.mezo.spring_6_reactive.repository.JuiceRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class JuiceServiceImpl implements JuiceService {

    private final JuiceRepo juiceRepo;

    private final JuiceMapper juiceMapper;

    @Override
    public Flux<JuiceDTO> listJuices() {
        return juiceRepo.findAll()
                        .map(juiceMapper::objToDto);
    }

    @Override
    public Mono<JuiceDTO> getById(Integer beerId) {
        return juiceRepo.findById(beerId)
                        .map(juiceMapper::objToDto);
    }

    @Override
    public Mono<JuiceDTO> saveNewJuice(JuiceDTO juiceDTO) {
        return juiceRepo.save(juiceMapper.dtoToObj(juiceDTO))
                        .map(juiceMapper::objToDto);
    }

    @Override
    public Mono<JuiceDTO> updateJuice(Integer juiceId, JuiceDTO juiceDTO) {
        juiceDTO.setId(juiceId);
        return juiceRepo.save(juiceMapper.dtoToObj(juiceDTO))
                        .map(juiceMapper::objToDto);
    }

    @Override
    public Mono<JuiceDTO> patchJuice(Integer juiceId, JuiceDTO juiceDTO) {
        return juiceRepo.findById(juiceId)
                        .map(juice -> {
                            if (StringUtils.hasText(juiceDTO.getName())) juice.setName(juiceDTO.getName());
                            if (StringUtils.hasText(juiceDTO.getStyle())) juice.setStyle(juiceDTO.getStyle());
                            if (StringUtils.hasText(juiceDTO.getUpc())) juice.setUpc(juiceDTO.getUpc());
                            if (juiceDTO.getPrice() != null) juice.setPrice(juiceDTO.getPrice());
                            if (juiceDTO.getQuantityOnHand() != null)
                                juice.setQuantityOnHand(juiceDTO.getQuantityOnHand());
                            return juice;
                        })
                        .flatMap(juiceRepo::save)
                        .map(juiceMapper::objToDto);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteJuice(Integer juiceId) {
        return juiceRepo.deleteById(juiceId)
                        .map(unused -> ResponseEntity.noContent()
                                                     .build());
    }


}
