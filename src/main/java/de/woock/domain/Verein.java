package de.woock.domain;

import java.util.List;

import de.woock.domain.personalien.Adresse;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
public class Verein extends Kopfdaten {
	 
	          private String         name;
	          private List<Mitglied> mitglieder;
	@Embedded private Adresse        adresse; 
	
	public Mitglied mitgliedschaftBeantragen(MitgliedsAntrag mitgliedsAntrag) {
		// Bonität prüfen
		// Mitglied aufnehmen oder ablehnen
		return null;
	}

}
