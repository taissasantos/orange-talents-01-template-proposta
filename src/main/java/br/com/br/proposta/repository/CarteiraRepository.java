package br.com.br.proposta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.br.proposta.model.Cards;
import br.com.br.proposta.model.Carteira;
import br.com.br.proposta.model.enums.NomeCarteiraEnum;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
	
	Optional<Carteira> findByCarteiraAndCartao(NomeCarteiraEnum carteira, Cards cartao);

}
