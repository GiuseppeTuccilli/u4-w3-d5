package giuseppetuccilli.dao;

import giuseppetuccilli.entities.Elemento;
import giuseppetuccilli.entities.Prestito;
import giuseppetuccilli.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class UtentiDAO {
    private EntityManager em;

    public UtentiDAO(EntityManager e) {
        this.em = e;
    }

    public void salvaUtente(Utente u) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.persist(u);
        tr.commit();
        System.out.println("utente salvato");
    }

    public Utente findByTessera(long tess) {
        Utente found = em.find(Utente.class, tess);
        if (found == null) {
            throw new RuntimeException("utente non trovato");
        }
        return found;
    }

    public void eliminaTessera(long tess) {
        Utente found = this.findByTessera(tess);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.remove(found);
        tr.commit();
        System.out.println("elemento eliminato");
    }

    public List<Elemento> inPrestitoPerTessera(int tess) {
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tess", Prestito.class);
        query.setParameter("tess", tess);
        ArrayList<Prestito> prs = (ArrayList<Prestito>) query.getResultList();
        ArrayList<Elemento> els = new ArrayList<>();
        for (int i = 0; i < prs.size(); i++) {
            els.add(prs.get(i).getElemento());
        }
        return els;

    }

    public Long getNumbUt() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM Utente u", Long.class);
        return query.getSingleResult();
    }
}
