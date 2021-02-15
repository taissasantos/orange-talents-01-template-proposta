package br.com.br.proposta.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Biometria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long	id;
	
	@ManyToOne
	@JoinColumn
	private Cards cartao;
	
	@NotEmpty
	@Column(nullable = false)
	private String biometria;
	
	
	@Column(unique = true , nullable = false)
	public LocalDateTime dataCriacao = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
	
	@Deprecated
	public Biometria() {}

	

	public Biometria(Cards cartao, @NotEmpty String biometria, LocalDateTime dataCriacao) {
		super();
		this.cartao = cartao;
		this.biometria = biometria;
		this.dataCriacao = dataCriacao;
	}


	public Biometria(@NotEmpty String biometria) {
		super();
		this.biometria = biometria;
	}


	public Biometria(Cards cartao, @NotEmpty String biometria) {
		super();
		this.cartao = cartao;
		this.biometria = biometria;
	}



	public Long getId() {
		return id;
	}



	public Cards getCartao() {
		return cartao;
	}



	public String getBiometria() {
		return biometria;
	}



	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	
	

}
