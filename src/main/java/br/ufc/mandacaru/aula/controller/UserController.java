package br.ufc.mandacaru.aula.controller;

import java.util.List;

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

import br.ufc.mandacaru.aula.dto.UserDTO;
import br.ufc.mandacaru.aula.service.UserService;

@RestController
@RequestMapping(path = "/api/user/")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		return new ResponseEntity<List<UserDTO>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<UserDTO> find(@PathVariable("id") int id) {
		UserDTO user = service.find(id);
		if (user != null) {
			return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/search")
	public ResponseEntity<UserDTO> find(@RequestParam("name") String name) {
		UserDTO advertisement = service.findByName(name);
		if (advertisement != null) {
			return new ResponseEntity<UserDTO>(advertisement, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public void save(@RequestBody UserDTO user) {
		service.save(0, user);
	}

	@PutMapping(path = "{id}")
	public void update(@PathVariable("id") int id, @RequestBody UserDTO user) {
		service.save(id, user);
	}

	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}

}
