package com.jarqprog.personapi.configuration;

import com.jarqprog.commonapi.components.HttpExceptionDTO;
import com.jarqprog.commonapi.components.HttpExceptionInfo;
import com.jarqprog.commonapi.exceptions.ResourceAlreadyExists;
import com.jarqprog.commonapi.exceptions.ResourceNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity handleResourceNotFoundException(Exception ex, WebRequest request) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceAlreadyExists.class, IllegalArgumentException.class})
    public ResponseEntity<HttpExceptionDTO> handleResourceAlreadyExists(Exception ex, WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<HttpExceptionDTO> handleIncorrectArguments(Exception ex, WebRequest request) {
//        return handleException(ex, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpExceptionDTO> handleInternalException(Exception ex, WebRequest request) {
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<HttpExceptionDTO> handleException(final Exception ex, final HttpStatus httpStatus) {
        var httpExceptionInfo = HttpExceptionInfo.with()
                .ex(ex)
                .httpStatus(httpStatus)
                .build();

        logger.info("UUID: {} - Exception in controller layer", httpExceptionInfo.uuid(), ex);
        return new ResponseEntity<>(httpExceptionInfo, httpStatus);
    }

}
