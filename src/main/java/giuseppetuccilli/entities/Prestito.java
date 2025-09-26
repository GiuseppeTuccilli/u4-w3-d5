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
}
