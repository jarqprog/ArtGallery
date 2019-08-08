package com.jarqprog.artGallery.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class SimpleExceptionInfo implements ExceptionInfo {

    //todo: need to recognize failures on client side and inform

    public static ExceptionInfo getClientInfo(long httpStatus, Exception exception) {
        String uuid = UUID.randomUUID().toString().replace ("-", "");
        LocalDateTime date = LocalDateTime.now();
        String message = String.format("Problem occurred: UID=%s, Date=%s, Status=%s, Exception=%s",
                uuid, date, httpStatus, exception.getClass().getSimpleName());
        return new SimpleExceptionInfo(uuid, date, httpStatus, message);
    }

    public static ExceptionInfo getLogInfo(ExceptionInfo exceptionInfo, Exception exception) {
        String newMessage = String.format("%s - %s", exceptionInfo.getMessage(), exception.getMessage());
        exceptionInfo.setMessage(newMessage);
        return exceptionInfo;
    }

    private final String uuid;
    private final LocalDateTime dateTime;
    private final long httpStatus;
    @Setter private String message;

    private SimpleExceptionInfo(String uuid, LocalDateTime dateTime, long httpStatus, String message) {
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
