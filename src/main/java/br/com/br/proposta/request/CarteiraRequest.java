package br.com.br.proposta.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.br.proposta.model.enums.NomeCarteiraEnum;

public class CarteiraRequest {
	
	@Email
	public String email;
		
	public NomeCarteiraEnum nomeCarteira;
	
	@Deprecated
	public CarteiraRequest() {}

	public CarteiraRequest(@Email String email, NomeCarteiraEnum nomeCarteira) {
		super();
		this.email = email;
		this.nomeCarteira = nomeCarteira;
	}

	public String getEmail() {
		return email;
	}

	public NomeCarteiraEnum getNomeCarteira() {
		return nomeCarteira;
	}
	
	

}
