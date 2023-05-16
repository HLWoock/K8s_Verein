package de.woock.infra.service;

import static de.woock.domain.status.Vertragsart.FIRMENKUNDE;
import static de.woock.domain.status.Vertragsart.PRIVATKUNDE;

import org.springframework.stereotype.Service;

import de.woock.infra.repository.MitgliederRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;

@Observed
@RequiredArgsConstructor
@Service
public class MitgliederStatistikService {
	
	private final MitgliederRepository mitgliederRepository;
	
	public long anzahlMitglieder() {
		return mitgliederRepository.count();
	}
	
	public long anzahlStaedte() {
		return mitgliederRepository.anzahlOrte();
	}
	
	public long anzahlPrivatkunden() {
		return mitgliederRepository.countByVertragsart(FIRMENKUNDE);
	}
	
	public long anzahlFirmenkunden() {
		return mitgliederRepository.countByVertragsart(PRIVATKUNDE);
	}
}
