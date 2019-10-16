package com.jarqprog.artGallery.springData.exceptions;

import java.time.LocalDateTime;

public interface ExceptionInfo {

    String getUuid();
    long getHttpStatus();
    LocalDateTime getDateTime();
    String getMessage();
}
