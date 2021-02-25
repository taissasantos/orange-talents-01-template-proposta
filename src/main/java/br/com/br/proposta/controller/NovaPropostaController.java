package br.com.br.proposta.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.br.proposta.model.Proposta;
import br.com.br.proposta.model.enums.StatusAvaliacao;
import br.com.br.proposta.repository.PropostaRepository;
import br.com.br.proposta.request.NovaPropostaRequest;
import br.com.br.proposta.request.StatusPropostaRequest;
import br.com.br.proposta.response.SolicitacaoAnaliseFinanceira;
import br.com.br.proposta.webservice.StatusPropostaWebRest;

@RestController
public class NovaPropostaController {

	private final Logger logger = LoggerFactory.getLogger(NovaPropostaController.class);

	@Autowired
	PropostaRepository repository;

	@Autowired
	StatusPropostaWebRest status;


	@PostMapping(value ="/api/proposta")
	public ResponseEntity<?>novaProposta(@RequestBody @Valid NovaPropostaRequest request, 
			UriComponentsBuilder uri){

		Proposta proposta = request.toModel();
		Optional<Proposta> optional = repository.findByDocumento(request.documento);

		logger.info("Iniciando Avalição da proposta", optional);

		if(optional.isPresent()) {
			logger.error("Documento já possui proposta na base de dados", proposta.getDocumento());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		} 

		StatusPropostaRequest obj = new StatusPropostaRequest(proposta);

		try {
			logger.info("Procurando numero de cartão, para a proposta solicitada");
			SolicitacaoAnaliseFinanceira resultadoAnalise = status.buscaStatus(obj);
			StatusAvaliacao propostaStatus = resultadoAnalise.getResultadoSolicitacao().toPropostaStatus();
			proposta.setStatus(propostaStatus);

			logger.info("Status recebido e salvo com sucesso", proposta.getStatus());

			repository.save(proposta);
			logger.info("Proposta sem restrições salva na base de dados", proposta);

			URI location = uri.path("/api/proposta/{id}")
					.buildAndExpand(proposta.getId()).toUri();

			return ResponseEntity.status(HttpStatus.CREATED).body(location);

		} catch (Exception e) {
			logger.error("Número de cartão não encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
		}




	}
}
