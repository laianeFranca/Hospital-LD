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
@Table(name = "leitos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leito {

    @Id
    @Column(length = 20, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private int numero;

    @Column(length = 30, nullable = false)
    private String tipo;

}
