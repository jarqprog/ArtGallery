package com.jarqprog.commonapi.components;

import com.jarqprog.commondomain.DTO;

import java.util.Date;

public interface HttpExceptionDTO extends DTO {

    String uuid();
    long httpStatus();
    Date date();
    String message();
}
