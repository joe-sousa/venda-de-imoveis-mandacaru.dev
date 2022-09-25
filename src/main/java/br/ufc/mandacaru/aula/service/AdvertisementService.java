package br.ufc.mandacaru.aula.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru.aula.dto.AdvertisementDTO;
import br.ufc.mandacaru.aula.model.Advertisement;
import br.ufc.mandacaru.aula.model.User;
import br.ufc.mandacaru.aula.repository.AdvertisementRepository;
import br.ufc.mandacaru.aula.repository.UserRepository;

@Service
public class AdvertisementService {

	@Autowired
	AdvertisementRepository advertisementRepository;

	@Autowired
	UserRepository userRepository;

	public void save(int id, AdvertisementDTO entity) {
		Advertisement ad = new Advertisement();

		if (id != 0) {
			ad = findAdvertisement(id);
		}
		// ad.setAdvertisement_id(entity.getId());
		ad.setTitle(entity.getTitle());
		ad.setPrice(entity.getPrice());
		ad.setAddress(entity.getAddress());
		ad.setNote(entity.getNote());
		ad.setStatus(entity.getStatus());
		advertisementRepository.save(ad);
	}

	public void delete(int id) {
		Advertisement ad = findAdvertisement(id);
		advertisementRepository.delete(ad);
	}

	public AdvertisementDTO find(int id) {
		if (id < 1) {
			return null;
		}

		Optional<Advertisement> advertisement = advertisementRepository.findById(id);
		if (advertisement.isPresent()) {
			AdvertisementDTO dto = new AdvertisementDTO();
			dto.setId(advertisement.get().getAdvertisement_id());
			dto.setTitle(advertisement.get().getTitle());
			dto.setPrice(advertisement.get().getPrice());
			dto.setAddress(advertisement.get().getAddress());
			dto.setNote(advertisement.get().getNote());
			dto.setStatus(advertisement.get().getStatus());
			dto.setBuild(advertisement.get().getBuilding());
			dto.setUser(advertisement.get().getUser().getName());
			dto.setId_user(advertisement.get().getUser().getUser_id());
			return dto;
		}

		return null;
	}

	private Advertisement findAdvertisement(int id) {
		if (id < 1) {
			return null;
		}

		Optional<Advertisement> advertisement = advertisementRepository.findById(id);

		if (advertisement.isPresent()) {
			return advertisement.get();
		}

		return null;
	}

	public List<AdvertisementDTO> findAll() {
		List<Advertisement> list = advertisementRepository.findAll();
		List<AdvertisementDTO> listDTO = new ArrayList<AdvertisementDTO>();

		for (Advertisement ad : list) {
			AdvertisementDTO dto = new AdvertisementDTO();
			dto.setId(ad.getAdvertisement_id());
			dto.setTitle(ad.getTitle());
			dto.setPrice(ad.getPrice());
			dto.setAddress(ad.getAddress());
			dto.setNote(ad.getNote());
			dto.setStatus(ad.getStatus());
			dto.setBuild(ad.getBuilding());
			dto.setUser(ad.getUser().getName());
			dto.setId_user(ad.getUser().getUser_id());
			listDTO.add(dto);
		}

		return listDTO;
	}

	/*
	 * public AdvertisementDTO findByName(String str) { if (str.length() < 3) {
	 * return null; }
	 * 
	 * Advertisement ad = advertisementRepository.findByFirstname(str);
	 * 
	 * if (ad != null) { AdvertisementDTO dto = new AdvertisementDTO(); //
	 * dto.setId(ad.getAdvertisement_id()); dto.setTitle(ad.getTitle());
	 * dto.setPrice(ad.getPrice()); dto.setAddress(ad.getAddress());
	 * dto.setNote(ad.getNote()); dto.setStatus(ad.getStatus());
	 * //dto.setBuild(ad.getBuilding()); //dto.setId_user(ad.getUser().getUserId());
	 * //dto.setUser(ad.getUser().getName()); return dto; }
	 * 
	 * return null; }
	 */

	/*public Advertisement findAllByName(String name) { // TODO return return

		return advertisementRepository.findByFirstname(name);
	}*/

	public List<Advertisement> findByAddress(String address) { // TODO
		return advertisementRepository.getAdByAdress(address);

	}

	public List<Advertisement> findByPrice(double price) { // TODO
		return advertisementRepository.getAdByPrice(price);

	}

	public List<Advertisement> findByUserId(int id) { // TODO
		return advertisementRepository.getAdByUserId(id);

	}

	/*
	 * public List<Advertisement> findByPrice(double price) { // TODO return
	 * advertisementRepository.getAdByMaxPrice(price); }
	 */

	public void saveAdOnUser(int user_id, Advertisement advertisement) {
		User user = userRepository.findById(user_id).get();
		advertisement.setUser(user);
		advertisementRepository.save(advertisement);
	}

	/*
	 * public List<Advertisement> addBuildingsIntoAdvertisement(int id) { // TODO
	 * return advertisementRepository.addBuildings(id); }
	 */

}