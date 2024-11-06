package riccardomamoli.gestione_prenotazioni_V2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardomamoli.gestione_prenotazioni_V2.entities.Viaggio;


@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
}