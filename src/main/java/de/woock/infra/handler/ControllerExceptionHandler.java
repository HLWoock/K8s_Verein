package de.woock.infra.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.woock.infra.exceptions.MitgliedNichtGefundenException;
import de.woock.infra.exceptions.MitgliedSchonVorhandenException;

import java.net.URI;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleBadRequestException(MitgliedSchonVorhandenException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problemDetail.setType(URI.create("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400"));

        log.error("Exception: ", exception);

        return problemDetail;
    }

    @ExceptionHandler
    public ProblemDetail handleNotFoundException(MitgliedNichtGefundenException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setType(URI.create("https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/404"));

        log.error("Exception: ", exception);

        return problemDetail;
    }
}