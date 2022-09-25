package br.ufc.mandacaru.aula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru.aula.dto.AdvertisementDTO;
import br.ufc.mandacaru.aula.model.Advertisement;
import br.ufc.mandacaru.aula.service.AdvertisementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/advertisement")
public class AdvertisementController {

	@Autowired
	AdvertisementService service;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<List<AdvertisementDTO>> findAll() {
		return new ResponseEntity<List<AdvertisementDTO>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<AdvertisementDTO> find(@PathVariable("id") int id) {
		AdvertisementDTO advertisement = service.find(id);
		if (advertisement != null) {
			return new ResponseEntity<AdvertisementDTO>(advertisement, HttpStatus.OK);
		} else {
			return new ResponseEntity<AdvertisementDTO>(HttpStatus.NOT_FOUND);
		}
	}

	/*@GetMapping(path = "/search")
	public ResponseEntity<Advertisement> find(@RequestParam("title") String name) {
		Advertisement advertisement = service.findAllByName(name);
		if (advertisement != null) {
			return new ResponseEntity<Advertisement>(advertisement, HttpStatus.OK);
		} else {
			return new ResponseEntity<Advertisement>(HttpStatus.NOT_FOUND);
		}
	}*/

	/*
	 * @GetMapping(path = "/searchAllByName") public
	 * ResponseEntity<List<Advertisement>> findEntity(@RequestParam("title") String
	 * title) { return new
	 * ResponseEntity<List<Advertisement>>(service.findAllByName(title),
	 * HttpStatus.OK);
	 * 
	 * }
	 */

	@PostMapping("/user/{id}/ad")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void save(@PathVariable("id") int user_id, @RequestBody Advertisement advertisement) {
		service.saveAdOnUser(user_id, advertisement);
	}

	@GetMapping(path = "/searchAllByAddress")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<List<Advertisement>> findEntity1(@RequestParam("address") String address) {
		return new ResponseEntity<List<Advertisement>>(service.findByAddress(address), HttpStatus.OK);
	}

	@GetMapping(path = "/searchAllbyUserid")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Advertisement>> findEntity(@RequestParam("user_user_id") int id) {
		return new ResponseEntity<List<Advertisement>>(service.findByUserId(id), HttpStatus.OK);
	}

	/*
	 * @GetMapping(path = "/searchPrice") public ResponseEntity<List<Advertisement>>
	 * find(@RequestParam("price") double price) { List<Advertisement> advertisement
	 * = service.findByPrice(price);
	 * 
	 * if (advertisement != null) { return new
	 * ResponseEntity<List<Advertisement>>(advertisement, HttpStatus.OK); } else {
	 * return new ResponseEntity<List<Advertisement>>(HttpStatus.NOT_FOUND); } }
	 */

	@GetMapping(path = "/searchPrice")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public ResponseEntity<List<Advertisement>> find(@RequestParam("price") double price) {
		List<Advertisement> advertisement = service.findByPrice(price);

		if (advertisement != null) {
			return new ResponseEntity<List<Advertisement>>(advertisement, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Advertisement>>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public void save(@RequestBody AdvertisementDTO advertisement) {
		service.save(0, advertisement);
	}

	@PutMapping(path = "{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void update(@PathVariable("id") int id, @RequestBody AdvertisementDTO advertisement) {
		service.save(id, advertisement);
	}

	@DeleteMapping(path = "{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}

	/*
	 * @PostMapping("/build/{id}") public void
	 * addBuildIntoAdvertisement(@PathVariable("id") int id, @RequestBody
	 * AdvertisementDTO advertisement) { service.addBuildingsIntoAdvertisement(id);
	 * }
	 */

}
