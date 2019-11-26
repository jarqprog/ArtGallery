package com.jarqprog.commonapi.components;

import com.jarqprog.commonapi.absmodel.DomainEntity;
import com.jarqprog.commondomain.Identity;
import lombok.Builder;
import lombok.NonNull;
import org.modelmapper.ModelMapper;

@Builder
public class DtoConverterImpl implements DtoConverter {

    private final ModelMapper modelMapper;

    public DtoConverterImpl(@NonNull ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <D extends Identity> D transformEntityTo(DomainEntity domainEntity, Class<D> destinationType) {
        return modelMapper.map(domainEntity, destinationType);
    }
}
