package br.edu.ifrn.hospitalld.persistencia.repositorio;

import br.edu.ifrn.hospitalld.persistencia.modelo.Ala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlaRepository extends JpaRepository<Ala, Long> {
    
    Optional<Ala> findByNome(String nome);
    
    boolean existsByNome(String nome);
    
    boolean existsByNomeAndIdNot(String nome, Long id);
    
    Optional<Ala> findByDescricao(String descricao);
}