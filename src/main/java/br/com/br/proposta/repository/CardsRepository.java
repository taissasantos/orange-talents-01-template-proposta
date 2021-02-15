package br.com.br.proposta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.br.proposta.model.Cards;

public interface CardsRepository extends JpaRepository<Cards, Long> {

}
