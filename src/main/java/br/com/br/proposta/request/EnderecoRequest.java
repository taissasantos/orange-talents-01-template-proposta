package br.com.br.proposta.request;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

	@NotBlank
	private String logradouro;
	@NotBlank
	private String cep;
	@NotBlank
	private String numero;
	@NotBlank
	private String complemento;
	
	@Deprecated
	private EnderecoRequest() {}
	
	
	
	public EnderecoRequest(@NotBlank String logradouro, @NotBlank String cep, @NotBlank String numero,
			@NotBlank String complemento) {
		super();
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}



	public String getLogradouro() {
		return logradouro;
	}
	public String getCep() {
		return cep;
	}
	public String getNumero() {
		return numero;
	}
	public String getComplemento() {
		return complemento;
	}
	
	
}
