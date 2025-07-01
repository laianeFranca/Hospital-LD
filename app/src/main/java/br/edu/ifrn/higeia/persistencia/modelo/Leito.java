import jakarta.persistence.*;

@Entity
@Table(name = "leitos")
public class Leito {

    @Id
    @Column(length = 20, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private int numero;

    @Column(length = 30, nullable = false)
    private String tipo;

    // Getters e Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
