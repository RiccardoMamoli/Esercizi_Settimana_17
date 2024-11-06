package riccardomamoli.design_pattern.esercizio_2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import riccardomamoli.design_pattern.esercizio_1.DataSource;
import riccardomamoli.design_pattern.esercizio_1.Info;
import riccardomamoli.design_pattern.esercizio_1.InfoAdapter;
import riccardomamoli.design_pattern.esercizio_1.UserData;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class LibroRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        Pagina p1 = new Pagina(1);
        Pagina p2 = new Pagina(2);
        Pagina p3 = new Pagina(3);
        Pagina p4 = new Pagina(4);
        Pagina p5 = new Pagina(5);
        Pagina p6 = new Pagina(6);
        Pagina p7 = new Pagina(7);
        Pagina p8 = new Pagina(8);

        Sezione sezione1 = new Sezione("Intro");
        sezione1.aggiungiElemento(p1);
        sezione1.aggiungiElemento(p2);

        Sezione sezione2 = new Sezione("Cap 1");
        sezione2.aggiungiElemento(p3);
        sezione2.aggiungiElemento(p4);

        Sezione sezione3 = new Sezione("Cap 2");
        sezione3.aggiungiElemento(p5);
        sezione3.aggiungiElemento(p6);

        Sezione sezione4 = new Sezione("Finale");
        sezione4.aggiungiElemento(p7);
        sezione4.aggiungiElemento(p8);

        Sezione sezionePrincipale = new Sezione("Parte 1");
        sezionePrincipale.aggiungiElemento(sezione1);
        sezionePrincipale.aggiungiElemento(sezione2);

        Sezione sezionePincipale2 = new Sezione("Parte 2");
        sezionePincipale2.aggiungiElemento(sezione3);
        sezionePincipale2.aggiungiElemento(sezione4);

        Libro libro = new Libro("Titolo a caso", Arrays.asList("Aut1" , "Aut2"), 10);
        libro.aggiungiElemento(sezionePrincipale);
        libro.aggiungiElemento(sezionePincipale2);

        libro.stampaLibro();
        System.out.println("Totale pagine: " + libro.getNumeroTotale());

    }

}
