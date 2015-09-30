package com.github.jlgrock.informatix.workmanager.controllers

import com.github.jlgrock.informatix.workmanager.exceptions.WebException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 *
 */
class AbstractSpringController {
    private static Logger LOGGER = LoggerFactory.getLogger(AbstractSpringController.class)

    @ExceptionHandler(WebException.class)
    protected ResponseEntity<? extends ErrorResponse> processWebException(WebException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.message, e.httpStatus);
        LOGGER.debug(errorResponse.toString())
        return new ResponseEntity<ErrorResponse>(errorResponse, errorResponse.httpStatus);
    }
}
