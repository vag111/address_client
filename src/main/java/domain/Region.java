package main.java.domain;

import java.util.Date;

/**
* Класс данных о посещениях компьютерного клуба
*/
public class Region {
	private Long id;
	// регион
	private String region;
	
	// внешний ключ для связи с моделью Country
	private Long countryId;
	// Навигационное свойства - ссылка на страну
	private Country country;
	
	public Region() {
		
	}
	public Region(String region, Long countryId) {
		this.region = region;
		this.countryId = countryId;
	}
	public Region(String region, Long countryId, Country country) {
		this.region = region;
		this.countryId = countryId;
		this.country = country;
	}
	public Region(Long id, String region, Long countryId) {
		this.id = id;
		this.region = region;
		this.countryId = countryId;
	}
	public Region(Long id, String region, Long countryId, Country country) {
		this.id = id;
		this.region = region;
		this.countryId = countryId;
		this.country = country;
	}

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public Country country () {
		return country;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	//public String getVisitor() {
	//	return address.getSurname() + " " + address.getName() + " " + address.getPatronymic();
	//}
	//public void setVisitor(Address address) {
	//	this.address = address;
	//}
	//public String getComputer() {
	//	return city.getComputerName();
	//}
	//public void setComputer(City city) {
	//	this.city = city;
	//}

	@Override
	public String toString() {
		return "Region {id=" + id + ", region=" + region + ", countryId=" + countryId + ", country=" + country + "}";
	}
}
