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
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru.aula.dto.BuildingDTO;
import br.ufc.mandacaru.aula.model.Building;
import br.ufc.mandacaru.aula.service.BuildingService;

//Consultas http não estão rolando para consultas por nome
@RestController
@RequestMapping(path = "/api/build/")
public class BuildingController {

	@Autowired
	BuildingService service;

	@GetMapping
	public ResponseEntity<List<BuildingDTO>> findAll() {
		return new ResponseEntity<List<BuildingDTO>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<BuildingDTO> find(@PathVariable("id") int id) {
		BuildingDTO build = service.find(id);
		if (build != null) {
			return new ResponseEntity<BuildingDTO>(build, HttpStatus.OK);
		} else {
			return new ResponseEntity<BuildingDTO>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * @GetMapping(path = "/search") public ResponseEntity<AdvertisementDTO>
	 * find(@RequestParam("title") String title) { AdvertisementDTO advertisement =
	 * service.findByName(title); if (advertisement != null) { return new
	 * ResponseEntity<AdvertisementDTO>(advertisement, HttpStatus.OK); } else {
	 * return new ResponseEntity<AdvertisementDTO>(HttpStatus.NOT_FOUND); } }
	 */

	/*
	 * @GetMapping(path = "/searchAllByName") public
	 * ResponseEntity<List<Advertisement>> findEntity(@RequestParam("title") String
	 * title) { return new
	 * ResponseEntity<List<Advertisement>>(service.findAllByName(title),
	 * HttpStatus.OK);
	 * 
	 * }
	 */

	@PostMapping
	public void save(@RequestBody BuildingDTO build) {
		service.save(0, build);
	}

	@PutMapping(path = "{id}")
	public void update(@PathVariable("id") int id, @RequestBody BuildingDTO build) {
		service.save(id, build);
	}

	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}
	
	@PostMapping("/advertisement/{id}/ad")
	public void save(@PathVariable("id") int ad_id, @RequestBody Building build) {
		service.saveBuildOnAd(ad_id, build);
	}
}
