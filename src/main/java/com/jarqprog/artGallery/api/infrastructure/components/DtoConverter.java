package com.jarqprog.artGallery.api.infrastructure.components;

import com.jarqprog.artGallery.api.domains.DTOFat;
import com.jarqprog.artGallery.api.domains.DomainEntity;
import com.jarqprog.artGallery.domain.DomainModel;

public interface DtoConverter {

    <D extends DomainModel> D convertEntityToModel(DomainEntity domainEntity, Class<D> destinationType);
    <E extends DomainEntity> E convertDTOToEntity(DTOFat domainDto, Class<E> destinationType);
}
