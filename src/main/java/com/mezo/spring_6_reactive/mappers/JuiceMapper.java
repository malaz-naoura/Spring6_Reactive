package com.mezo.spring_6_reactive.mappers;

import com.mezo.spring_6_reactive.domain.Juice;
import com.mezo.spring_6_reactive.model.JuiceDTO;
import com.sun.tools.javac.Main;
import org.mapstruct.Mapper;

@Mapper
public interface JuiceMapper extends MapperMain<Juice, JuiceDTO> {


}
