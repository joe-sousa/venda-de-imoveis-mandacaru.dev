package br.ufc.mandacaru.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.mandacaru.aula.model.Advertisement;

@Repository
public interface ReportSolicitationRepository extends JpaRepository<Advertisement, Integer> {

	@Query(value = "select * from advertisement where advertisement_id = :id", nativeQuery = true)
	List<Advertisement> getAdById(int id);
}
