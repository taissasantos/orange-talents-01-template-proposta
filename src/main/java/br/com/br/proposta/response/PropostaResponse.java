package br.com.br.proposta.response;

import java.util.Optional;

import br.com.br.proposta.model.Proposta;
import br.com.br.proposta.model.StatusAvaliacao;

public class PropostaResponse {

	private String documento;
    private String nome;
    private StatusAvaliacao status;
    private Long idProposta;
    
    @Deprecated
    public PropostaResponse() {}

	public PropostaResponse(Optional<Proposta> proposta) {
		if(proposta.isPresent()) {
			this.documento = proposta.get().documento;
			this.nome = proposta.get().nome;
			this.status = proposta.get().getStatus();
			this.idProposta = proposta.get().id;
		}
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public StatusAvaliacao getStatus() {
		return status;
	}

	public Long getIdProposta() {
		return idProposta;
	}
	
	
    
    
}
