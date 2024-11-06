package riccardomamoli.design_pattern.esercizio_2;

public class Pagina extends elemento{
    private int numeroPagina;

    public Pagina(){}

    public Pagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    @Override
    public int getNumeroPagine() {
        return 1;
    }

    @Override
    public void stampa() {
        System.out.println("Stampo la pagina:" + getNumeroPagina());

    }
}
