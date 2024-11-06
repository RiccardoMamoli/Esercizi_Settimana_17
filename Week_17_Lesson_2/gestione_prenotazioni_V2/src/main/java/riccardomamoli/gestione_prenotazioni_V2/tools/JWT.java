package riccardomamoli.gestione_prenotazioni_V2.tools;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import riccardomamoli.gestione_prenotazioni_V2.entities.Dipendente;
import riccardomamoli.gestione_prenotazioni_V2.exceptions.UnauthorizedException;

import java.util.Date;

@Component
public class JWT {
    @Value("${jwt.secret}")

    private String secret;

    public String createToken(Dipendente dipendente) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .subject(String.valueOf(dipendente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String accessToken) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(accessToken);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi con il token! per favore effettua nuovamente il login");
        }
    }

    public String getIdFromToken(String accessToken) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(accessToken)
                .getPayload().getSubject(); // All'interno del Payload, nel campo Subject avevamo inserito l'id dell'utente
    }
}
