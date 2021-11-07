package br.com.codemasters.bluebank.resources.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.codemasters.bluebank.domain.dtos.ResponseError;
import br.com.codemasters.bluebank.domain.dtos.ResponseError.ResponseErrorBuilder;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<ResponseError> notFoundException(HttpServletRequest httpServletRequest, NotFoundException ex) {	
		return builderResponseError(httpServletRequest, ex, "Not found", HttpStatus.NOT_FOUND);					
	}
	
	@ExceptionHandler(FundsNotAcceptException.class)
	protected ResponseEntity<ResponseError> fundsNotAcceptException(HttpServletRequest httpServletRequest, FundsNotAcceptException ex) {	
		return builderResponseError(httpServletRequest, ex, "Funds not accepted", HttpStatus.NOT_FOUND);					
	}
	
	@ExceptionHandler(TransactionException.class)
	protected ResponseEntity<ResponseError> fransactionException(HttpServletRequest httpServletRequest, FundsNotAcceptException ex) {	
		return builderResponseError(httpServletRequest, ex, "Transaction not accepted", HttpStatus.NOT_FOUND);					
	}

	private ResponseEntity<ResponseError> builderResponseError(HttpServletRequest httpServletRequest, RuntimeException ex, String message, HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus)
							.contentType(MediaType.APPLICATION_JSON)
							.body(
								ResponseError.builder()
									.message(message)
									.path(httpServletRequest.getContextPath())
									.time(LocalDateTime.now())
									.exception(ex.getMessage())
									.build()
									);
	}

}
