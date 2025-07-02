package br.edu.ifrn.higeia.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ala {

    @Id
    @Column(length = 40, nullable = false)
    private String descricao;

    @Column(length = 50, nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private int andar;

}
