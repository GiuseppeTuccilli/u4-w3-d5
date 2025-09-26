package giuseppetuccilli;

import giuseppetuccilli.dao.ElementiDAO;
import giuseppetuccilli.dao.PrestitiDAO;
import giuseppetuccilli.dao.UtentiDAO;
import giuseppetuccilli.entities.*;
import giuseppetuccilli.enums.Periodicità;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4-w3-d5pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ElementiDAO ed = new ElementiDAO(em);
        UtentiDAO ud = new UtentiDAO(em);
        PrestitiDAO pd = new PrestitiDAO(em);
        Scanner scanner = new Scanner(System.in);
        String op = "";
        String ti = "";
        String aut = "";
        String gen = "";
        String perSt;
        Periodicità per;
        long id = 0;
        String search = "";

        int anno = 0, pag = 0;


        Libro libro1 = new Libro("titoloLibro", 2005, 200, "autore1", "fantasy");
        Libro libro2 = new Libro("titoloLibro2", 2004, 300, "autore1", "horror");
        Libro libro3 = new Libro("titoloLibro3", 2007, 300, "autore1", "horror");
        Libro libro4 = new Libro("titoloLibro4", 2008, 300, "autore1", "horror");
        Utente ut1 = new Utente("pippo", "kjh", LocalDate.of(1991, 10, 10));
        Utente ut2 = new Utente("pluto", "kjh", LocalDate.of(1995, 1, 27));
        Rivista riv1 = new Rivista("titoloRivista", 2024, 50, Periodicità.MENSILE);

        while (true) {
            System.out.println("catalogo libri e riviste; [elementi attuali: " + ed.getNumbEl() + "; utenti attuali: " + ud.getNumbUt() + "]");
            System.out.println("inserire l per aggiungere un libro");
            System.out.println("inserire r per aggiungere una rivista");
            System.out.println("inserire p per aggiungere un nuovo prestito");
            System.out.println("inserire e per eliminare un elemento via codice ISBN (intero)");
            System.out.println("inserire c per le opzioni di ricerca");
            System.out.println("inserire q per uscire");
            op = scanner.nextLine();
            if (op.equals("q")) {
                break;
            }

            switch (op) {
                case "l":
                    System.out.println("Nuovo libro");
                    System.out.println("inserire il titolo del libro");
                    ti = scanner.nextLine();
                    System.out.println("inserire l'anno di pubblicazione (intero)");
                    try {
                        anno = Integer.parseInt(scanner.nextLine());
                    } catch (RuntimeException e) {
                        System.out.println("input non valido");
                        break;
                    }
                    System.out.println("inserire il numero di pagine (intero)");
                    try {
                        pag = Integer.parseInt(scanner.nextLine());
                    } catch (RuntimeException e) {
                        System.out.println("input non valido");
                        break;
                    }
                    System.out.println("inserire l'autore");
                    aut = scanner.nextLine();
                    System.out.println("inserire il genere");
                    gen = scanner.nextLine();
                    Libro l = new Libro(ti, anno, pag, aut, gen);
                    ed.aggElement(l);
                    break;
                case "r":
                    System.out.println("Nuova rivista");
                    System.out.println("inserire il titolo della rivista");
                    ti = scanner.nextLine();
                    System.out.println("inserire l'anno di pubblicazione (intero)");
                    try {
                        anno = Integer.parseInt(scanner.nextLine());
                    } catch (RuntimeException e) {
                        System.out.println("input non valido");
                        break;
                    }
                    System.out.println("inserire il numero di pagine (intero)");
                    try {
                        pag = Integer.parseInt(scanner.nextLine());
                    } catch (RuntimeException e) {
                        System.out.println("input non valido");
                        break;
                    }
                    System.out.println("periodicità: inserire un numero da 1 a 3");
                    System.out.println("1 = Settimanale, 2 = Mensile, 3 = Semestrale");
                    perSt = scanner.nextLine();
                    if (perSt.equals("1")) {
                        per = Periodicità.SETTIMANALE;
                    } else if (perSt.equals("2")) {
                        per = Periodicità.MENSILE;
                    } else if (perSt.equals("3")) {
                        per = Periodicità.SEMESTRALE;
                    } else {
                        System.out.println("input non valido");
                        break;
                    }
                    Rivista r = new Rivista(ti, anno, pag, per);
                    ed.aggElement(r);
                    break;
                case "e":
                    id = 0;
                    System.out.println("inserire codice ISBN dell'elemnto da aliminare oppure q per uscire");
                    if (scanner.nextLine().equals("q")) {
                        break;
                    }
                    try {
                        id = Long.parseLong(scanner.nextLine());
                        ed.eliminaISBN(id);
                        break;
                    } catch (RuntimeException e) {
                        System.out.println("input non valido, " + e.getMessage());
                        break;
                    }

                case "p":
                    long tess = 0;
                    long isbn = 0;
                    Utente u = null;
                    Elemento e = null;
                    System.out.println("Nuovo prestito (la data di inizio sarà quella di oggi ");
                    System.out.println("inserire num tessera utente");
                    try {
                        tess = Long.parseLong(scanner.nextLine());
                        u = ud.findByTessera(tess);
                    } catch (RuntimeException ex) {
                        System.out.println("input non valido");
                        break;
                    }
                    System.out.println("inserire ISBN dell' elemento (gli elementi già in prestito non possono essere aggiunti)");
                    try {
                        isbn = Long.parseLong(scanner.nextLine());
                        e = ed.findBISBN(isbn);
                    } catch (RuntimeException ex) {
                        System.out.println("input non valido");
                        break;
                    }

                    LocalDate d = LocalDate.now();
                    Prestito pr = new Prestito(u, e, d);
                    List<Prestito> prestiti = pd.findAll();
                    boolean ok = true;

                    for (int i = 0; i < prestiti.size(); i++) {
                        if (prestiti.get(i).getElemento().equals(e)) {

                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        pd.nuovoPrestito(pr, u);
                        break;
                    } else {
                        System.out.println("impossibile salvare il prestito");
                        break;
                    }


                case "c":
                    System.out.println("Opzioni di ricerca: inserire un numero da 1 a 1");
                    System.out.println("1 = Cerca per ISBN");
                    System.out.println("2 = Cerca per anno pubblicazione");
                    System.out.println("3 = Cerca per autore");
                    System.out.println("4 = Cerca elementi in prestito per numero tessera");
                    System.out.println("5 = Cerca prestiti scaduti");
                    System.out.println("6 = Cerca per titolo");
                    search = scanner.nextLine();
                    switch (search) {
                        case "1":
                            id = 0;
                            System.out.println("inserire codice ISBN");
                            try {
                                id = Long.parseLong(scanner.nextLine());
                                System.out.println(ed.findBISBN(id));
                                break;
                            } catch (RuntimeException ex) {
                                System.out.println("input non valido, " + ex.getMessage());
                                break;
                            }
                        case "2":
                            int srcYear = 0;
                            System.out.println("inserire anno di pubblicazione (intero)");
                            try {
                                srcYear = Integer.parseInt(scanner.nextLine());
                                System.out.println(ed.cercaPerAnno(srcYear));
                                break;
                            } catch (RuntimeException ex) {
                                System.out.println("input non valido, " + ex.getMessage());
                                break;
                            }
                        case "3":
                            String srcAut = "";
                            System.out.println("inserire nome dell'autore");
                            srcAut = scanner.nextLine();
                            System.out.println(ed.cercaPerAutore(srcAut));
                            break;
                        case "4":
                            long stess = 0;
                            System.out.println("inserire numero tessera (intero)");
                            try {
                                tess = Long.parseLong(scanner.nextLine());
                                Utente ut = ud.findByTessera(stess);
                                System.out.println(ut.getPrestiti());
                                break;
                            } catch (RuntimeException ex) {
                                System.out.println("input non valido, " + ex.getMessage());
                                break;
                            }
                        case "5":
                            System.out.println("prestiti scaduti");
                            System.out.println(pd.cercaPresScaduti());
                            break;
                        case "6":
                            String srcTit = "";
                            System.out.println("inserire il titolo (o parte di esso)");
                            srcTit = scanner.nextLine();
                            System.out.println(ed.cercaPerTitolo(srcTit));
                            break;
                        default:
                            System.out.println("input non valido");
                            break;


                    }
                    break;

                default:
                    System.out.println("input non valido");
                    break;

            }

        }


        em.close();
        emf.close();
        System.out.println("Bye");
    }
}
