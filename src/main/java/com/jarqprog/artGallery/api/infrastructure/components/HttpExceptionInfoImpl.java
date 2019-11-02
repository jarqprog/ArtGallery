package com.jarqprog.artGallery.api.infrastructure.components;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
public class HttpExceptionInfoImpl implements HttpExceptionInfo {

    private final String uuid;
    private static final int MAX_MESSAGE_LEN = 30;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime dateTime;

    private final long httpStatus;

    private final String message;

    public HttpExceptionInfoImpl(HttpStatus httpStatus, String message, Exception ex) {
        this.uuid = String.valueOf(UUID.randomUUID());
        this.dateTime = LocalDateTime.now();
        this.httpStatus = httpStatus.value();
        this.message = String.format("Exception: %s occurred:\n\t------> %s", ex.getClass().getSimpleName(), message);
    }
}
