package br.com.br.proposta.request;

import br.com.br.proposta.model.Proposta;

public class StatusPropostaRequest {
	
	public String documento;
	public String nome;
	public Long idProposta;
	
	@Deprecated
	public StatusPropostaRequest() {}


	public StatusPropostaRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
	}


	public String getDocumento() {
		return documento;
	}


	public String getNome() {
		return nome;
	}


	public Long getIdProposta() {
		return idProposta;
	}


	


	
	
	


}
