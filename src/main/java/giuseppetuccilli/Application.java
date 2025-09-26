package giuseppetuccilli;

import giuseppetuccilli.dao.ElementiDAO;
import giuseppetuccilli.entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-w3-d5pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ElementiDAO ed = new ElementiDAO(em);

        Libro libro1 = new Libro("titoloLibro", 2005, 200, "autore1", "fantasy");
        Libro libro2 = new Libro("titoloLibro2", 2004, 300, "autore1", "horror");
        //ed.aggElement(libro1);
        //ed.aggElement(libro2);
        System.out.println(ed.cercaPerAutore("au"));


        em.close();
        emf.close();
        System.out.println("Hello World!");
    }
}
