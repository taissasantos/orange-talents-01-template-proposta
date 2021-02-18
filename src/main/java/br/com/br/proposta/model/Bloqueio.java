package br.com.br.proposta.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Bloqueio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    private LocalDateTime instante = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());

    @NotBlank
    private String ipCliente;

    @NotBlank
    private String userAgent;
    
    @ManyToOne
    private Cards cartao;
    
    @Deprecated
    public Bloqueio(){}

	public Bloqueio(@NotBlank String ipCliente, @NotBlank String userAgent, Cards cartao) {
		super();
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}

	
    
    
    
    

}
