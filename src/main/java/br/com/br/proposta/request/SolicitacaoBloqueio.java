package br.com.br.proposta.request;

public class SolicitacaoBloqueio {

	private String sistemaResponsavel;
	
	@Deprecated
	public SolicitacaoBloqueio() {}

	public SolicitacaoBloqueio(String sistemaResponsavel) {
		super();
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	public void setSistemaResponsavel(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}
	
	
}
