package com.jarqprog.artGallery.api.infrastructure.components;

import com.jarqprog.artGallery.api.domains.DomainEntity;
import com.jarqprog.artGallery.domain.Identity;
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
    public <D extends Identity> D transformEntityTo(DomainEntity domainEntity, Class<D> destinationType) {
        return modelMapper.map(domainEntity, destinationType);
    }
}
