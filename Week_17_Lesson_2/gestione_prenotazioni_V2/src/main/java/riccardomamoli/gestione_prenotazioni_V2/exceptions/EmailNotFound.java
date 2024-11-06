package riccardomamoli.gestione_prenotazioni_V2.exceptions;

public class EmailNotFound extends RuntimeException {
    public EmailNotFound(String message) {
        super(message);
    }
}
