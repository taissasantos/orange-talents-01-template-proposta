package br.com.br.proposta.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssociaCarteiraRequest {
	
	
    private String email;
	
    private String carteira;

	public AssociaCarteiraRequest(CarteiraRequest request) {
		super();
		this.email = request.getEmail();
		this.carteira = request.getNomeCarteira().toString();
	}

    
}
