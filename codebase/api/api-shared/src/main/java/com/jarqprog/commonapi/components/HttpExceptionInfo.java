package com.jarqprog.commonapi.components;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Value
@Getter
@ToString
@Accessors(fluent = true)
public class HttpExceptionInfo implements HttpExceptionDTO {

    @Builder(builderMethodName = "with")
    public HttpExceptionDTO with(HttpStatus httpStatus, Exception ex) {
        final var message = String.format("Exception occurred - status code [%s]", httpStatus.value());
        return new HttpExceptionInfo(httpStatus, message, ex);
    }

    @Builder(builderMethodName = "withMessage")
    public HttpExceptionDTO withMessage(HttpStatus httpStatus, String message, Exception ex) {
        return new HttpExceptionInfo(httpStatus, message, ex);
    }

    private final String uuid;
    private static final int MAX_MESSAGE_LEN = 30;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final Date date;

    private final long httpStatus;

    private final String message;

    private HttpExceptionInfo(HttpStatus httpStatus, String message, Exception ex) {
        this.uuid = String.valueOf(UUID.randomUUID());
        this.date = Timestamp.from(Instant.now());
        this.httpStatus = httpStatus.value();
        this.message = String.format("Exception: %s occurred: ------> %s", ex.getClass().getSimpleName(), message);
    }
}
