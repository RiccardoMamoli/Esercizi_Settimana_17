package riccardomamoli.gestione_prenotazioni_V2.exceptions;

public class NotFoundException extends RuntimeException {
  public NotFoundException(Long id) {
    super("Il record con id " + id + " non è stato trovato!");
  }
}