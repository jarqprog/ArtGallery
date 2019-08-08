package com.jarqprog.artGallery.config;

import com.jarqprog.artGallery.exception.ExceptionInfo;
import com.jarqprog.artGallery.exception.SimpleExceptionInfo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.log4j.Logger;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = Logger.getLogger(GeneralExceptionHandler.class);

    //todo: need to recognize failures on client side and inform

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request){
        //todo request not used at this moment

        ExceptionInfo clientExceptionInfo = SimpleExceptionInfo
                .getClientInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex);

        ExceptionInfo logExceptionInfo = SimpleExceptionInfo.getLogInfo(clientExceptionInfo, ex);
        logger.warn(logExceptionInfo.toString());

        return new ResponseEntity<>(clientExceptionInfo.getMessage(),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
