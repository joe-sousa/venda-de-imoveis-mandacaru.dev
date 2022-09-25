package br.ufc.mandacaru.aula.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru.aula.model.Login;
import br.ufc.mandacaru.aula.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/login")
public class LoginController {

	@Autowired
	LoginService service;

	@PostMapping
	public ResponseEntity<Login> save(@RequestBody Login login) {
		return new ResponseEntity<Login>(service.save(login), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<Login> find(@PathVariable("id") int id) {
		Login login = service.find(id);
		if (login != null) {
			return new ResponseEntity<Login>(login, HttpStatus.OK);
		} else {
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/search")
	public ResponseEntity<Login> find(@RequestParam("email") String email) {
		Login login = service.findLoginByEmail(email);
		if (login != null) {
			return new ResponseEntity<Login>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "{id}")
	public void update(@PathVariable("id") int id, @RequestBody Login login) {
		service.update(id, login);
	}

	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}

	@GetMapping
	public ResponseEntity<List<Login>> findAll() {
		return new ResponseEntity<List<Login>>(service.findAll(), HttpStatus.OK);
	}
}