package de.woock.infra.repository;

import static de.woock.domain.status.Vertragsart.FIRMENKUNDE;
import static de.woock.domain.status.Vertragsart.PRIVATKUNDE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import de.woock.domain.Mitglied;
import de.woock.domain.MitgliedsAntrag;
import de.woock.domain.personalien.Adresse;
import de.woock.infra.repository.MitgliederRepository;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = NONE)
public class MitgliederRepositoryTest {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private MitgliederRepository repo;
	
	@Test
	public void findeMitgliedMitUsernamen() {
		// given
		String username = "Hermann";
		Mitglied mitglied = em.persistAndFlush(new Mitglied(username));

		// when
		Optional<Mitglied> gefundenesMitglied = repo.findByUsernameIgnoreCase(username);

		// then
		assertThat(gefundenesMitglied).isNotNull();
		assertThat(gefundenesMitglied.get()).isEqualTo(mitglied);
		assertThat(gefundenesMitglied.get().getUsername()).isEqualTo(username);
	}
	
	@Test
	public void mitgliedMitUsernamenNichtVorhanden() {
		// given
		String username = "Hermann";
		
		// when
		Optional<Mitglied> gefundenesMitglied = repo.findByUsernameIgnoreCase(username);
		
		// then
		assertThat(gefundenesMitglied).isEqualTo(Optional.empty());
	}
	
	@Test
	public void anzahlMitgliederNachVertagsart() {
		// given
		Mitglied firmenkunde1 = em.persistAndFlush(new Mitglied("firmenkunde1")); firmenkunde1.setVertragsart(FIRMENKUNDE);
		Mitglied firmenkunde2 = em.persistAndFlush(new Mitglied("firmenkunde2")); firmenkunde2.setVertragsart(FIRMENKUNDE);
		Mitglied firmenkunde3 = em.persistAndFlush(new Mitglied("firmenkunde3")); firmenkunde3.setVertragsart(FIRMENKUNDE);
		Mitglied firmenkunde4 = em.persistAndFlush(new Mitglied("firmenkunde4")); firmenkunde4.setVertragsart(FIRMENKUNDE);
		Mitglied privatkunde1 = em.persistAndFlush(new Mitglied("privatkunde1")); privatkunde1.setVertragsart(PRIVATKUNDE);

		// when
		Long anzahlFirmenkunden = repo.countByVertragsart(FIRMENKUNDE);
		Long anzahlPrivatkunden = repo.countByVertragsart(PRIVATKUNDE);

		// then
		assertThat(anzahlFirmenkunden).isEqualTo(4);
		assertThat(anzahlPrivatkunden).isEqualTo(1);
	}
	
	@Test
	public void anzahlOrteInDenenMitgliederWohnen() {
		// given
		Mitglied hamburg1 = em.persistAndFlush(new Mitglied("hamburg1")); hamburg1.setAdresse(new Adresse("Hamburg", null));
		Mitglied hamburg2 = em.persistAndFlush(new Mitglied("hamburg2")); hamburg2.setAdresse(new Adresse("Hamburg", null));
		Mitglied bremen1  = em.persistAndFlush(new Mitglied("bremen1"));  bremen1 .setAdresse(new Adresse("Bremen" , null));
		
		// when
		Long anzahlOrte = repo.anzahlOrte();
		
		// then
		assertThat(anzahlOrte).isEqualTo(2);
	}
}
