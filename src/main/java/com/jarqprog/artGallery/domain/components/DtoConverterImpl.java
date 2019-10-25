package com.jarqprog.artGallery.domain.components;

import com.jarqprog.artGallery.domain.dto.DTO;
import com.jarqprog.artGallery.domain.dto.DomainDTO;
import com.jarqprog.artGallery.domain.dto.fatDTO.DTOFat;
import com.jarqprog.artGallery.domain.dto.thinDTO.DTOThin;
import com.jarqprog.artGallery.domain.entity.DomainEntity;
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
    public <D extends DTO> D convertEntityToDTO(DomainEntity domainEntity, Class<D> destinationType) {
        return modelMapper.map(domainEntity, destinationType);
    }

    @Override
    public <E extends DomainEntity> E convertDTOToEntity(DTOFat domainDto, Class<E> destinationType) {
        return modelMapper.map(domainDto, destinationType);
    }

//    @Override
//    public <L extends DTOThin> L convertFatToThin(DTOFat dtoFull, Class<L> destinationType) {
//        return modelMapper.map(dtoFull, destinationType);
//    }

}
