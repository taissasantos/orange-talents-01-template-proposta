package br.com.br.proposta.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.br.proposta.model.Cards;
import br.com.br.proposta.model.Carteira;
import br.com.br.proposta.repository.CardsRepository;
import br.com.br.proposta.repository.CarteiraRepository;
import br.com.br.proposta.request.AssociaCarteiraRequest;
import br.com.br.proposta.request.CarteiraRequest;
import br.com.br.proposta.webservice.CartaoWebRest;

@RestController
public class CarteiraController {
	
	private final Logger logger = LoggerFactory.getLogger(CarteiraController.class);

	@Autowired
	CardsRepository cartaoRepository;

	@Autowired
	CarteiraRepository carteiraRepository;

	@Autowired
	CartaoWebRest cartaoWebRest;


	@PostMapping("/cartoes/{id}/carteiras")
	public ResponseEntity<?> associaCarteira(@PathVariable Long id, @RequestBody @Valid CarteiraRequest request,
			UriComponentsBuilder uriBuilder){

		Optional<Cards> cards = cartaoRepository.findById(id);

		logger.info("Buscando cartão", id);
		if(cards.isEmpty()) {
			logger.error("Cartão não encontrado", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
		}

		Cards cartao = cards.get();
		
		try {
			logger.info("Recebendo solicitação de carteira");
			Optional<Carteira> buscaCarteira = carteiraRepository.findByCarteiraAndCartao(request.nomeCarteira, cartao);
			if(buscaCarteira.isPresent()) {
				logger.error("Cartão já possui carteira associada", id);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cartão já está associado a carteira");
			}
			logger.info("Associando cartão á carteira solicitada", id);
			cartaoWebRest.associaCarteira(cartao.getIdentificadorCartao(), new AssociaCarteiraRequest(request));
			Carteira carteira = carteiraRepository.save(new Carteira(cartao, request.getEmail(), request.getNomeCarteira()));
			cartao.addNovaCarteira(carteira);

			URI location = uriBuilder.path("/cartoes/{id}/carteiras/{id}").build(cartao.getId(), carteira.getId());
			logger.info("Sucesso na operação", id);
			return ResponseEntity.status(HttpStatus.CREATED).body(location);

		} catch (Exception e) {
			logger.info("Erro tentando associar cartão");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Não foi possível associar cartão a carteira solicitada");
		}

		

	}

}
