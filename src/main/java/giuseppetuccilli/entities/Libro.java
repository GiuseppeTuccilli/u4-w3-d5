package giuseppetuccilli.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Year;

@Entity
@Table(name = "libri")
public class Libro extends Elemento {
    private String autore;
    private String genere;

    public Libro() {
    }

    public Libro(String titolo, Year annoPubl, int pagine, String autore, String genere) {
        super(titolo, annoPubl, pagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                "} " + super.toString();
    }
}
