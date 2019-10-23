package com.jarqprog.artGallery.domain.dto;

import com.jarqprog.artGallery.domain.dto.heavyDto.HeavyDTO;
import com.jarqprog.artGallery.domain.dto.lightDto.LightDTO;
import com.jarqprog.artGallery.domain.entity.DomainEntity;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleDtoConverter implements DtoConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public SimpleDtoConverter(@NonNull ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <D extends DomainEntity> D convertDtoToEntity(DTO dto, Class<D> destinationType) {
        return modelMapper.map(dto, destinationType);
    }

    @Override
    public <D extends DTO> D convertEntityToDto(DomainEntity domainEntity, Class<D> destinationType) {
        return modelMapper.map(domainEntity, destinationType);
    }

    @Override
    public <L extends LightDTO> L convertHeavyToLight(HeavyDTO heavyDTO, Class<L> destinationType) {
        return modelMapper.map(heavyDTO, destinationType);
    }

    @Override
    public <H extends HeavyDTO> H convertLightToHeavy(LightDTO lightDTO, Class<H> heavyDTO) {
        return modelMapper.map(lightDTO, heavyDTO);
    }
}
