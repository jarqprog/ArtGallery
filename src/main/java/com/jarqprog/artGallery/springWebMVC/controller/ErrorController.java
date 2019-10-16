package com.jarqprog.artGallery.springWebMVC.controller;

import com.jarqprog.artGallery.springData.exceptions.ExceptionInfo;
import com.jarqprog.artGallery.springData.exceptions.implementation.ExceptionInfoImpl;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.log4j.Logger;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    private static final Logger logger = Logger.getLogger(ErrorController.class);

    //todo: need to recognize failures on client side and inform

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleConflict(final Exception exception, final Model model){

        ExceptionInfo clientExceptionInfo = ExceptionInfoImpl
                .getClientInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception);
        model.addAttribute("message", clientExceptionInfo.getMessage());
        model.addAttribute("errorId", clientExceptionInfo.getUuid());
        model.addAttribute("date", clientExceptionInfo.getDateTime());
        model.addAttribute("status", clientExceptionInfo.getHttpStatus());

        ExceptionInfo logExceptionInfo = ExceptionInfoImpl.getLogInfo(clientExceptionInfo, exception);
        logger.warn(logExceptionInfo.toString());
        return "/error/error";
    }
}
