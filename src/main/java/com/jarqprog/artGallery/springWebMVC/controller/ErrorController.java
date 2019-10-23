package com.jarqprog.artGallery.springWebMVC.controller;

import com.jarqprog.artGallery.springData.exceptions.HttpExceptionInfo;
import com.jarqprog.artGallery.springData.exceptions.ResourceNotFoundException;
import com.jarqprog.artGallery.springData.exceptions.implementation.HttpExceptionInfoImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    //todo: need to implement this for varied exception and HttpStatuses - at the moment it is general solution

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleConflict(final Exception exception, final Model model) {
        handleExceptionInfo("Something gone wrong", exception, model, HttpStatus.INTERNAL_SERVER_ERROR);
        return "/error/error";
    }

    private void handleExceptionInfo(String message, final Exception exception,
                                     final Model model, HttpStatus httpStatus) {

        HttpExceptionInfo httpExceptionInfo = new HttpExceptionInfoImpl(httpStatus, message, exception);
        model.addAttribute("clientInfo", httpExceptionInfo);
        logger.error(String.format("UUID: %s - Exception in controller layer", httpExceptionInfo.getUuid()), exception);
    }

    //use this one for REST
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<HttpExceptionInfo> customHandleNotFound(Exception ex, WebRequest request) {
//        HttpExceptionInfo httpExceptionInfo = new HttpExceptionInfoImpl(httpStatus, message, exception);
//        model.addAttribute("exceptionInfo", httpExceptionInfo);
//        logger.error(String.format("UUID: %s - Exception in controller layer", httpExceptionInfo.getUuid()), exception);
//
//        return new ResponseEntity<>(exceptionInfo, HttpStatus.NOT_FOUND);
//    }
}
