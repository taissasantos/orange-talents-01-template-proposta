package br.com.br.proposta.request;

import br.com.br.proposta.model.Proposta;

public class DadosCardsFormRequest {
	
	private String documento;
    private String nome;
    private String idProposta;
    
    public DadosCardsFormRequest() {}
    
    public DadosCardsFormRequest(Proposta proposta) {
    	this.documento = proposta.getDocumento();
    	this.nome = proposta.getNome();
    	this.idProposta = proposta.getId().toString();
    }
    
    
	public String getDocumento() {
		return documento;
	}
	public String getNome() {
		return nome;
	}
	public String getIdProposta() {
		return idProposta;
	}
    
    

}
