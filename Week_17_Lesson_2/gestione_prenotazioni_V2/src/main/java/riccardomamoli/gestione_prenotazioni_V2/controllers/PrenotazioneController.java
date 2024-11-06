package riccardomamoli.gestione_prenotazioni_V2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardomamoli.gestione_prenotazioni_V2.entities.Prenotazione;
import riccardomamoli.gestione_prenotazioni_V2.payloads.NewPrenotazioneDTO;
import riccardomamoli.gestione_prenotazioni_V2.services.PrenotazioneService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    // 1. GET http://localhost:3001/prenotazioni
    @GetMapping
    public Page<Prenotazione> getAllPrenotazioni(Pageable pageable) {
        return prenotazioneService.getAllPrenotazioni(pageable);
    }

    // 2. POST http://localhost:3001/prenotazioni
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(@RequestBody @Validated NewPrenotazioneDTO body) {
        return prenotazioneService.savePrenotazione(body);
    }

    // 3. GET http://localhost:3001/prenotazioni/{prenotazioneId}
    @GetMapping("/{prenotazioneId}")
    public Prenotazione getPrenotazioneById(@PathVariable Long prenotazioneId) {
        return prenotazioneService.findById(prenotazioneId);
    }

    // 4. DELETE http://localhost:3001/prenotazioni/{prenotazioneId}
    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long prenotazioneId) {
        prenotazioneService.findByIdAndDelete(prenotazioneId);
    }

    @GetMapping("/dipendente/{dipendenteId}")
    public Page<Prenotazione> getPrenotazioniByDipendente(
            @PathVariable Long dipendenteId,
            Pageable pageable) {
        return prenotazioneService.getPrenotazioniByIdDipendente(dipendenteId, pageable);
    }

    @GetMapping("/data/{dataViaggio}")
    public List<Prenotazione> getPrenotazioniByData(@PathVariable LocalDate dataViaggio) {
        return prenotazioneService.getPrenotazioniByDataRichiesta(dataViaggio);
    }

    @GetMapping("/exists")
    public boolean existsPrenotazioneForViaggioAndData(
            @RequestParam Long viaggioId,
            @RequestParam LocalDate dataViaggio) {
        return prenotazioneService.existsPrenotazioneForViaggioAndData(viaggioId, dataViaggio);
    }
}