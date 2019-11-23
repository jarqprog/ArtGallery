package com.jarqprog.commonapi.exceptions.exceptionInfo;

import java.time.LocalDateTime;

public interface ExceptionInfo {

    String getUuid();
    long getHttpStatus();
    LocalDateTime getDateTime();
    String getMessage();
}
