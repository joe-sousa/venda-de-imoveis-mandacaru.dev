package br.ufc.mandacaru.aula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru.aula.model.Status;
import br.ufc.mandacaru.aula.service.StatusService;

//Corrigir status proibindo atrelar dois id's iguais a diferentes anuncios.
@RestController
@RequestMapping(path = "/api")
public class StatusController {

	@Autowired
	StatusService service;

	@GetMapping("/status/{id}")
	public ResponseEntity<Status> find(@PathVariable("id") int id) {
		return new ResponseEntity<Status>(service.find(id), HttpStatus.OK);
	}

	@PostMapping("/statusAnuncio/{id}/status")
	public void save(@PathVariable("id") int product_id, @RequestBody Status status) {
		service.save(product_id, status);
	}

	@PutMapping("/status/{id}")
	public void update(@PathVariable("id") int id, @RequestBody Status status) {
		service.update(id, status);
	}

	@DeleteMapping("/status/{id}")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}
}