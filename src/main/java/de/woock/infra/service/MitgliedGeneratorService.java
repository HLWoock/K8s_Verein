package de.woock.infra.service;

import static de.woock.domain.status.Bonitaet.ANFRAGE_AUSSTEHEND;
import static de.woock.domain.status.Vertragsart.PRIVATKUNDE;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.stereotype.Service;

import de.woock.domain.Mitglied;
import de.woock.domain.MitgliedsAntrag;
import de.woock.domain.personalien.Adresse;
import de.woock.domain.personalien.Anrede;
import de.woock.domain.personalien.Geschlecht;
import de.woock.domain.status.Bonitaet;
import de.woock.infra.repository.MitgliederRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Observed
@RequiredArgsConstructor
@Service
public class MitgliedGeneratorService {
	
	private final MitgliederRepository mitgliederRepository;
	private final SchufaService        schufaService;
	
	public void aufnehmen(MitgliedsAntrag antrag) {
		log.info("Antrag aufnehmen für {} {}", antrag.vorname(), antrag.name());
		Anrede    anrede        = null;
		String    vorname       = antrag.vorname();
		String    name          = antrag.name();
		String    username      = username(name, vorname);
		Bonitaet  bonitaet      = bonitaet(username);
		LocalDate geburtsdatum  = LocalDate.now();
		String    telefon       = antrag.telefon();
		String    mobil         = null;
		String    email         = antrag.email();
		Adresse   adresse       = new Adresse(antrag.ort(), antrag.strasse());
		Geschlecht geschlecht   = null;
		
		Mitglied mitglied = new Mitglied(anrede, vorname, name, username, geburtsdatum, telefon, mobil, email, adresse, geschlecht, bonitaet, PRIVATKUNDE);
		mitgliederRepository.save(mitglied);
	}
	
	private String username(String name, String vorname) {
		Random rand = new Random();
		return vorname.substring(0, 2) + name + rand.nextInt(1000) ;
	}
	
	private Bonitaet bonitaet(String name) {
		log.info("Bonitaets-Anfrage für : {}", name);
		schufaService.bonitaet(name);
		return ANFRAGE_AUSSTEHEND;
	}

}
