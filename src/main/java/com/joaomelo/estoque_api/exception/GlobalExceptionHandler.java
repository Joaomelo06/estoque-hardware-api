package com.joaomelo.estoque_api.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StandardError> 
	handleRuntimeException(RuntimeException e,
			HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST; // HTTP 400
		
		StandardError err = new StandardError(
				Instant.now(),
				status.value(),
				"Regra de Negócio Violada / Recurso Não Encontrado",
			e.getMessage(),
			request.getRequestURI()
			);
		
		return ResponseEntity.status(status).body(err);
	}
}
