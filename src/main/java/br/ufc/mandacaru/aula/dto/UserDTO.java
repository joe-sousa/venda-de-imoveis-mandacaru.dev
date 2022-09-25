package br.ufc.mandacaru.aula.dto;

import java.util.List;

import br.ufc.mandacaru.aula.model.Advertisement;

public class UserDTO {
	private int user_id;
	private String name;
	private String email;
	private String password;
	private String ssn;
	private String phone;
	private String address_user;
	private String neighborhood;
	private String home_number;
	private String home_reference;
	private List<Advertisement> advertisement;

	public UserDTO(int user_id, String name, String email, String password, String ssn, String phone,
			String address_user, String neighborhood, String home_number, String home_reference,
			List<Advertisement> advertisement) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.ssn = ssn;
		this.phone = phone;
		this.address_user = address_user;
		this.neighborhood = neighborhood;
		this.home_number = home_number;
		this.home_reference = home_reference;
		this.advertisement = advertisement;
	}

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Advertisement> getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(List<Advertisement> advertisement) {
		this.advertisement = advertisement;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress_user() {
		return address_user;
	}

	public void setAddress_user(String address_user) {
		this.address_user = address_user;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getHome_number() {
		return home_number;
	}

	public void setHome_number(String home_number) {
		this.home_number = home_number;
	}

	public String getHome_reference() {
		return home_reference;
	}

	public void setHome_reference(String home_reference) {
		this.home_reference = home_reference;
	}

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", ssn=" + ssn + ", phone=" + phone + ", address_user=" + address_user + ", neighborhood="
				+ neighborhood + ", home_number=" + home_number + ", home_reference=" + home_reference
				+ ", advertisement=" + advertisement + "]";
	}

}
