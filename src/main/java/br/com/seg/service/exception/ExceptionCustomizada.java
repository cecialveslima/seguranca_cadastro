package br.com.seg.service.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestController
@ControllerAdvice
public class ExceptionCustomizada extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)//Classe ResponseException no pacote br.com.seg.service.exception	
	public final ResponseEntity<ResponseException> todasExceptions(Exception ex, WebRequest web){
		
		//Cria instância passando os valores como parâmetro.
		ResponseException exceptionResponse = 
				new ResponseException(new Date(),ex.getMessage(),web.getDescription(false));
	    
		//Retorna exceptionResponse
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	} 
	
	@ExceptionHandler(ObjetoNulo.class) //Classe ResponseException no pacote br.com.seg.service.exception
	public final ResponseEntity<ResponseException> ObjetoNulo(Exception ex, WebRequest web){
		
		//Cria instância passando os valores como parâmetro.
		ResponseException exceptionResponse = 
				new ResponseException(new Date(),ex.getMessage(),web.getDescription(false));
	    
		//Retorna exceptionResponse
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	} 	
	
	@ExceptionHandler(ResourceNotFoundException.class) //Classe ResponseException no pacote br.com.seg.service.exception
	public final ResponseEntity<ResponseException> NotFoundExceptions(Exception ex, WebRequest web){
		
		//Cria instância passando os valores como parâmetro.
		ResponseException exceptionResponse = 
				new ResponseException(new Date(),ex.getMessage(),web.getDescription(false));
	    
		//Retorna exceptionResponse
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	} 	

	@ExceptionHandler(ResourceNotFound.class) //Classe ResponseException no pacote br.com.seg.service.exception
	public final ResponseEntity<ResponseException> ResourceVazio(Exception ex, WebRequest web){
		
		//Cria instância passando os valores como parâmetro.
		ResponseException exceptionResponse = 
				new ResponseException(new Date(),ex.getMessage(),web.getDescription(false));
	    
		//Retorna exceptionResponse
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);
	}
}
