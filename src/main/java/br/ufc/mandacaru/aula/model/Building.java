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
public class Building {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "building_generator")
	@SequenceGenerator(name = "building_generator", sequenceName = "building_seq", allocationSize = 1)
	private int building_id;
	private int land_size;
	private int build_area_size;
	private int quantity_bedrooms;
	private int quantity_bathrooms;
	private int quantity_garage;
	private boolean buildingIsNew;

	@OneToOne
	@JoinColumn(name = "advertisement_id")
	@JsonIgnore
	private Advertisement advertisement2;

	public int getBuilding_id() {
		return building_id;
	}

	public void setBuilding_id(int building_id) {
		this.building_id = building_id;
	}

	public int getLand_size() {
		return land_size;
	}

	public void setLand_size(int land_size) {
		this.land_size = land_size;
	}

	public int getBuild_area_size() {
		return build_area_size;
	}

	public void setBuild_area_size(int build_area_size) {
		this.build_area_size = build_area_size;
	}

	public int getQuantity_bedrooms() {
		return quantity_bedrooms;
	}

	public void setQuantity_bedrooms(int quantity_bedrooms) {
		this.quantity_bedrooms = quantity_bedrooms;
	}

	public int getQuantity_bathrooms() {
		return quantity_bathrooms;
	}

	public void setQuantity_bathrooms(int quantity_bathrooms) {
		this.quantity_bathrooms = quantity_bathrooms;
	}

	public int getQuantity_garage() {
		return quantity_garage;
	}

	public void setQuantity_garage(int quantity_garage) {
		this.quantity_garage = quantity_garage;
	}

	public boolean isBuildingIsNew() {
		return buildingIsNew;
	}

	public void setBuildingIsNew(boolean buildingIsNew) {
		this.buildingIsNew = buildingIsNew;
	}

	public Advertisement getAdvertisement2() {
		return advertisement2;
	}

	public void setAdvertisement2(Advertisement advertisement2) {
		this.advertisement2 = advertisement2;
	}

	public Building(int building_id, int land_size, int build_area_size, int quantity_bedrooms, int quantity_bathrooms,
			int quantity_garage, boolean buildingIsNew, Advertisement advertisement2) {
		super();
		this.building_id = building_id;
		this.land_size = land_size;
		this.build_area_size = build_area_size;
		this.quantity_bedrooms = quantity_bedrooms;
		this.quantity_bathrooms = quantity_bathrooms;
		this.quantity_garage = quantity_garage;
		this.buildingIsNew = buildingIsNew;
		this.advertisement2 = advertisement2;
	}

	public Building() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Building [building_id=" + building_id + ", land_size=" + land_size + ", build_area_size="
				+ build_area_size + ", quantity_bedrooms=" + quantity_bedrooms + ", quantity_bathrooms="
				+ quantity_bathrooms + ", quantity_garage=" + quantity_garage + ", advertisement2=" + advertisement2
				+ "]";
	}

}
