package br.ufc.mandacaru.aula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru.aula.service.ReportSolicitationService;

@RestController
@RequestMapping("/api/advertisement/exporter/")
public class ReportSolicitationController {

	@Autowired
	private ReportSolicitationService reportSolicitationService;

	/*
	 * @GetMapping("/report") public String generateReport() {
	 * 
	 * return reportSolicitationService.generateReport(); }
	 */

	@GetMapping(path = "{id}")
	public String find(@PathVariable("id") int id) {
		return reportSolicitationService.generateReport(id);
	}
}
