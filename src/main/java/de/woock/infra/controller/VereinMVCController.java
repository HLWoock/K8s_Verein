package de.woock.infra.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import de.woock.domain.Mitglied;
import de.woock.domain.MitgliedsAntrag;
import de.woock.infra.service.MitgliederService;
import de.woock.infra.service.MitgliederStatistikService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Observed
@RequiredArgsConstructor
@Controller
public class VereinMVCController {
	
	private final MitgliederService mitgliederService;
	private final MitgliederStatistikService mitgliederStatistikService;

	@GetMapping({"/", "/home", "/index"})
	public ModelAndView home() {
		ModelAndView view = new ModelAndView("index");
		view.addObject("mitglieder"  , mitgliederStatistikService.anzahlMitglieder());
		view.addObject("orte"        , mitgliederStatistikService.anzahlStaedte());
		view.addObject("privatkunden", mitgliederStatistikService.anzahlPrivatkunden());
		view.addObject("firmenkunden", mitgliederStatistikService.anzahlFirmenkunden());
		return view;
	}
	
	@GetMapping("/mitglieder")
	public ModelAndView mitglieder() {
		ModelAndView view = new ModelAndView("mitglieder");
		view.addObject("mitglieder", mitgliederService.mitglieder());
		return view;

	}
	
	@GetMapping("/antrag")
	public ModelAndView antrag() {
		ModelAndView view = new ModelAndView("antrag", "antrag", new MitgliedsAntrag("", "", "", "", "", ""));
		return view;
	}
	
	@PostMapping("/antrag")
	public String submitAntrag(MitgliedsAntrag antrag) {
		log.debug("Antrag fuer Mitglied {} {}", antrag.vorname(), antrag.name());
		mitgliederService.aufnehmen(antrag);
		return "redirect:/mitglieder";
	}
	
	@GetMapping("/mitglieder/{page}")
	public ModelAndView mitglieder(@PathVariable() Integer page) {
		ModelAndView view = new ModelAndView("mitglieder");
		Page<Mitglied> paged = mitgliederService.mitglieder(page);
		view.addObject("mitglieder", paged.toList());
		view.addObject("anzahl", paged.getSize());
		return view;
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
}
