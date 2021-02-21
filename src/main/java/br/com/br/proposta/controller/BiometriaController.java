package br.com.br.proposta.controller;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.br.proposta.model.Biometria;
import br.com.br.proposta.model.Cards;
import br.com.br.proposta.request.NovaBiometriaRequest;

@RestController
public class BiometriaController {
	
	private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);
	
	@PersistenceContext
	EntityManager manager;
	
	
	@SuppressWarnings("unused")
	@PostMapping(value = "/api/{id}/biometria")
	@Transactional
	public ResponseEntity<?> cadastraBiometria(@PathVariable Long id, @RequestBody @Valid NovaBiometriaRequest request,
																						UriComponentsBuilder uriBuilder){
		
		logger.info("Buscando número de cartão", id);
		Cards cartao = manager.find(Cards.class, id);
		
		if(cartao == null) {
			logger.error("Número de cartão não encontrado", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Número de cartão não encontrado");
		}
		
		logger.info("Recebendo biometria", request);
		Biometria biometria = new Biometria(cartao, request.getBiometria());
		
		if(biometria == null) {
			logger.error("Biometria inválida", biometria);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Biometria inválida");
		}
		
		
		
		logger.info("Salvando biometria", biometria.getBiometria());
		manager.persist(biometria);
		
		URI location = uriBuilder.path("/cartoes/{id}/biometrias/{idBiometria}").buildAndExpand(cartao.getId(), biometria.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}

}
