package br.ufc.mandacaru.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.mandacaru.aula.dto.AdvertisementDTO;
import br.ufc.mandacaru.aula.model.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
	//Advertisement findByFirstname(String name);

	// List<Advertisement> findAllByName(String title);

	/*@Query(value = "select advertisement_id, address, note, price, title, user_id from advertisement join user on user_id = user_id where price = :maxprice", nativeQuery = true)
	List<Advertisement> getAdByMaxPrice(double maxprice);*/

	@Query(value = "select * from advertisement where address = :address", nativeQuery = true)
	List<Advertisement> getAdByAdress(String address);

	@Query(value = "select * from advertisement where user_user_id = :id", nativeQuery = true)
	List<Advertisement> getAdByUserId(int id);

	@Query(value = "select * from advertisement where price = :priceAd", nativeQuery = true)
	List<Advertisement> getAdByPrice(double priceAd);

}
