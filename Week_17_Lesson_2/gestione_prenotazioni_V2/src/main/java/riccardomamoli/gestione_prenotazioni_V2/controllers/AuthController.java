package riccardomamoli.gestione_prenotazioni_V2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardomamoli.gestione_prenotazioni_V2.entities.Dipendente;
import riccardomamoli.gestione_prenotazioni_V2.exceptions.BadRequestException;
import riccardomamoli.gestione_prenotazioni_V2.payloads.DipendenteLoginDTO;
import riccardomamoli.gestione_prenotazioni_V2.payloads.DipendenteLoginResponseDTO;
import riccardomamoli.gestione_prenotazioni_V2.payloads.NewDipendenteDTO;
import riccardomamoli.gestione_prenotazioni_V2.services.AuthService;
import riccardomamoli.gestione_prenotazioni_V2.services.DipendenteService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public DipendenteLoginResponseDTO login(@RequestBody DipendenteLoginDTO body) {
        return new DipendenteLoginResponseDTO(this.authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.dipendenteService.saveDipendente(body);
    }
}