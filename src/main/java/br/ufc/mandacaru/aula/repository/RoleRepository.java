package br.ufc.mandacaru.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufc.mandacaru.aula.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

