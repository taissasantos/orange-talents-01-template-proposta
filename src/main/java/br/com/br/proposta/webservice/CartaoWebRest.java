package br.com.br.proposta.webservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.br.proposta.request.DadosCardsFormRequest;

@FeignClient(url = "http://localhost:8888", name = "cartao")
public interface CartaoWebRest {

	@GetMapping("/api/cartoes/{id}")
	DadosCardsResponse solicitacaoCartao(@RequestParam Long idProposta);


	@PostMapping("/api/cartoes") 
	DadosCardsResponse enviaDadosProposta(DadosCardsFormRequest request);

}
