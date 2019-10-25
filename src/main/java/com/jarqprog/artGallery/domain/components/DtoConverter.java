package com.jarqprog.artGallery.domain.components;

import com.jarqprog.artGallery.domain.dto.DTO;
import com.jarqprog.artGallery.domain.dto.fatDTO.DTOFat;
import com.jarqprog.artGallery.domain.entity.DomainEntity;

public interface DtoConverter {

    <D extends DTO> D convertEntityToDTO(DomainEntity domainEntity, Class<D> destinationType);
    <E extends DomainEntity> E convertDTOToEntity(DTOFat domainDto, Class<E> destinationType);
//    <L extends DTOThin> L convertFatToThin(DTOFat fatDTO, Class<L> destinationType);

}
