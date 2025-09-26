package giuseppetuccilli.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "elementi")
@Inheritance(strategy = InheritanceType.JOINED)
public class Elemento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long codISBN;
    protected String titolo;
    protected int annoDiPubblicazione;
    protected int numeroPagine;
    @OneToOne(mappedBy = "elemento")
    private Prestito prestito;

    public Elemento() {
    }

    public Elemento(String titolo, int annoPubl, int pagine) {
        this.titolo = titolo;
        this.annoDiPubblicazione = annoPubl;
        this.numeroPagine = pagine;
    }

    public long getCodISBN() {
        return codISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public Prestito getPrestito() {
        return prestito;
    }

    @Override
    public String toString() {
        return "Elemento{" +
                "codISBN=" + codISBN +
                ", titolo='" + titolo + '\'' +
                ", annoDiPubblicazione=" + annoDiPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
