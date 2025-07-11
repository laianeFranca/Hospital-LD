package br.edu.ifrn.hospitalld.persistencia.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.hospitalld.persistencia.modelo.Ala;

// A correção está aqui: JpaRepository<Ala, String> em vez de JpaRepository<Ala, Long>
public interface AlaRepository extends JpaRepository<Ala, String> {

    Optional<Ala> findByDescricao(String descricao);

}