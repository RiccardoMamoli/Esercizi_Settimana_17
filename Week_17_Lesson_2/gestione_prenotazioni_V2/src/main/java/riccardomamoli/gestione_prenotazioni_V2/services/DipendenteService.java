package riccardomamoli.gestione_prenotazioni_V2.services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import riccardomamoli.gestione_prenotazioni_V2.entities.Dipendente;
import riccardomamoli.gestione_prenotazioni_V2.enums.Ruoli;
import riccardomamoli.gestione_prenotazioni_V2.exceptions.BadRequestException;
import riccardomamoli.gestione_prenotazioni_V2.exceptions.EmailNotFound;
import riccardomamoli.gestione_prenotazioni_V2.exceptions.NotFoundException;
import riccardomamoli.gestione_prenotazioni_V2.payloads.NewDipendenteDTO;
import riccardomamoli.gestione_prenotazioni_V2.repositories.DipendenteRepository;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public Dipendente saveDipendente (NewDipendenteDTO body) {
        this.dipendenteRepository.findByEmailDipendente(body.emailDipendente()).ifPresent(dipendente -> {
            throw new BadRequestException("Email " + body.emailDipendente() + " gia in uso!");
        });

        this.dipendenteRepository.findByUsernameDipendente(body.usernameDipendente()).ifPresent(dipendente -> {
            throw new BadRequestException("Username " + body.usernameDipendente() + " gia in uso!");
        });

        Dipendente newDipendente = new  Dipendente(
                body.nomeDipendente(),
                body.cognomeDipendente(),
                body.usernameDipendente(),
                body.emailDipendente(),
                "https://ui-avatars.com/api/?name=" + body.nomeDipendente() + "+" + body.cognomeDipendente(),
                body.password(), Ruoli.USER);

        return this.dipendenteRepository.save(newDipendente);
    }

    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente findById(Long idDipendente){
        return this.dipendenteRepository.findById(idDipendente).orElseThrow(() -> new NotFoundException(idDipendente));
    }


    public Dipendente findByIdAndUpdate(Long idDipendente, NewDipendenteDTO body) {
        Dipendente found = this.findById(idDipendente);
        if(!found.getEmailDipendente().equals(body.emailDipendente())) {
            this.dipendenteRepository.findByEmailDipendente(body.emailDipendente()).ifPresent(dipendente -> {
                throw new BadRequestException("Email " + body.emailDipendente() + " gia in uso");
            });
        }

        found.setNomeDipendente(body.nomeDipendente());
        found.setCognomeDipendente(body.cognomeDipendente());
        found.setEmailDipendente(body.emailDipendente());
        found.setUsernameDipendente(body.usernameDipendente());
        found.setPassword(body.password());

        return this.dipendenteRepository.save(found);
    }


    public void findByIdAndDelete(Long idDipendente) {
        Dipendente found = this.findById(idDipendente);
        this.dipendenteRepository.delete(found);
    }

    public String uploadFotoProfilo(MultipartFile file, Long idDipendente) {
        try {
            String url = (String) cloudinaryUploader.uploader()
                    .upload(file.getBytes(), ObjectUtils.emptyMap())
                    .get("url");

            Dipendente found = this.findById(idDipendente);
            found.setImmagineProfilo(url);
            dipendenteRepository.save(found);

            return url;
        } catch (IOException | java.io.IOException e) {
            throw new BadRequestException("Errore durante l'upload dell'immagine!");
        }
    }

    public Dipendente findByEmail(String email) {
        return this.dipendenteRepository.findByEmailDipendente(email).orElseThrow(() -> new EmailNotFound("L'utente con email " + email + " non è stato trovato"));
    }



}
