package riccardomamoli.gestione_prenotazioni_V2.security;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import riccardomamoli.gestione_prenotazioni_V2.entities.Dipendente;
import riccardomamoli.gestione_prenotazioni_V2.exceptions.UnauthorizedException;
import riccardomamoli.gestione_prenotazioni_V2.services.DipendenteService;
import riccardomamoli.gestione_prenotazioni_V2.tools.JWT;

@Component
public class JWTCheckerFilter extends OncePerRequestFilter {
    @Autowired
    private JWT jwt;
    @Autowired
    private DipendenteService dipendenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire token nell'Authorization Header nel formato corretto!");
        String accessToken = authHeader.substring(7);
        jwt.verifyToken(accessToken);
        String dipendenteId = jwt.getIdFromToken(accessToken);
        Dipendente currentDipendente = this.dipendenteService.findById(Long.parseLong(dipendenteId));
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentDipendente, null, currentDipendente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication); // Aggiorniamo il SecurityContext associandogli l'utente autenticato


        filterChain.doFilter(request, response); // Tramite .doFilter(req,res) richiamo il prossimo membro della catena (o un filtro o un controller)

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}