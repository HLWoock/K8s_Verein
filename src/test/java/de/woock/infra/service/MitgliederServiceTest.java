package de.woock.infra.service;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import de.woock.domain.Mitglied;
import de.woock.domain.MitgliedsAntrag;
import de.woock.infra.exceptions.MitgliedSchonVorhandenException;
import de.woock.infra.repository.MitgliederRepository;

@SpringBootTest
public class MitgliederServiceTest {
	
	@MockBean
	private MitgliederRepository repo;
	
	@Autowired
	private MitgliederService service;
	
	@Test
	public void mitgliedAufnehmen() {
		// given
		MitgliedsAntrag mitgliedsAntrag = new MitgliedsAntrag(null, null, null, "Hermann", null, null, null);
		Mitglied        mitglied        = new Mitglied(mitgliedsAntrag);
		given(repo.findByUsernameIgnoreCase("Hermann")).willReturn(Optional.empty());
		given(repo.save(mitglied)).willReturn(mitglied);
		
		// when 
		Mitglied aufgenommenesMitglied = service.aufnehmen(mitgliedsAntrag);
		
		// then
		then(aufgenommenesMitglied.getUsername()).as("username")
		                                         .isEqualTo(mitgliedsAntrag.username());
	}
	
	@Test
	public void doppelterUsername() {
		// given
		MitgliedsAntrag antrag = new MitgliedsAntrag(null, null, null, "Mitglied", null, null, null);
		Mitglied        mitglied        = new Mitglied(antrag);
		given(repo.findByUsernameIgnoreCase(antrag.username())).willThrow(new MitgliedSchonVorhandenException("Mitglied"));
		given(repo.save(mitglied)).willReturn(mitglied);
		
		// when 
		Throwable thrown = catchThrowable(() -> {service.aufnehmen(antrag);});
		
		// then
	    then(thrown).isInstanceOf(MitgliedSchonVorhandenException.class)
  	                .hasMessageContaining("Mitglied");
	}	
	
	@Test
	public void mitgliedNichtGefunden() {
		// given
		String username = "Mitglied";
		given(repo.findByUsernameIgnoreCase(username)).willThrow(new MitgliedSchonVorhandenException(username));
		
		// when 
		Throwable thrown = catchThrowable(() -> {service.mitglied(username);});
		
		// then
		then(thrown).isInstanceOf(MitgliedSchonVorhandenException.class)
		            .hasMessageContaining("Mitglied");
	}	

}
