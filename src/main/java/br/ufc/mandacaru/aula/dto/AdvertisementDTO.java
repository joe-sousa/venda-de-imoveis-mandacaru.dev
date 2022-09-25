package br.ufc.mandacaru.aula.dto;

import br.ufc.mandacaru.aula.model.Building;
import br.ufc.mandacaru.aula.model.Status;

public class AdvertisementDTO {
	private int id;
	private String title;
	private double price;
	private String address;
	private String note;
	private Status status;
	private Building build;
	private String user;
	private int id_user;

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AdvertisementDTO() {
		super();
	}

	public Building getBuild() {
		return build;
	}

	public void setBuild(Building build) {
		this.build = build;
	}

	public AdvertisementDTO(int id, String title, double price, String address, String note, Status status,
			Building build, String user, int id_user) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.address = address;
		this.note = note;
		this.status = status;
		this.build = build;
		this.user = user;
		this.id_user = id_user;
	}

	@Override
	public String toString() {
		return "AdvertisementDTO [id=" + id + ", title=" + title + ", price=" + price + ", address=" + address
				+ ", note=" + note + ", status=" + status + ", build=" + build + ", user=" + user + ", id_user="
				+ id_user + "]";
	}

}
