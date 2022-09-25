package br.ufc.mandacaru.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.mandacaru.aula.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

	Login findByEmail(String email);
}
