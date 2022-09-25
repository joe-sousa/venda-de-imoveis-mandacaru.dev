package br.ufc.mandacaru.aula.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Advertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advertisement_generator")
	@SequenceGenerator(name = "advertisement_generator", sequenceName = "advertisement_seq", allocationSize = 1)
	private int advertisement_id;
	private String title;
	private double price;
	private String address;
	private String note;

	@OneToOne(mappedBy = "advertisement")
	private Status status;

	@OneToOne(mappedBy = "advertisement2")
	Building building;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	public Advertisement(int advertisement_id, String title, double price, String address, String note, Status status,
			Building building, User user) {
		super();
		this.advertisement_id = advertisement_id;
		this.title = title;
		this.price = price;
		this.address = address;
		this.note = note;
		this.status = status;
		this.building = building;
		this.user = user;
	}

	public Advertisement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAdvertisement_id() {
		return advertisement_id;
	}

	public void setAdvertisement_id(int advertisement_id) {
		this.advertisement_id = advertisement_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Advertisement [advertisement_id=" + advertisement_id + ", title=" + title + ", price=" + price
				+ ", address=" + address + ", note=" + note + ", status=" + status + ", building=" + building
				+ ", user=" + user + "]";
	}

}
