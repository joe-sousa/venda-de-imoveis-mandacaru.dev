package br.ufc.mandacaru.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.mandacaru.aula.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findFirstByName(String name);
	
	//User findById(int id);
}