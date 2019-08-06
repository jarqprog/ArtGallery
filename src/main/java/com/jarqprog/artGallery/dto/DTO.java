package com.jarqprog.artGallery.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class DTO implements Serializable {

    private long id;
    private LocalDateTime createdDate; //todo check if need to expose it
    private LocalDateTime modifiedDate;
    private boolean isRemoved;

}
