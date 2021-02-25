package br.com.br.proposta.model.enums;

public enum StatusEnum {
	
	COM_RESTRICAO {
		@Override
		public StatusAvaliacao toPropostaStatus() {
			return StatusAvaliacao.NAO_ELEGIVEL;
		}
	}, SEM_RESTRICAO {
		@Override
		public StatusAvaliacao toPropostaStatus() {
			return StatusAvaliacao.ELEGIVEL;
		}
	} ;
	
	

	public abstract StatusAvaliacao toPropostaStatus();
	
	

}
