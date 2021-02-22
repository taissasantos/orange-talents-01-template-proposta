package br.com.br.proposta.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Carteira {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	public Cards cartao;
	
	@Email
	public String email;
	
	
	@Enumerated(EnumType.STRING)
	public NomeCarteiraEnum carteira;
	
	@Deprecated
	public Carteira() {}

	public Carteira(Cards cartao, @Email String email, NomeCarteiraEnum carteira) {
		super();
		this.cartao = cartao;
		this.email = email;
		this.carteira = carteira;
	}

	public Long getId() {
		return id;
	}

	public Cards getCartao() {
		return cartao;
	}

	public String getEmail() {
		return email;
	}

	public NomeCarteiraEnum getCarteira() {
		return carteira;
	}
	
	
	
	
}
