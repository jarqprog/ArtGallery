package com.jarqprog.artGallery.springWebMVC.controller;

import com.jarqprog.artGallery.exception.ExceptionInfo;
import com.jarqprog.artGallery.exception.SimpleExceptionInfo;
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

        ExceptionInfo clientExceptionInfo = SimpleExceptionInfo
                .getClientInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception);
        model.addAttribute("errorMessage", clientExceptionInfo.getMessage());

        ExceptionInfo logExceptionInfo = SimpleExceptionInfo.getLogInfo(clientExceptionInfo, exception);
        logger.warn(logExceptionInfo.toString());
        return "error";
    }
}
