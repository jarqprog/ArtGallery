package com.jarqprog.artGallery.helper;

public interface DtoEntityMapper {

    <S extends Object> S void addConverter(Class<S> sourceType, Class<D> destinationType);

}
