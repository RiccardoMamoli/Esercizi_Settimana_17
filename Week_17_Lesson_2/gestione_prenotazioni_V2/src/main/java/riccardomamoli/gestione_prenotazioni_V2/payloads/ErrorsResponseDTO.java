package riccardomamoli.gestione_prenotazioni_V2.payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timestamp) {
}
