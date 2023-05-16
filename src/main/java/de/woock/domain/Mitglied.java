package de.woock.domain;

import static jakarta.persistence.EnumType.STRING;

import java.time.LocalDate;

import de.woock.domain.personalien.Adresse;
import de.woock.domain.personalien.Anrede;
import de.woock.domain.personalien.Geschlecht;
import de.woock.domain.status.Bonitaet;
import de.woock.domain.status.Vertragsart;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@SuppressWarnings("serial")
@Builder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Mitglied extends Kopfdaten {
	
	
	@Enumerated(STRING)    Anrede      anrede;
						   String      vorname;
     					   String      name;
	@Column(unique = true) String      username;
	                       LocalDate   geburtsdatum;
	                       String      telefon;
	                       String      mobil;
    					   String      email;
    @Embedded              Adresse     adresse;
    @Enumerated(STRING)	   Geschlecht  geschlecht;
    @Enumerated(STRING)    Bonitaet    bonitaet;
    @Enumerated(STRING)    Vertragsart vertragsart;

    public Mitglied(String username) {
    	this.username = username;
    }
    
    public Mitglied(@Valid MitgliedsAntrag antrag) {
    	// TODO Auto-generated constructor stub
    }
}
