package com.jarqprog.artGallery.api.domains.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface HttpExceptionInfo extends Serializable {

    String getUuid();
    long getHttpStatus();
    LocalDateTime getDateTime();
    String getMessage();
}
