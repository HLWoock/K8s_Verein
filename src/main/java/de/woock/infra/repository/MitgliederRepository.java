package de.woock.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.woock.domain.Mitglied;
import de.woock.domain.status.Vertragsart;

public interface MitgliederRepository extends JpaRepository<Mitglied, Long> {
	
	Optional<Mitglied> findByUsernameIgnoreCase(String username);
	
	long countByVertragsart(Vertragsart vertragsart);

	@Query("SELECT COUNT(DISTINCT mc.ort) FROM Mitglied mt JOIN mt.adresse mc")
    long anzahlOrte();
    


}
