package br.com.br.proposta.webservice;

import javax.validation.constraints.NotBlank;

public class StatusCartaoResponse {

	@NotBlank
	private String resultado;
	
	@Deprecated
	public StatusCartaoResponse() {}

	public StatusCartaoResponse(@NotBlank String resultado) {
		super();
		this.resultado = resultado;
	}

	public String getResultado() {
		return resultado;
	}

	
	

}
