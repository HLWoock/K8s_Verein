package de.woock.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.woock.domain.Mitglied;
import de.woock.domain.status.Vertragsart;

public interface MitgliederRepository extends JpaRepository<Mitglied, Long> {
	
    @Query("SELECT COUNT(DISTINCT mc.ort) FROM Mitglied mt JOIN mt.adresse mc")
    long anzahlOrte();
    
    long countByVertragsart(Vertragsart vertragsart);

	Mitglied findByUsername(String name);
	
	Optional<Mitglied> findByUsernameIgnoreCase(String username);

}
