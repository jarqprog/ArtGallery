package com.jarqprog.commonapi.components;


import com.jarqprog.commonapi.absmodel.DomainEntity;
import com.jarqprog.commondomain.absmodel.Identity;

public interface DtoConverter {

    <D extends Identity> D transformEntityTo(DomainEntity domainEntity, Class<D> destinationType);

}
