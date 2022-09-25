package br.ufc.mandacaru.aula.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_generator")
	@SequenceGenerator(name = "status_generator", sequenceName = "status_seq", allocationSize = 1)
	private int id;
	private String description;

	@OneToOne
	@JoinColumn(name = "advertisement_id")
	@JsonIgnore
	private Advertisement advertisement;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", description=" + description + "]";
	}

}
