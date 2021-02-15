package br.com.br.proposta.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.br.proposta.model.Proposta;
import br.com.br.proposta.repository.PropostaRepository;
import br.com.br.proposta.response.PropostaResponse;

@RestController
public class AcompanhaProposta {

	private final Logger logger = LoggerFactory.getLogger(AcompanhaProposta.class);

	@Autowired
	PropostaRepository repository;

	@GetMapping("/proposta/{id}")
	public ResponseEntity<?> buscaProposta(@PathVariable("id") Long id){

		logger.info("Buscando proposta solicitada");

		Optional<Proposta> proposta = repository.findById(id);

		if(proposta.isPresent()) {

			logger.info("Proposta encontrada" );

			PropostaResponse response = new PropostaResponse(proposta);

			return ResponseEntity.status(HttpStatus.OK).body(response);

		}
		logger.error("Não foi encontrado proposta para o id solicitado");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requisição não encontrada");
	}









}
