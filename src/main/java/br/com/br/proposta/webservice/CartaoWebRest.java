package br.com.br.proposta.webservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.br.proposta.request.AvisoViagemRequest;
import br.com.br.proposta.request.DadosCardsFormRequest;

@FeignClient(url = "http://localhost:8888", name = "cartao")
public interface CartaoWebRest {

	@GetMapping("/api/cartoes/{id}")
	DadosCardsResponse solicitacaoCartao(@RequestParam Long idProposta);

	@PostMapping("/api/cartoes") 
	DadosCardsResponse enviaDadosProposta(DadosCardsFormRequest request);

	@PostMapping(value = "/api/cartoes/{id}/bloqueios")
	StatusCartaoResponse bloqueiaCartao(@PathVariable("id") String id, @RequestBody SolicitacaoBloqueio solicitacaoBloqueio);
	
	@PostMapping(value = "/api/cartoes/{id}/avisos")
    ResultadoAvisoViagem avisaViagem(@PathVariable("id") String id, @RequestBody AvisoViagemRequest response);

}
