package de.woock.infra.exceptions;

@SuppressWarnings("serial")
public class MitgliedNichtGefundenException extends RuntimeException {
	
	public MitgliedNichtGefundenException(Long id) {
		super(String.format("Mitglied nicht gefunden. [id=%s]", id));
	}

}
