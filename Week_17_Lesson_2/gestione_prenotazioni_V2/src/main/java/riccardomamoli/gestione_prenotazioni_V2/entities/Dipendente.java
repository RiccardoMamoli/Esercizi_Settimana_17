package riccardomamoli.gestione_prenotazioni_V2.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import riccardomamoli.gestione_prenotazioni_V2.enums.Ruoli;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "dipendenti")
public class Dipendente implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private String nomeDipendente;
    private String cognomeDipendente;
    private String usernameDipendente;
    private String emailDipendente;
    private String immagineProfilo;
    private String password;
    @Enumerated(EnumType.STRING)
    private Ruoli ruoli;

    public Dipendente(){}

    public Dipendente(String nomeDipendente, String cognomeDipendente, String usernameDipendente, String emailDipendente, String immagineProfilo, String password, Ruoli ruoli) {
        this.nomeDipendente = nomeDipendente;
        this.cognomeDipendente = cognomeDipendente;
        this.usernameDipendente = usernameDipendente;
        this.emailDipendente = emailDipendente;
        this.immagineProfilo = immagineProfilo;
        this.password = password;
        this.ruoli = ruoli;
    }

    public String getEmailDipendente() {
        return emailDipendente;
    }

    public void setEmailDipendente(String emailDipendente) {
        this.emailDipendente = emailDipendente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruoli getRuoli() {
        return ruoli;
    }

    public void setRuoli(Ruoli ruoli) {
        this.ruoli = ruoli;
    }

    public Long getId() {
        return id;
    }

    public String getNomeDipendente() {
        return nomeDipendente;
    }

    public void setNomeDipendente(String nomeDipendente) {
        this.nomeDipendente = nomeDipendente;
    }

    public String getCognomeDipendente() {
        return cognomeDipendente;
    }

    public void setCognomeDipendente(String cognomeDipendente) {
        this.cognomeDipendente = cognomeDipendente;
    }

    public String getUsernameDipendente() {
        return usernameDipendente;
    }

    public void setUsernameDipendente(String usernameDipendente) {
        this.usernameDipendente = usernameDipendente;
    }

    public String getImmagineProfilo() {
        return immagineProfilo;
    }

    public void setImmagineProfilo(String immagineProfilo) {
        this.immagineProfilo = immagineProfilo;
    }

    @Override
    public String toString() {
        return "Dipendente{" +
                "id=" + id +
                ", nomeDipendente='" + nomeDipendente + '\'' +
                ", cognomeDipendente='" + cognomeDipendente + '\'' +
                ", usernameDipendente='" + usernameDipendente + '\'' +
                ", emailDipendente='" + emailDipendente + '\'' +
                ", immagineProfilo='" + immagineProfilo + '\'' +
                ", password='" + password + '\'' +
                ", ruoli=" + ruoli +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruoli.name()));
    }

    @Override
    public String getUsername() {
        return this.emailDipendente;
    }
}
