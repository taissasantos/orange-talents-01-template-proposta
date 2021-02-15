package br.com.br.proposta.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.br.proposta.webservice.DadosCardsResponse;


@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long	id;

	@NotBlank
	@Column(unique = true, nullable = false)
	public String documento;

	@NotBlank
	@Email
	@Column(nullable = false)
	public String email;

	@NotBlank
	@Column(nullable = false)
	public String nome;

	@NotBlank
	@Column(nullable = false)
	public String endereco;

	@Positive
	@Column(nullable = false)
	public BigDecimal salario;

	@Enumerated(EnumType.STRING)
	private StatusAvaliacao status;

	
	@OneToOne(mappedBy = "proposta", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Cards cartao;
	
	public void associaCartao(DadosCardsResponse response) {
		this.cartao = new Cards(response.getTitular() , response.getId(),  this);
	}

	

	@Deprecated
	public Proposta() {}


	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive BigDecimal salario) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}


	public Long getId() {
		return id;
	}


	public String getDocumento() {
		return documento;
	}


	public String getEmail() {
		return email;
	}


	public String getNome() {
		return nome;
	}


	public String getEndereco() {
		return endereco;
	}


	public BigDecimal getSalario() {
		return salario;
	}


	public StatusAvaliacao getStatus() {
		return status;
	}


	public void setStatus(StatusAvaliacao status) {
		this.status = status;
	}


}
