package giuseppetuccilli.dao;

import giuseppetuccilli.entities.Elemento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    public Elemento findBISBN(long isbn) {
        Elemento found = em.find(Elemento.class, isbn);
        if (found == null) {
            throw new RuntimeException("elemento non trovato");
        }
        return found;
    }

    public void eliminaISBN(long isbn) {
        Elemento found = this.findBISBN(isbn);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.remove(found);
        tr.commit();
        System.out.println("elemento eliminato");
    }

    public List<Elemento> cercaPerAnno(int anno) {
        TypedQuery<Elemento> query = em.createQuery("SELECT e FROM Elemento e WHERE e.annoDiPubblicazione = :anno", Elemento.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public List<Elemento> cercaPerAutore(String partName) {
        TypedQuery<Elemento> query = em.createQuery("SELECT e FROM Elemento e WHERE LOWER(e.autore) LIKE LOWER(:partName) ", Elemento.class);
        query.setParameter("partName", partName + "%");
        return query.getResultList();
    }

    public List<Elemento> cercaPerTitolo(String partName) {
        TypedQuery<Elemento> query = em.createQuery("SELECT e FROM Elemento e WHERE LOWER(e.titolo) LIKE LOWER(:partName) ", Elemento.class);
        query.setParameter("partName", partName + "%");
        return query.getResultList();
    }


}
