package com.jarqprog.artGallery.helper;

import com.jarqprog.artGallery.domain.DomainEntity;
import com.jarqprog.artGallery.dto.DTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleDtoEntityConverter implements DtoEntityConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public <D extends DomainEntity> D convertDtoToEntity(DTO dto, Class<D> destinationType) {
        return modelMapper.map(dto, destinationType);
    }

    @Override
    public <D extends DTO> D convertEntityToDto(DomainEntity domainEntity, Class<D> destinationType) {
        return modelMapper.map(domainEntity, destinationType);
    }
}
