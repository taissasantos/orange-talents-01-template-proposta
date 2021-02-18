package br.com.br.proposta.model;

public enum StatusCartao {
	
	DESBLOQUEADO("Desbloqueado"), BLOQUEADO("Bloqueado");

	private String label;

	private StatusCartao(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	
	

}
