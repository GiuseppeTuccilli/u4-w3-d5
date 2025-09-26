package giuseppetuccilli.entities;

import jakarta.persistence.*;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Elemento elemento = (Elemento) o;
        return codISBN == elemento.codISBN && annoDiPubblicazione == elemento.annoDiPubblicazione && numeroPagine == elemento.numeroPagine && Objects.equals(titolo, elemento.titolo) && Objects.equals(prestito, elemento.prestito);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codISBN, titolo, annoDiPubblicazione, numeroPagine, prestito);
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
