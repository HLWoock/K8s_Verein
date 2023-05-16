package de.woock.infra.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.woock.domain.schufa.Anfrage;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Observed
@RequiredArgsConstructor
@Service
public class SchufaService {
	
	private final RestTemplate restTemplate;
	
	public void bonitaet(String name) {
		log.info("stelle Anfrage für {}", name);
		restTemplate.postForEntity("http://schufa:8091/api/anfrage", new Anfrage(name), Void.class);
	}


}
