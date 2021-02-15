package br.com.br.proposta.customerErros;

import java.time.LocalDateTime;

public class ErrosDTO {
	
	private LocalDateTime instanteErro = LocalDateTime.now();
	
	private String campo;
	
	private String mensagemErro;
	
	private ErrosDTO() {}

	public ErrosDTO(String campo, String mensagemErro) {
		this.campo = campo;
		this.mensagemErro = mensagemErro;
	}

	public LocalDateTime getInstanteErro() {
		return instanteErro;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}
	
	
	
	
}
