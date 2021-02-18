package br.com.br.proposta.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.br.proposta.model.Bloqueio;
import br.com.br.proposta.model.Cards;
import br.com.br.proposta.webservice.CartaoWebRest;
import br.com.br.proposta.webservice.SolicitacaoBloqueio;
import br.com.br.proposta.webservice.StatusCartaoResponse;
import feign.FeignException;

@RestController
public class CartaoController {
	
	private final Logger logger = LoggerFactory.getLogger(CartaoController.class);

	@PersistenceContext
	EntityManager manager;
	
	@Autowired
	CartaoWebRest cartaoWebRest;

	@PostMapping(value = "/cartoes/{id}/bloqueio")
	@Transactional
	public ResponseEntity<?> bloqueiaCartao(@PathVariable("id") Long id, HttpServletRequest client){
		
		logger.info("Recebendo cartão, e buscando por id" , id);
		Cards cartao = manager.find(Cards.class, id);
		
		if (cartao == null) {
			logger.error("Erro, cartão informado não existe na base de dados", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados do cartão inconsistentes");
		}
		
		logger.info("Pegando os dados da request", client);
		String ipAddress = client.getRemoteAddr();
        String userAgentStr = client.getHeader("User-Agent");
        
        logger.info("Bloqueando o cartão solicitado", id);
		Bloqueio bloqueio = new Bloqueio(ipAddress, userAgentStr, cartao);
		
		logger.info("Verificando o cabeçalho da request", id);
		
		if(ipAddress.isBlank() || userAgentStr.isBlank()){
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Dados da requisição inválidos");
        }
		logger.info("Cartão bloqueado salvo", id);
		manager.persist(bloqueio);
		
		logger.info("Enviando bloqueio para o sistema legado", id);
		try {
			StatusCartaoResponse statusCartao = cartaoWebRest.bloqueiaCartao(cartao.getIdentificadorCartao(), new SolicitacaoBloqueio("Propostas"));
			cartao.bloquerCartao();
			
		} catch (FeignException e) {
			logger.error("Não foi possível enviar dados para o sistema legado", id);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Impossível bloquear cartão, dados inconsistentes");
		}

		logger.info("Status do cartão alterado com sucesso", id);
		manager.persist(cartao);
		return ResponseEntity.ok().build();

	}




}
