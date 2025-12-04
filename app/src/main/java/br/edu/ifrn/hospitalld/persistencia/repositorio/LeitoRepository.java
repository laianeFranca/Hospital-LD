package br.edu.ifrn.hospitalld.persistencia.repositorio;

import br.edu.ifrn.hospitalld.persistencia.modelo.Leito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeitoRepository extends JpaRepository<Leito, Long> {
    
    Optional<Leito> findByDescricao(String descricao);
    
    boolean existsByDescricao(String descricao);
    
    boolean existsByDescricaoAndIdNot(String descricao, Long id);
    
    List<Leito> findByNumeroLeitosGreaterThan(int numero);
}