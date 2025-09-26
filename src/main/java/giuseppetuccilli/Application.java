package giuseppetuccilli;

import giuseppetuccilli.dao.ElementiDAO;
import giuseppetuccilli.dao.PrestitiDAO;
import giuseppetuccilli.dao.UtentiDAO;
import giuseppetuccilli.entities.Elemento;
import giuseppetuccilli.entities.Libro;
import giuseppetuccilli.entities.Prestito;
import giuseppetuccilli.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-w3-d5pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ElementiDAO ed = new ElementiDAO(em);
        UtentiDAO ud = new UtentiDAO(em);
        PrestitiDAO pd = new PrestitiDAO(em);

        Libro libro1 = new Libro("titoloLibro", 2005, 200, "autore1", "fantasy");
        Libro libro2 = new Libro("titoloLibro2", 2004, 300, "autore1", "horror");
        Utente ut1 = new Utente("pippo", "kjh", LocalDate.of(1991, 10, 10));
        Utente ut2 = new Utente("pluto", "kjh", LocalDate.of(1995, 1, 27));
        //ed.aggElement(libro1);
        //ed.aggElement(libro2);
        //ud.salvaUtente(ut2);

        Utente uFromDb = ud.findByTessera(1);
        Elemento elFromDb = ed.findBISBN(1);
        Elemento elFromDb2 = ed.findBISBN(2);

        Prestito pr1 = new Prestito(uFromDb, elFromDb, LocalDate.of(2025, 1, 3));
        Prestito pr2 = new Prestito(uFromDb, elFromDb2, LocalDate.of(2025, 2, 6));
        // pd.nuovoPrestito(pr2);


        System.out.println(ud.inPrestitoPerTessera(1));


        em.close();
        emf.close();
        System.out.println("Hello World!");
    }
}
