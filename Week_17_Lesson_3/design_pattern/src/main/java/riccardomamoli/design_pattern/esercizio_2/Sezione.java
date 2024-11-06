package riccardomamoli.design_pattern.esercizio_2;

import java.util.ArrayList;
import java.util.List;

public class Sezione extends elemento{
    private String titolo;
    private List<elemento> elementi = new ArrayList<>();

    public Sezione(String titolo) {
        this.titolo = titolo;
    }

    public Sezione(){}

    public void aggiungiElemento(elemento elemento) {
        elementi.add(elemento);
    }

    public void eliminaElemento(elemento elemento) {
        elementi.remove(elemento);
    }

    @Override
    public int getNumeroPagine() {
        int numeroPagine = 0;
        for (elemento elemento : elementi) {
            numeroPagine += elemento.getNumeroPagine();
        }
        return numeroPagine;
    }

    @Override
    public void stampa() {
        System.out.println("Stampo la sezione: " + titolo);
        for (elemento elemento : elementi) {
            elemento.stampa();
        }
    }
}
