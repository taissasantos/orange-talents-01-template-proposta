package br.com.br.proposta.webservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.br.proposta.request.StatusPropostaRequest;

@FeignClient(url = "http://localhost:9999", name = "status")
public interface StatusPropostaWebRest {

	@PostMapping("/api/solicitacao")
		SolicitacaoAnaliseFinanceira buscaStatus(@RequestBody StatusPropostaRequest status );

	

}
