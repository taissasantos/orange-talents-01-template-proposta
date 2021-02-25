package br.com.br.proposta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.br.proposta.model.Proposta;
import br.com.br.proposta.model.enums.StatusAvaliacao;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
	
	@Query("select p from Proposta p LEFT JOIN p.cartao c where p.status = 'ELEGIVEL' and c is null")
	List<Proposta> findAllElegiveisSemCartao();

	public Optional<Proposta> findByDocumento(String documento);
	


}
