package br.com.br.proposta.controller.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.br.proposta.model.Proposta;
import br.com.br.proposta.model.StatusAvaliacao;
import br.com.br.proposta.repository.PropostaRepository;
import br.com.br.proposta.request.NovaPropostaRequest;
import br.com.br.proposta.request.StatusPropostaRequest;
import br.com.br.proposta.webservice.SolicitacaoAnaliseFinanceira;
import br.com.br.proposta.webservice.StatusPropostaWebRest;

@Service
@Transactional
public class PropostaService {

	private final Logger logger = LoggerFactory.getLogger(PropostaService.class);

	@Autowired
	PropostaRepository repository;

	@Autowired
	StatusPropostaWebRest status;

	public Proposta validaProposta(NovaPropostaRequest novaPropostaRequest ) {

		Proposta proposta = novaPropostaRequest.toModel();

		StatusPropostaRequest obj = new StatusPropostaRequest(proposta);

		try {
			SolicitacaoAnaliseFinanceira resultadoAnalise = status.buscaStatus(obj);
			StatusAvaliacao propostaStatus = resultadoAnalise.getResultadoSolicitacao().toPropostaStatus();
			proposta.setStatus(propostaStatus);

			logger.info("Status recebido e salvo com sucesso", proposta.getStatus());

		} catch (Exception e) {
			// TODO: handle exception
		}

		repository.save(proposta);
		logger.info("Proposta com status verificado, salvo", proposta);
		return proposta;


	}

}
