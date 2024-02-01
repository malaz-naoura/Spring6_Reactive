package com.mezo.spring_6_reactive.mappers;

public interface MapperMain<T,D> {

    T dtoToObj(D dto);

    D objToDto(T obj);
}
