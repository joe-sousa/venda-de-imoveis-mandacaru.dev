package br.ufc.mandacaru.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufc.mandacaru.aula.model.Building;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
	
}
