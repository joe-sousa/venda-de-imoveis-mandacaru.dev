package br.ufc.mandacaru.aula.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru.aula.model.Advertisement;
import br.ufc.mandacaru.aula.model.Status;
import br.ufc.mandacaru.aula.repository.AdvertisementRepository;
import br.ufc.mandacaru.aula.repository.StatusRepository;

@Service
public class StatusService {

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	AdvertisementRepository adRepository;

	public void update(int id, Status entity) {
		Status status = find(id);
		status.setDescription(entity.getDescription());
		statusRepository.save(status);
	}

	public void save(int advertisement_id, Status entity) {
		Advertisement advertisement = adRepository.findById(advertisement_id).get();

		entity.setAdvertisement(advertisement);
		statusRepository.save(entity);

	}

	public void delete(int id) {
		Status status = find(id);
		statusRepository.delete(status);
	}

	public Status find(int id) {
		if (id < 1) {
			return null;
		}

		Optional<Status> status = statusRepository.findById(id);

		if (status.isPresent()) {
			return status.get();
		}

		return null;
	}

}