package com.jarqprog.artGallery.domain.dto;

import com.jarqprog.artGallery.domain.dto.heavyDto.HeavyDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.LightDTO;
import com.jarqprog.artGallery.domain.entity.DomainEntity;

public interface DtoConverter {

    <D extends DomainEntity> D convertDtoToEntity(DTO dto, Class<D> destinationType);
    <D extends DTO> D convertEntityToDto(DomainEntity domainEntity, Class<D> destinationType);
    <L extends LightDTO> L convertHeavyToLight(HeavyDTO heavyDTO, Class<L> destinationType);
    <H extends HeavyDTO> H convertLightToHeavy(LightDTO lightDTO, Class<H> heavyDTO);

}
