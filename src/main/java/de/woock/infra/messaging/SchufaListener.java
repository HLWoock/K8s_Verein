package de.woock.infra.messaging;

import static de.woock.config.MessagingConfig.DEFAULT_PARSING_QUEUE;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import de.woock.domain.Mitglied;
import de.woock.domain.schufa.Antwort;
import de.woock.infra.repository.MitgliederRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class SchufaListener {
	
	private final MitgliederRepository mitgliederRepository;
	
	@RabbitListener(queues = DEFAULT_PARSING_QUEUE)
	public void processMessage(final Antwort antwort) {
		log.info("Bonitiaetsanfrage fuer {} eingegangen", antwort.name());
		Optional<Mitglied> _mitglied = mitgliederRepository.findByUsernameIgnoreCase(antwort.name());
		if (_mitglied.isPresent()) {
			Mitglied mitglied = _mitglied.get();
			mitglied.setBonitaet(antwort.bonitaet());
			mitgliederRepository.save(mitglied);
			log.info("Bonitaet von {} ist {}", mitglied.getName(), mitglied.getBonitaet());
		} else {
			log.info("Mitglied {} nicht gefunden", antwort.name());
			
		}
	}
}
