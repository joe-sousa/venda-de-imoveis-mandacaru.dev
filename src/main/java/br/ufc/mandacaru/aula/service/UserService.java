package br.ufc.mandacaru.aula.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.mandacaru.aula.dto.UserDTO;
import br.ufc.mandacaru.aula.model.User;
import br.ufc.mandacaru.aula.repository.AdvertisementRepository;
import br.ufc.mandacaru.aula.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdvertisementRepository adRepository;

	public void save(int id, UserDTO entity) {
		User user = new User();

		if (id != 0) {
			user = findUser(id);
		}
		// user.setUser_id(entity.getUser_id());
		user.setName(entity.getName());
		user.setSsn(entity.getSsn());
		user.setEmail(entity.getEmail());
		user.setAddress_user(entity.getAddress_user());
		user.setNeighborhood(entity.getNeighborhood());
		user.setHome_number(entity.getHome_number());
		user.setHome_reference(entity.getHome_reference());
		user.setPhone(entity.getPhone());
		user.setPassword(entity.getPassword());
		// Criar Anuncios no UserDTO
		// user.setAdvertisement3();
		userRepository.save(user);
	}

	public void delete(int id) {
		User user = findUser(id);
		userRepository.delete(user);
	}

	public UserDTO find(int id) {
		if (id < 1) {
			return null;
		}

		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			UserDTO user_dto = new UserDTO();
			user_dto.setUser_id(user.get().getUser_id());
			user_dto.setName(user.get().getName());
			user_dto.setSsn(user.get().getSsn());
			user_dto.setAddress_user(user.get().getAddress_user());
			user_dto.setNeighborhood(user.get().getNeighborhood());
			user_dto.setHome_number(user.get().getHome_number());
			user_dto.setHome_reference(user.get().getHome_reference());
			user_dto.setPhone(user.get().getPhone());
			user_dto.setEmail(user.get().getEmail());
			user_dto.setPassword(user.get().getPassword());
			user_dto.setAdvertisement(user.get().getAdvertisement3());
			return user_dto;
		}

		return null;
	}

	private User findUser(int id) {
		if (id < 1) {
			return null;
		}

		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();
		}

		return null;
	}

	public List<UserDTO> findAll() {
		List<User> list = userRepository.findAll();
		List<UserDTO> listDTO = new ArrayList<UserDTO>();

		for (User user : list) {
			UserDTO user_dto = new UserDTO();
			user_dto.setUser_id(user.getUser_id());
			user_dto.setSsn(user.getSsn());
			user_dto.setName(user.getName());
			user_dto.setEmail(user.getEmail());
			user_dto.setPhone(user.getPhone());
			user_dto.setAddress_user(user.getAddress_user());
			user_dto.setNeighborhood(user.getNeighborhood());
			user_dto.setHome_number(user.getHome_number());
			user_dto.setHome_reference(user.getHome_reference());
			user_dto.setPassword(user.getPassword());
			user_dto.setAdvertisement(user.getAdvertisement3());
			listDTO.add(user_dto);
		}

		return listDTO;
	}

	public UserDTO findByName(String str) {
		if (str.length() < 3) {
			return null;
		}

		User user = userRepository.findFirstByName(str);

		if (user != null) {
			UserDTO user_dto = new UserDTO();
			user_dto.setUser_id(user.getUser_id());
			;
			user_dto.setName(user.getName());
			user_dto.setEmail(user.getEmail());
			user_dto.setPhone(user.getPhone());
			user_dto.setPassword(user.getPassword());
			user_dto.setAddress_user(user.getAddress_user());
			user_dto.setHome_number(user.getHome_number());
			user_dto.setNeighborhood(user.getNeighborhood());
			user_dto.setHome_reference(user.getHome_reference());
			user_dto.setSsn(user.getSsn());
			user_dto.setAdvertisement(user.getAdvertisement3());
			return user_dto;
		}

		return null;
	}

	/*
	 * public List<Advertisement> findAllByName(String name) { // TODO return
	 * advertisementRepository.findAllByName(name); }
	 */

	/*
	 * public List<Advertisement> findByCity(String city) { // TODO return
	 * advertisementRepository.getAdByCity(city);
	 * 
	 * }
	 */

	/*
	 * public List<Advertisement> addBuildingsIntoAdvertisement(int id) { // TODO
	 * return advertisementRepository.addBuildings(id); }
	 */

}
