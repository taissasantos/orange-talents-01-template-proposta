package br.com.br.proposta.request;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import br.com.br.proposta.model.Proposta;
import br.com.br.proposta.validator.CPFOuCNPJ;

public class NovaPropostaRequest {
	
	@NotBlank(message = "Insira um documento")
	@CPFOuCNPJ(domainClass = Proposta.class , fieldName = "documento")
	public String documento;
	
	@NotBlank(message = "Insira o email")
	@Email
	public String email;
	
	@NotBlank(message = "Insira o nome")
	public String nome;
	
	@NotBlank(message = "Insira o endereço")
	public String endereco;
	
	@Positive(message = "Insira o salário")
	public BigDecimal salario;
	
	public NovaPropostaRequest() {}

	public NovaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive @NotBlank BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
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

	public Proposta toModel() {
		return new Proposta(documento, email, nome, endereco, salario);
	}

	
	
	

}
