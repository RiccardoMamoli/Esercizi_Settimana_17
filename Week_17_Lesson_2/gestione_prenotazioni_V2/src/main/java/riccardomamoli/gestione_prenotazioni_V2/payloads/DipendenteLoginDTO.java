package riccardomamoli.gestione_prenotazioni_V2.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record DipendenteLoginDTO(

        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password
) {
}