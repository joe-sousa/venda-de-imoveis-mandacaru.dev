package br.ufc.mandacaru.aula.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ufc.mandacaru.aula.dto.AdvertisementDTO;
import br.ufc.mandacaru.aula.model.Advertisement;
import br.ufc.mandacaru.aula.model.Building;
import br.ufc.mandacaru.aula.model.Status;
import br.ufc.mandacaru.aula.model.User;
import br.ufc.mandacaru.aula.repository.AdvertisementRepository;

public class AdvertisementServiceTest {
	private static final int ID = 1;
	private static final String NAME = "terreno";
	private static final double PRICE = 450.0;
	private static final String ADDRESS = "RUA SANTO ANTONIO";
	private static final String NOTE = "PIX";
	private static final String USUARIO = "JOEL";
	private static final int ID_USER = 3;

	private static int BUILDING_ID = 1;
	private static int LAND_SIZE = 300;
	private int BUILD_AREA_SIZE = 250;
	private int QUANTITY_BEDROOM = 4;
	private int QUANTITY_BATHROOM = 2;
	private int QUANTITY_GARAGE = 1;

	private static final int ID_STATUS1 = 1;
	private static final int ID_STATUS2 = 2;
	private static final String MESSAGE_STATUS1 = "PRONTO";
	private static final String MESSAGE_STATUS2 = "ANALISE";

	private static final int USER_ID = 1;
	private static final String NAME_USER = "JOEL SOUSA";
	private static final String email = "joe_sousa@bol.com.br";
	private static final String password = "12345";
	private static final String ssn = "123131";
	private static final String phone = "8598765231";
	private static final String address_user = "RUA SAO JOAO";
	private static final String neighborhood = "COHAB";
	private static final String home_number = "869873456";
	private static final String home_reference = "PIZZARIA DO GUI";
	private static final String note_user = "SEM ALTERAÇÕES";

	@InjectMocks
	private AdvertisementService service;
	@Mock
	private AdvertisementRepository repository;
	private Advertisement advertisement;
	private Building build;
	private Status status;

	private void start() {
		Status status1 = new Status();
		status1.setId(ID_STATUS1);

		status1.setDescription(MESSAGE_STATUS1);

		Status status2 = new Status();
		status2.setId(ID_STATUS2);
		status2.setDescription(MESSAGE_STATUS2);

		User user = new User();

		user.setUser_id(USER_ID);
		user.setName(NAME_USER);
		user.setEmail(email);
		user.setPassword(password);
		user.setSsn(ssn);
		user.setPhone(phone);
		user.setAddress_user(address_user);
		user.setNeighborhood(neighborhood);
		user.setHome_number(home_number);
		user.setHome_reference(home_reference);
		user.setNote_user(note_user);

		build.setBuilding_id(BUILDING_ID);
		build.setLand_size(LAND_SIZE);
		build.setBuild_area_size(BUILD_AREA_SIZE);
		build.setQuantity_bedrooms(QUANTITY_BEDROOM);
		build.setQuantity_bathrooms(QUANTITY_BATHROOM);
		build.setQuantity_garage(QUANTITY_GARAGE);

		advertisement.setAdvertisement_id(ID);
		advertisement.setTitle(NAME);
		advertisement.setPrice(PRICE);
		advertisement.setAddress(ADDRESS);
		advertisement.setNote(NOTE);
		advertisement.setStatus(status1);
		advertisement.setBuilding(build);
		advertisement.setUser(user);
		
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		start();
	}

	/*@Test
	public void whenFindByIdThenReturnAdvertisement() {
		Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(advertisement));
		AdvertisementDTO response = service.find(ID);
		
		Assertions.assertEquals(Advertisement.class, response.getClass());
	}*/

}
