package br.edu.ifrn.hospitalld.persistencia.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.higeia.persistencia.modelo.Ala;

public interface AlaRepository extends JpaRepository<Ala, Long> {

    Optional<Ala> findByDescricao(String descricao);

}