package com.jarqprog.commonapi.components;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jarqprog.commonapi.constants.ApiConstants;
import com.jarqprog.commondomain.DTO;

import java.time.LocalDateTime;

public interface HttpExceptionDTO extends DTO {

    @JsonSerialize
    String uuid();

    @JsonSerialize
    long httpStatus();


    @JsonFormat(pattern = ApiConstants.JSON_LOCAL_DATE_TIME_FORMAT)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime dateTime();

    @JsonSerialize
    String message();
}
