package giuseppetuccilli;

import giuseppetuccilli.dao.ElementiDAO;
import giuseppetuccilli.entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.Year;

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-w3-d5pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ElementiDAO ed = new ElementiDAO(em);

        Libro libro1 = new Libro("titoloLibro", Year.of(2005), 200, "autore1", "fantasy");
        // ed.aggElement(libro1);

        em.close();
        emf.close();
        System.out.println("Hello World!");
    }
}
