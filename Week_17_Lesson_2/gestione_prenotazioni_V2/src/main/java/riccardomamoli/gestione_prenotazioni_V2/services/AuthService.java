package riccardomamoli.gestione_prenotazioni_V2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import riccardomamoli.gestione_prenotazioni_V2.entities.Dipendente;
import riccardomamoli.gestione_prenotazioni_V2.exceptions.UnauthorizedException;
import riccardomamoli.gestione_prenotazioni_V2.payloads.DipendenteLoginDTO;
import riccardomamoli.gestione_prenotazioni_V2.tools.JWT;

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWT jwt;
    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(DipendenteLoginDTO body) {
        Dipendente found = this.dipendenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            String accessToken = jwt.createToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali errate");
        }
    }

}