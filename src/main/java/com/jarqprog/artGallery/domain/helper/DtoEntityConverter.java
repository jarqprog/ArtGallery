package com.jarqprog.artGallery.domain.helper;

import com.jarqprog.artGallery.domain.entity.DomainEntity;
import com.jarqprog.artGallery.domain.dto.DTO;
import org.springframework.stereotype.Component;

public interface DtoEntityConverter {

    <D extends DomainEntity> D convertDtoToEntity(DTO dto, Class<D> destinationType);
    <D extends DTO> D convertEntityToDto(DomainEntity domainEntity, Class<D> destinationType);

}
