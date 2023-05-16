package de.woock.infra.messaging;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class LivenessEventListener {
    
    @EventListener
    public void onEvent(AvailabilityChangeEvent<LivenessState> event) {
        switch (event.getState()) {
        case BROKEN:
            log.debug("Liveness broken");
            break;
        case CORRECT:
        	log.debug("Liveness correct");
        }
    }
}