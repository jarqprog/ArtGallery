package com.jarqprog.artGallery.api.infrastructure.components;

import com.jarqprog.artGallery.api.domains.DomainEntity;
import com.jarqprog.artGallery.domain.Identity;

public interface DtoConverter {

    <D extends Identity> D transformEntityTo(DomainEntity domainEntity, Class<D> destinationType);
}
