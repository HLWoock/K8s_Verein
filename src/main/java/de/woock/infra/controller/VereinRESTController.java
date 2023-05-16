package de.woock.infra.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.woock.domain.Mitglied;
import de.woock.domain.MitgliedsAntrag;
import de.woock.infra.service.MitgliederService;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Observed
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class VereinRESTController {
	
	private final MitgliederService mitgliederService;
	
	

	@GetMapping
    public Page<Mitglied> getMitglieder(int page) {
        return mitgliederService.mitglieder(page);
    }
	
    @GetMapping("/{id}")
    public Mitglied getMitglieder(@PathVariable Long id) {
        return mitgliederService.mitglied(id);
    }
    
    @PostMapping("/antrag")
    public void submitAntrag(@Valid @RequestBody MitgliedsAntrag antrag) {
    	log.debug("Antrag fuer Mitglied {} {}", antrag.vorname(), antrag.name());
    	mitgliederService.aufnehmen(antrag);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMitglieder(@PathVariable Long id) {
    	mitgliederService.deleteMitglied(id);
        return ResponseEntity.noContent().build();
    }
}
