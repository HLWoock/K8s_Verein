package de.woock.infra.exceptions;

@SuppressWarnings("serial")
public class MitgliedSchonVorhandenException extends RuntimeException {
	
	public MitgliedSchonVorhandenException(String username) {
		super(String.format("Mitglied existiert schon. [Username=%s]", username));
	}

}
