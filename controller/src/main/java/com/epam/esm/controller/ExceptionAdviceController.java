package com.epam.esm.controller;

import com.epam.esm.constant.ErrorCode;
import com.epam.esm.constant.LanguagePath;
import com.epam.esm.dto.ExceptionDto;
import com.epam.esm.exception.ControllerException;
import com.epam.esm.exception.ServiceSearchException;
import com.epam.esm.exception.ServiceValidationException;
import com.epam.esm.util.LocaleManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionAdviceController {

    @ExceptionHandler({ServiceSearchException.class})
    public ResponseEntity<ExceptionDto> handleSearchException(ServiceSearchException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), ErrorCode.NOT_FOUND_ERROR_CODE);
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ServiceValidationException.class})
    public ResponseEntity<ExceptionDto> handleValidationException(ServiceValidationException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), ErrorCode.BAD_REQUEST_ERROR_CODE);
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ControllerException.class})
    public ResponseEntity<ExceptionDto> handleValidationException(ControllerException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), ErrorCode.BAD_REQUEST_ERROR_CODE);
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ExceptionDto> handleError(NoHandlerFoundException e) {
        String errorMessage = LocaleManager.getLocalizedMessage(LanguagePath.CONTROLLER_NO_HANDLER);
        ExceptionDto exceptionDto = new ExceptionDto(errorMessage, ErrorCode.NOT_FOUND_ERROR_CODE);
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionDto> handleError(HttpRequestMethodNotSupportedException e) {
        String errorMessage = LocaleManager.getLocalizedMessage(LanguagePath.CONTROLLER_METHOD_NOT_SUPPORTED);
        ExceptionDto exceptionDto = new ExceptionDto(errorMessage, ErrorCode.METHOD_NOT_ALLOWED_ERROR_CODE);
        return new ResponseEntity<>(exceptionDto, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
