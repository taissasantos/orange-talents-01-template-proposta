package br.com.br.proposta.request;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.br.proposta.model.AvisoViagem;

public class AvisoViagemRequest {
	
	@NotBlank
	public String destino;
	
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	public LocalDate terminoViagem;
	
	private String ipCliente;

    private String userAgent;
    
    @Deprecated
    public AvisoViagemRequest() {}

	public AvisoViagemRequest(@NotBlank String destino, @Future LocalDate terminoViagem) {
		super();
		this.destino = destino;
		this.terminoViagem = terminoViagem;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getTerminoViagem() {
		return terminoViagem;
	}

	public AvisoViagem toModel(HttpServletRequest request) {
		this.ipCliente = request.getRemoteAddr();
        this.userAgent = request.getHeader("User-Agent");
		return new AvisoViagem(destino, terminoViagem, ipCliente,userAgent );
	}


	

}
