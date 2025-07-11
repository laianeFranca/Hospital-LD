package br.edu.ifrn.hospitalld.persistencia.modelo; // Pacote corrigido

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enfermeiros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Enfermeiro { // A classe é "Enfermeiro", então o arquivo deve ser "Enfermeiro.java"

    @Id
    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "turno", nullable = false, length = 20)
    private String turno;
}