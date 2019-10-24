package com.jarqprog.artGallery.api.dataLogic.components.implementation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jarqprog.artGallery.api.dataLogic.components.HttpExceptionInfo;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
public class HttpExceptionInfoImpl implements HttpExceptionInfo {

    private final String uuid;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime dateTime;

    private final long httpStatus;

    private final String message;

    private final String exceptionInfo;

    public HttpExceptionInfoImpl(HttpStatus httpStatus, String message, Exception ex) {
        this.uuid = String.valueOf(UUID.randomUUID());
        this.dateTime = LocalDateTime.now();
        this.httpStatus = httpStatus.value();
        this.message = String.format("%s, exception: %s", message, ex.getClass().getSimpleName());
        this.exceptionInfo = ex.getClass().getSimpleName();
    }
}
