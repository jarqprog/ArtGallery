package com.jarqprog.commonapi.components;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Getter
@ToString
@Accessors(fluent = true)
public class HttpExceptionInfo implements HttpExceptionDTO {

    @Builder(builderMethodName = "with")
    public static HttpExceptionInfo withCodeAndException(@NonNull HttpStatus httpStatus, @NonNull Exception ex) {
        return new HttpExceptionInfo(httpStatus, ex);
    }

    private final String uuid;

    private static final int MAX_MESSAGE_LEN = 30;

    private final LocalDateTime dateTime;

    private final long httpStatus;

    private final String message;

    private HttpExceptionInfo(HttpStatus httpStatus, Exception ex) {
        this.uuid = String.valueOf(UUID.randomUUID());
        this.dateTime = LocalDateTime.now();
        this.httpStatus = httpStatus.value();
        this.message = String.format("Exception occurred [%s] with message: [%s]", ex.getClass().getSimpleName(), ex.getMessage());
    }
}
