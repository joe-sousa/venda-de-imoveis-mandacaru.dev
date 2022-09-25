package br.ufc.mandacaru.aula.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru.aula.dto.BuildingDTO;
import br.ufc.mandacaru.aula.model.Advertisement;
import br.ufc.mandacaru.aula.model.Building;
import br.ufc.mandacaru.aula.repository.AdvertisementRepository;
import br.ufc.mandacaru.aula.repository.BuildingRepository;

@Service
public class BuildingService {
	@Autowired
	BuildingRepository buildingRepository;

	@Autowired
	AdvertisementRepository adRepository;

	public void save(int id, BuildingDTO entity) {
		Building build = new Building();

		if (id != 0) {
			build = findBuild(id);
		}
		// ad.setAdvertisement_id(entity.getId());
		build.setBuild_area_size(entity.getBuild_area_size());
		build.setLand_size(entity.getLand_size());
		build.setQuantity_bathrooms(entity.getQuantity_bathrooms());
		build.setQuantity_bedrooms(entity.getQuantity_bedrooms());
		build.setQuantity_garage(entity.getQuantity_garage());
		build.setBuilding_id(id);
		buildingRepository.save(build);
	}

	public void saveBuildOnAd(int advertisement_id, Building build) {
		Advertisement advertisement = adRepository.findById(advertisement_id).get();
		build.setAdvertisement2(advertisement);
		buildingRepository.save(build);
	}

	public void delete(int id) {
		Building build = findBuild(id);
		buildingRepository.delete(build);
	}

	public BuildingDTO find(int id) {
		if (id < 1) {
			return null;
		}

		Optional<Building> build = buildingRepository.findById(id);

		if (build.isPresent()) {
			BuildingDTO build_dto = new BuildingDTO();
			build_dto.setLand_size(build.get().getLand_size());
			build_dto.setBuild_area_size(build.get().getBuild_area_size());
			build_dto.setQuantity_bathrooms(build.get().getQuantity_bathrooms());
			build_dto.setQuantity_bedrooms(build.get().getQuantity_bedrooms());
			build_dto.setQuantity_garage(build.get().getQuantity_garage());
			build_dto.setBuilding_id(build.get().getBuilding_id());
			return build_dto;
		}

		return null;
	}

	private Building findBuild(int id) {
		if (id < 1) {
			return null;
		}

		Optional<Building> build = buildingRepository.findById(id);

		if (build.isPresent()) {
			return build.get();
		}

		return null;
	}

	public List<BuildingDTO> findAll() {
		List<Building> list = buildingRepository.findAll();
		List<BuildingDTO> listDTO = new ArrayList<BuildingDTO>();

		for (Building build : list) {
			BuildingDTO build_dto = new BuildingDTO();
			build_dto.setBuilding_id(build.getBuilding_id());
			build_dto.setLand_size(build.getLand_size());
			build_dto.setBuild_area_size(build.getBuild_area_size());
			build_dto.setQuantity_bathrooms(build.getQuantity_bathrooms());
			build_dto.setQuantity_bedrooms(build.getQuantity_bedrooms());
			build_dto.setQuantity_garage(build.getQuantity_garage());
			listDTO.add(build_dto);
		}

		return listDTO;
	}

	/*
	 * public AdvertisementDTO findByName(String str) { if (str.length() < 3) {
	 * return null; }
	 * 
	 * Advertisement ad = advertisementRepository.findFirstByName(str);
	 * 
	 * if (ad != null) { AdvertisementDTO dto = new AdvertisementDTO();
	 * dto.setId(ad.getAdvertisement_id()); dto.setTitle(ad.getTitle());
	 * dto.setPrice(ad.getPrice()); dto.setCity(ad.getCity());
	 * dto.setNote(ad.getNote()); dto.setStatus(ad.getStatus()); return dto; }
	 * 
	 * return null; }
	 */

	/*
	 * public List<Advertisement> findAllByName(String name) { // TODO return
	 * advertisementRepository.findAllByName(name); }
	 */

}
