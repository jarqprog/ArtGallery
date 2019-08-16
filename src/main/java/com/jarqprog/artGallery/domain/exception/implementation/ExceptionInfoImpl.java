package com.jarqprog.artGallery.springWebMVC.helper;

import com.jarqprog.artGallery.domain.exception.ExceptionInfo;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Getter
@ToString
public class MvcExceptionInfo implements ExceptionInfo {

    //todo: need to recognize failures on client side and inform

    public static ExceptionInfo getClientInfo(long httpStatus, Exception exception) {
        String uuid = UUID.randomUUID().toString().replace ("-", "");
        LocalDateTime date = LocalDateTime.now();
        String message = String.format("Problem occurred: UID=%s, Date=%s, Status=%s, Exception=%s",
                uuid, date, httpStatus, exception.getClass().getSimpleName());
        return new MvcExceptionInfo(uuid, date, httpStatus, message);
    }

    public static ExceptionInfo getLogInfo(ExceptionInfo exceptionInfo, Exception exception) {
        String newMessage = String.format("%s - %s *** CAUSE: %s *** STACK TRACE: %s", exceptionInfo.getMessage(),
                exception.getMessage(),
                exception.getCause(),
                Arrays.toString(exception.getStackTrace()));
        return new MvcExceptionInfo(exceptionInfo, newMessage);
    }

    private final String uuid;
    private final LocalDateTime dateTime;
    private final long httpStatus;
    private final String message;

    private MvcExceptionInfo(String uuid, LocalDateTime dateTime, long httpStatus, String message) {
        this.uuid = uuid;
        this.dateTime = dateTime;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private MvcExceptionInfo(ExceptionInfo exceptionInfo, String message) {
        this.uuid = exceptionInfo.getUuid();
        this.dateTime = exceptionInfo.getDateTime();
        this.httpStatus = exceptionInfo.getHttpStatus();
        this.message = message;
    }
}
