package com.nic.ev.exception;

import java.io.IOException;
import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import io.jsonwebtoken.InvalidClaimException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleresourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		LOGGER.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusinessException(BusinessException ex, WebRequest request){
		LOGGER.error(ex.getMessage(), ex);
		ErrorDetails errorDetails = new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> globleExcpetionHandler(MultipartException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid Upload Request", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> globleExcpetionHandler(HttpRequestMethodNotSupportedException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "HTTP request method not supported for this operation.", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }
	@ExceptionHandler(IOException.class)
    public ResponseEntity<?> globleExcpetionHandler(IOException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "IO Error: " + ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }
	@ExceptionHandler(InvalidClaimException.class)
    public ResponseEntity<?> globleExcpetionHandler(InvalidClaimException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid Claim Exception: " + ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(Unauthorized.class)
    public ResponseEntity<?> globleExcpetionHandler(Unauthorized ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Unauthorized Exception: "+ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> globleExcpetionHandler(HttpMessageNotReadableException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Malformed JSON request : "+ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> globleExcpetionHandler(MethodArgumentNotValidException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Errors : " +ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> globleExcpetionHandler(MethodArgumentTypeMismatchException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Type Mismatch : " +ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> globleExcpetionHandler(ConstraintViolationException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Constraint Violations : " +ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> globleExcpetionHandler(MissingServletRequestParameterException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Missing Parameters : " +ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> globleExcpetionHandler(HttpMediaTypeNotSupportedException ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Unsupported Media Type : " +ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
	@ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		LOGGER.error(ex.getMessage(), ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}