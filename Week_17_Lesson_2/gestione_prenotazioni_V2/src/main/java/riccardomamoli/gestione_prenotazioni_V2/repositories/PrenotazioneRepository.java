package riccardomamoli.gestione_prenotazioni_V2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardomamoli.gestione_prenotazioni_V2.entities.Prenotazione;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {

    boolean existsByDipendenteIdAndViaggioDataViaggio(Long idDipendente, LocalDate dataViaggio);

    List<Prenotazione> findByDataRichiesta(LocalDate dataRichiesta);

    Page<Prenotazione> findByDipendenteId(Long id, Pageable pageable);

    boolean existsByViaggioIdAndDataViaggio(Long viaggioId, LocalDate dataViaggio);
}
