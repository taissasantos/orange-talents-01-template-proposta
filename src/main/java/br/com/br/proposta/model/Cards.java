package br.com.br.proposta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Cards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @NotBlank
    private String titular;

    private String identificadorCartao;

    @OneToOne
    private Proposta proposta;
    
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
    private List<Biometria> biometrias = new ArrayList<>();

	@Deprecated
	public Cards() {}

	public Cards(@NotBlank String titular, String identificadorCartao, Proposta proposta) {
		super();
		this.titular = titular;
		this.identificadorCartao = identificadorCartao;
		this.proposta = proposta;
	}

	public Long getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public String getIdentificadorCartao() {
		return identificadorCartao;
	}

	public Proposta getProposta() {
		return proposta;
	}
	
	public void associaBiometria(String biometria) {
		
        this.biometrias.add(new Biometria(biometria));
    }

	
}
