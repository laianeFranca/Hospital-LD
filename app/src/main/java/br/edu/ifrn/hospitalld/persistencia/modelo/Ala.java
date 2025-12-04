package br.edu.ifrn.hospitalld.persistencia.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(name = "descricao", length = 40, nullable = false)
    private String descricao;

    @NotBlank(message = "O nome é obrigatório")
    @Column(length = 50, nullable = false, unique = true)
    private String nome;

    @NotNull(message = "O andar é obrigatório")
    @Column(nullable = false)
    private int andar;
}