package br.com.seg.service.exception;

import java.util.Date;

/*
 * 1  - Estrutura para retorno na tela do postman
 */
public class ResponseException {
	private Date timestamp;
	private String mensagem;
	private String detalhes;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getDetalhes() {
		return detalhes;
	}
	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	public ResponseException(Date timestamp, String mensagem, String detalhes) {
		super();
		this.timestamp = timestamp;
		this.mensagem = mensagem;
		this.detalhes = detalhes;
	}
	public ResponseException() {
		super();
	}
	
	
}
