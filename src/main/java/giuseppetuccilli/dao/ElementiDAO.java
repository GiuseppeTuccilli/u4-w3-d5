package giuseppetuccilli.dao;

import giuseppetuccilli.entities.Elemento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ElementiDAO {
    private EntityManager em;

    public ElementiDAO(EntityManager e) {
        this.em = e;
    }

    public void aggElement(Elemento el) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.persist(el);
        tr.commit();
        System.out.println("l'elemento " + el.getTitolo() + " è stato salvato");
    }
}
