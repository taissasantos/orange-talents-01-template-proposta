package br.com.br.proposta.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssociaCarteiraRequest {
	
	@JsonProperty
    private String email;
	@JsonProperty
    private String carteira;

	public AssociaCarteiraRequest(CarteiraRequest request) {
		super();
		this.email = request.getEmail();
		this.carteira = request.getNomeCarteira().toString();
	}

    
}
