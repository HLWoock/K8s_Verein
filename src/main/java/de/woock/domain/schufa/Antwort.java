package de.woock.domain.schufa;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.woock.domain.status.Bonitaet;

public record Antwort (@JsonProperty("bonitaet") Bonitaet bonitaet,
                       @JsonProperty("name")     String name) {}