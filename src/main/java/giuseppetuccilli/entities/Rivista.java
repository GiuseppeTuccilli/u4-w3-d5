package giuseppetuccilli.entities;

import giuseppetuccilli.enums.Periodicità;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "riviste")
public class Rivista extends Elemento {
    @Enumerated(EnumType.STRING)
    private Periodicità periodicità;

    public Rivista() {
    }

    public Rivista(String titolo, int annoPubl, int pagine, Periodicità per) {
        super(titolo, annoPubl, pagine);
        this.periodicità = per;
    }

    public Periodicità getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(Periodicità periodicità) {
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicità=" + periodicità +
                "} " + super.toString();
    }
}
