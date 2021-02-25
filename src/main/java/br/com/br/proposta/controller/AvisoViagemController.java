package br.com.br.proposta.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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

import br.com.br.proposta.model.AvisoViagem;
import br.com.br.proposta.model.Cards;
import br.com.br.proposta.request.AvisoViagemRequest;
import br.com.br.proposta.response.ResultadoAvisoViagem;
import br.com.br.proposta.webservice.CartaoWebRest;

@RestController
public class AvisoViagemController {

	private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

	@PersistenceContext
	EntityManager manager;

	@Autowired
	CartaoWebRest cartaoWebRest;

	@PostMapping(value = "/api/viagem/{id}")
	@Transactional
	public ResponseEntity<?> avisoViagem(@PathVariable("id") Long id, @RequestBody @Valid AvisoViagemRequest request,
			HttpServletRequest client ){
		
		Cards cartao = manager.find(Cards.class, id);
		logger.info("Verificando dados do cartão", id);
		if (cartao == null) {
			logger.error("Erro, cartão informado não existe na base de dados", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados do cartão inconsistentes");
		}

		try {
			logger.info("Recebendo solicitação de aviso para viagem", id);
			ResultadoAvisoViagem resultado = cartaoWebRest.avisaViagem(cartao.getIdentificadorCartao(), request);
			
			if(resultado.estaCriado()) {
				AvisoViagem viagem = request.toModel(client);
				logger.info("Salvando dados da viagem" , id);
				manager.persist(viagem);
				
				logger.info("Adicionando aviso de viagem, na tabela de cartões", id);
				cartao.addNovaViagem(viagem);
				manager.persist(cartao);
				
				logger.info("Operação concluída, aviso de viagem regristrado", id);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Impossível avisar cartão, dados inconsistentes");
		}

		return ResponseEntity.ok().build();

	}


}
