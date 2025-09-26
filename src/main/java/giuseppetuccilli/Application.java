package giuseppetuccilli;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-w3-d5pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        

        em.close();
        emf.close();
        System.out.println("Hello World!");
    }
}
