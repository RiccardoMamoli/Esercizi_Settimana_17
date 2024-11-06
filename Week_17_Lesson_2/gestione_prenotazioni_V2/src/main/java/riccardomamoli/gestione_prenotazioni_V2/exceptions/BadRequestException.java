package riccardomamoli.gestione_prenotazioni_V2.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
