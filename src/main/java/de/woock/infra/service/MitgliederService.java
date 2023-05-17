package de.woock.infra.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.woock.domain.Mitglied;
import de.woock.domain.MitgliedsAntrag;
import de.woock.infra.exceptions.MitgliedNichtGefundenException;
import de.woock.infra.exceptions.MitgliedSchonVorhandenException;
import de.woock.infra.repository.MitgliederRepository;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Observed
@RequiredArgsConstructor
@Service
public class MitgliederService {

	private final MitgliederRepository mitgliederRepository;
	
	public List<Mitglied> mitglieder() {
		return mitgliederRepository.findAll();
	}
	
	public Mitglied mitglied(Long id) {
		return mitgliederRepository.findById(id)
	                               .orElseThrow(() -> new MitgliedNichtGefundenException(id));
	}
	
	public Mitglied mitglied(String username) {
		return mitgliederRepository.findByUsernameIgnoreCase(username)
				                   .orElseThrow(() -> new MitgliedNichtGefundenException(username));
	}

	public Page<Mitglied> mitglieder(Integer page){
		 Page<Mitglied> paged = mitgliederRepository.findAll(PageRequest.of(page, 10, Sort.by("name", "vorname")));
		 return paged;
	}

	public void deleteMitglied(Long id) {
		Mitglied mitglied = mitgliederRepository.findById(id)
				                                .orElseThrow(() -> new MitgliedNichtGefundenException(id));
         mitgliederRepository.delete(mitglied);		
	}

	public Mitglied aufnehmen(@Valid MitgliedsAntrag antrag) {
		mitgliederRepository.findByUsernameIgnoreCase(antrag.username()) 
		                    .ifPresent(mitglied -> { 
		                    	throw new MitgliedSchonVorhandenException(antrag.username());
		                    });
		
		log.debug(String.format("Neues Mitglied aufgenommen {Username: %s}", antrag.username()));
		return mitgliederRepository.save(new Mitglied(antrag));
	}

}
