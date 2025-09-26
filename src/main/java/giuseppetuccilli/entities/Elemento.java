package giuseppetuccilli.entities;

import jakarta.persistence.*;

import java.time.Year;

@Entity
@Table(name = "elementi")
@Inheritance(strategy = InheritanceType.JOINED)
public class Elemento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long codISBN;
    protected String titolo;
    protected Year annoDiPubblicazione;
    protected int numeroPagine;

    public Elemento() {
    }

    public Elemento(String titolo, Year annoPubl, int pagine) {
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

    public Year getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(Year annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
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
