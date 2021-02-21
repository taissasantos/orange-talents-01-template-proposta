package br.com.br.proposta.request;

import javax.validation.constraints.NotEmpty;


import br.com.br.proposta.model.Biometria;
import br.com.br.proposta.validator.Base64;

public class NovaBiometriaRequest {


	@NotEmpty
	@Base64(domainClass = Biometria.class, fieldName = "biometria", message = "alguns caracteres podem estar inválidos")
	private String biometria;


	@Deprecated
	private NovaBiometriaRequest() {}


	public NovaBiometriaRequest(@NotEmpty String biometria) {
		super();
		this.biometria = biometria;
	}



	public String getBiometria() {
		return biometria;
	}




}
