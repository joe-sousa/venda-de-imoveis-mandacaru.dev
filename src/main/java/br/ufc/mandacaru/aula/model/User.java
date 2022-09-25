package br.ufc.mandacaru.aula.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
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
	private String note_user;

	@OneToMany(mappedBy = "user")
	private List<Advertisement> advertisement3;

	public User(int user_id, String name, String email, String password, String ssn, String phone, String address_user,
			String neighborhood, String home_number, String home_reference, String note_user,
			List<Advertisement> advertisement3) {
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
		this.note_user = note_user;
		this.advertisement3 = advertisement3;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getNote_user() {
		return note_user;
	}

	public void setNote_user(String note_user) {
		this.note_user = note_user;
	}

	public List<Advertisement> getAdvertisement3() {
		return advertisement3;
	}

	public void setAdvertisement3(List<Advertisement> advertisement3) {
		this.advertisement3 = advertisement3;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password=" + password + ", ssn="
				+ ssn + ", phone=" + phone + ", address_user=" + address_user + ", neighborhood=" + neighborhood
				+ ", home_number=" + home_number + ", home_reference=" + home_reference + ", note_user=" + note_user
				+ ", advertisement3=" + advertisement3 + "]";
	}

}
