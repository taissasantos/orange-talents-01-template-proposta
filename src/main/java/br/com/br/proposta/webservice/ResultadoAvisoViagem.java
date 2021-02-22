package br.com.br.proposta.webservice;

public class ResultadoAvisoViagem {
	
	private ResultadoViagemEnum resultado;
	
	@Deprecated
	public ResultadoAvisoViagem() {}

	public ResultadoAvisoViagem(ResultadoViagemEnum resultado) {
		super();
		this.resultado = resultado;
	}

	public ResultadoViagemEnum getResultado() {
		return resultado;
	}
	
	public boolean estaCriado() {
        return ResultadoViagemEnum.CRIADO.equals(this.resultado);
    }
	
	

	
	
	
	
	

}
