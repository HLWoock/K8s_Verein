package de.woock.infra.remote;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import de.woock.domain.schufa.Anfrage;
import de.woock.domain.schufa.Antwort;

public interface Schufa {
	@PostExchange("/api/anfrage{name}")
	Antwort anfrage(@RequestBody Anfrage anfrage);
}