package com.jarqprog.artGallery.domain.helper.implementation;

import com.jarqprog.artGallery.domain.entity.DomainEntity;
import com.jarqprog.artGallery.domain.dto.DTO;
import com.jarqprog.artGallery.domain.helper.DtoEntityConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoEntityConverterImpl implements DtoEntityConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoEntityConverterImpl(ModelMapper modelMapper) {
        assert modelMapper != null;
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
}
