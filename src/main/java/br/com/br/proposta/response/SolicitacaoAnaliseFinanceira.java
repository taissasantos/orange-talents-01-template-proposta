package br.com.br.proposta.response;

import br.com.br.proposta.model.enums.StatusEnum;

public class SolicitacaoAnaliseFinanceira {
	
	private String documento;
    private String nome;
    private StatusEnum resultadoSolicitacao;
    private String idProposta;
    
    @Deprecated
    public SolicitacaoAnaliseFinanceira(){
    }

	public SolicitacaoAnaliseFinanceira(String documento, String nome, StatusEnum resultadoSolicitacao,
			String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public StatusEnum getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public void setResultadoSolicitacao(StatusEnum resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public String getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(String idProposta) {
		this.idProposta = idProposta;
	}

    
}
