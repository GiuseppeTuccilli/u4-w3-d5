package giuseppetuccilli.entities;

import giuseppetuccilli.enums.Periodicità;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Year;

@Entity
@Table(name = "riviste")
public class Rivista extends Elemento {
    private Periodicità periodicità;

    public Rivista() {
    }

    public Rivista(String titolo, Year annoPubl, int pagine, Periodicità per) {
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
