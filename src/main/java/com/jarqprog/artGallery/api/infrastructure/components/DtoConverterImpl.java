package com.jarqprog.artGallery.api.infrastructure.components;

import com.jarqprog.artGallery.api.domains.DTO;
import com.jarqprog.artGallery.api.domains.DTOFat;
import com.jarqprog.artGallery.api.domains.DomainEntity;
import com.jarqprog.artGallery.domain.DomainModel;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoConverterImpl implements DtoConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoConverterImpl(@NonNull ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <D extends DomainModel> D convertEntityToModel(DomainEntity domainEntity, Class<D> destinationType) {
        return modelMapper.map(domainEntity, destinationType);
    }

    @Override
    public <E extends DomainEntity> E convertDTOToEntity(DTOFat domainDto, Class<E> destinationType) {
        return modelMapper.map(domainDto, destinationType);
    }
}
