package com.jarqprog.artGallery.helper;

import com.jarqprog.artGallery.domain.DomainEntity;
import com.jarqprog.artGallery.dto.DTO;
import org.springframework.stereotype.Component;

@Component
public interface DtoEntityConverter {

    <D extends DomainEntity> D convertDtoToEntity(DTO dto, Class<D> destinationType);
    <D extends DTO> D convertEntityToDto(DomainEntity domainEntity, Class<D> destinationType);

}
