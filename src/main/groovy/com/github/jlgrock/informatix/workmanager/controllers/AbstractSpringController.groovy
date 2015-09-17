package com.github.jlgrock.informatix.workmanager.controllers

import com.github.jlgrock.informatix.workmanager.exceptions.WebException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 *
 */
class AbstractSpringController {
    @ExceptionHandler(WebException.class)
    protected ResponseEntity<? extends ErrorResponse> processWebException(WebException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.message, e.httpStatus);
        LOGGER.debug(errorResponse.toString())
        return new ResponseEntity<ErrorResponse>(errorResponse, errorResponse.httpStatus);
    }
}
