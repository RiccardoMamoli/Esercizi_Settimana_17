package riccardomamoli.design_pattern.esercizio_2;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String titolo;
    private List<String> autori;
    private double prezzo;
    private List<elemento> contenuto = new ArrayList<>();

    public Libro(){}

    public Libro(String titolo, List<String> autori, double prezzo) {
        this.titolo = titolo;
        this.autori = autori;
        this.prezzo = prezzo;
    }

    public void aggiungiElemento(elemento elemento) {
        contenuto.add(elemento);
    }

    public void eliminaElemento(elemento elemento) {
        contenuto.remove(elemento);
    }

    public int getNumeroTotale() {
        int numeroPagine = 0;
        for (elemento elemento : contenuto) {
            numeroPagine += elemento.getNumeroPagine();
        }
        return numeroPagine;
    }

    public void printAutori() {
        autori.forEach(a -> System.out.println(a));
    }

    public void stampaLibro() {
        System.out.println("Stampo libro: " + titolo);
        System.out.println("Autori:");
        printAutori();
        System.out.println("Prezzo: " + prezzo);
        for(elemento elemento : contenuto) {
            elemento.stampa();
        }}
}
