package io.lenur.spring.blog.exception;

import io.lenur.spring.blog.dto.ErrorResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandler {
    private static final Logger LOGGER = LogManager.getLogger(ErrorHandler.class);

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthenticationException(AuthenticationException exception) {
        return buildResponse(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception) {
        return buildResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        return buildResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolation(ConstraintViolationException exception) {
        return buildResponse(exception.toString(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponseDto> buildResponse(String message, HttpStatus httpStatus) {
        LOGGER.error(message);

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setMessage(message);
        errorResponse.setCode(httpStatus.value());

        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private ResponseEntity<ErrorResponseDto> buildResponse(Exception exception, HttpStatus httpStatus) {
        String message = exception.getMessage();
        LOGGER.error(message, exception);

        ErrorResponseDto errorResponse = new ErrorResponseDto();
        errorResponse.setMessage(message);
        errorResponse.setCode(httpStatus.value());

        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
