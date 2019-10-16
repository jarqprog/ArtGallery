package com.jarqprog.artGallery.springData.components;

import com.jarqprog.artGallery.domain.entity.DomainEntity;
import com.jarqprog.artGallery.domain.dto.DTO;

public interface DtoEntityConverter {

    <D extends DomainEntity> D convertDtoToEntity(DTO dto, Class<D> destinationType);
    <D extends DTO> D convertEntityToDto(DomainEntity domainEntity, Class<D> destinationType);

}
