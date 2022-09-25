package br.ufc.mandacaru.aula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MandacaruApplicattion {
	public static void main(String[] args) {
		SpringApplication.run(MandacaruApplicattion.class, args);
		// System.out.println(new BCryptPasswordEncoder().encode("teste"));
	}
}
