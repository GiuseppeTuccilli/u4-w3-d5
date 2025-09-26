package giuseppetuccilli.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPrestito;
    private LocalDate dataInizio;
    private LocalDate restituzionePrevista;
    private LocalDate restEffettiva;

    @ManyToOne
    @JoinColumn(name = "tessera_utente")
    private Utente utente;

    @OneToOne
    @JoinColumn(name = "ISBN_elemnto")
    private Elemento elemento;

    public Prestito() {
    }

    public Prestito(Utente u, Elemento e, LocalDate inizio) {
        this.elemento = e;
        this.utente = u;
        this.dataInizio = inizio;
        this.restituzionePrevista = inizio.plusDays(30);

    }

    public long getIdPrestito() {
        return idPrestito;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getRestituzionePrevista() {
        return restituzionePrevista;
    }

    public void setRestituzionePrevista(LocalDate restituzionePrevista) {
        this.restituzionePrevista = restituzionePrevista;
    }

    public LocalDate getRestEffettiva() {
        return restEffettiva;
    }

    public void setRestEffettiva(LocalDate restEffettiva) {
        this.restEffettiva = restEffettiva;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Elemento getElemento() {
        return elemento;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "idPrestito=" + idPrestito +
                ", dataInizio=" + dataInizio +
                ", restituzionePrevista=" + restituzionePrevista +
                ", restEffettiva=" + restEffettiva +
                ", utente=" + utente +
                ", elemento=" + elemento +
                '}';
    }
}
