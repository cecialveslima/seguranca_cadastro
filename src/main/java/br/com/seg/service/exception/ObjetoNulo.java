package br.com.seg.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjetoNulo extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ObjetoNulo(String ex) {
		super(ex);
	}
 
	public ObjetoNulo() {
		super("Objeto nulo não permitido");
	}
}
