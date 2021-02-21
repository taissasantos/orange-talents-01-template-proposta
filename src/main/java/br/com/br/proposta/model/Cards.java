package br.com.br.proposta.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import br.com.br.proposta.request.AvisoViagemRequest;

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
	
	@OneToMany(cascade = CascadeType.ALL)
    private List<AvisoViagem> viagens = new ArrayList<>();

	@OneToMany(mappedBy = "cartao")
	private List<Bloqueio> bloqueio;

	@Enumerated(EnumType.STRING)
	private StatusCartao status;

	

	@Deprecated
	public Cards() {}

	public Cards(@NotBlank String titular, String identificadorCartao, Proposta proposta) {
		super();
		this.titular = titular;
		this.identificadorCartao = identificadorCartao;
		this.proposta = proposta;
	}

	public void associaBiometria(String biometria) {

		this.biometrias.add(new Biometria(biometria));
	}

	public void bloquerCartao() {
		this.status = StatusCartao.BLOQUEADO;
		
	}
	
	public void addNovaViagem(AvisoViagem avisoViagem) {
        this.viagens.add(avisoViagem);
    }
	
	/*
	 * public void addNovaViagem(AvisoViagemRequest request, Map<String, String>
	 * requestHeaders) { viagens.add(new AvisoViagem(request.destino,
	 * request.terminoViagem, requestHeaders.get(HttpServletService.IP),
	 * requestHeaders.get(HttpServletService.USER_AGENT), this)); }
	 */

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

	public List<Biometria> getBiometrias() {
		return biometrias;
	}

	public StatusCartao getStatus() {
		return status;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}


}
