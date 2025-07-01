import jakarta.persistence.*;

@Entity
@Table(name = "alas")
public class Ala {

    @Id
    @Column(length = 40, nullable = false)
    private String descricao;

    @Column(length = 50, nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private int andar;

    // Getters e Setters

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }
}
