package br.com.br.proposta.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity
public class AvisoViagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long	id;
	
	@NotBlank
	public String destino;
	
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	public LocalDate terminoViagem;
	
	public LocalDateTime dataAviso = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
	
	
	@NotBlank
    private String ipCliente;

    @NotBlank
    private String userAgent;
    
    @Deprecated
    public AvisoViagem() {}

	public AvisoViagem(@NotBlank String destino, @Future LocalDate terminoViagem, @NotBlank String ipCliente,
			@NotBlank String userAgent) {
		super();
		this.destino = destino;
		this.terminoViagem = terminoViagem;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		
	}

	

	

	public AvisoViagem(@NotBlank String destino, @Future LocalDate terminoViagem) {
		super();
		this.destino = destino;
		this.terminoViagem = terminoViagem;
	}

	public Long getId() {
		return id;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDate getTerminoViagem() {
		return terminoViagem;
	}

	public LocalDateTime getDataAviso() {
		return dataAviso;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}
    
    

}
