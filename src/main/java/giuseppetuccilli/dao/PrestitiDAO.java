package giuseppetuccilli.dao;

import giuseppetuccilli.entities.Elemento;
import giuseppetuccilli.entities.Prestito;
import giuseppetuccilli.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class PrestitiDAO {
    private EntityManager em;

    public PrestitiDAO(EntityManager e) {
        this.em = e;
    }

    public void nuovoPrestito(Prestito pr, Utente u) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        pr.setUtente(u);
        em.persist(pr);
        tr.commit();
        System.out.println("prestito salvato");
    }

    public Prestito findById(long id) {
        Prestito found = em.find(Prestito.class, id);
        if (found == null) {
            throw new RuntimeException("prestito non trovato");
        }
        return found;
    }

    public void eliminaId(long id) {
        Prestito found = this.findById(id);
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        em.remove(found);
        tr.commit();
        System.out.println("prestito eliminato");
    }

    public List<Prestito> cercaPresScaduti() {
        LocalDate today = LocalDate.now();
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.restituzionePrevista < :now AND p.restEffettiva IS NULL", Prestito.class);
        query.setParameter("now", today);
        return query.getResultList();
    }

    public List<Prestito> findAll() {
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p", Prestito.class);
        return query.getResultList();
    }

    public List<Prestito> getPrestTessera(long tess) {
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :tess", Prestito.class);
        query.setParameter("tess", tess);
        return query.getResultList();
    }

    public List<Prestito> getPresElem(Elemento el) {
        TypedQuery<Prestito> query = em.createQuery("SELECT p FROM Prestito p WHERE p.elemento.codISBN = :sbn", Prestito.class);
        query.setParameter("sbn", el.getCodISBN());
        return query.getResultList();
    }


}
