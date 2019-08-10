package com.jarqprog.artGallery.exception;

import java.time.LocalDateTime;

public interface ExceptionInfo {

    String getUuid();
    long getHttpStatus();
    LocalDateTime getDateTime();
    String getMessage();
}
