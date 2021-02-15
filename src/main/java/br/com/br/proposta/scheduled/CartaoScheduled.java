package br.com.br.proposta.scheduled;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.br.proposta.model.Proposta;
import br.com.br.proposta.model.StatusAvaliacao;
import br.com.br.proposta.repository.PropostaRepository;
import br.com.br.proposta.request.DadosCardsFormRequest;
import br.com.br.proposta.webservice.CartaoWebRest;
import br.com.br.proposta.webservice.DadosCardsResponse;
import feign.FeignException;

@Component
@EnableScheduling
public class CartaoScheduled {

	private static final Logger log = LoggerFactory.getLogger(CartaoScheduled.class);

	@Autowired
	PropostaRepository repository;

	@Autowired
	CartaoWebRest cartao;

	Proposta proposta;

	@Autowired
	CartaoWebRest cartaoWebRest;

	@Scheduled(fixedDelay = 1000) //1min 20000 - 10min 600000milli
	@Transactional
	public void buscaNumeroCartao() throws Exception{

		List<Proposta> propostas = repository.findAllElegiveisSemCartao();


		try {

			for( Proposta proposta: propostas) {
				DadosCardsResponse dados = cartao.enviaDadosProposta(new DadosCardsFormRequest(proposta));

				proposta.associaCartao(dados);

				proposta.setStatus(StatusAvaliacao.CONCLUIDA);

				repository.save(proposta);
			}


		} catch (FeignException ex){
			log.info("Não encontrou um cartão para a proposta: "+proposta.getId());
		}




	}




}
