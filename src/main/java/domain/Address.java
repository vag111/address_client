package main.java.domain;

public class Address {
	private Long id;
	// клиент
	private String person;
	// наименование улицы
	private String street;
	// номер строения, дома
	private Integer building;
	// номер офиса
	private Integer office;

	// внешний ключ для связи с моделью City
	private Long cityId;
	// навигационное свойства - ссылка на город
	private City city;


	public Address() {
		
	}
	public Address(String person, String street, Integer building, Integer office, Long cityId) {
		this.person = person;
		this.street = street;
		this.building = building;
		this.office = office;
		this.cityId = cityId;
	}
	public Address(String person, String street, Integer building, Integer office, Long cityId, City city) {
		this.person = person;
		this.street = street;
		this.building = building;
		this.office = office;
		this.cityId = cityId;
		this.city = city;
	}
	public Address(String person, String street, Integer building, Integer office, City city) {
		this.person = person;
		this.street = street;
		this.building = building;
		this.office = office;
		this.city = city;
	}
	public Address(Long id, String person, String street, Integer building, Integer office, Long cityId) {
		this.id = id;
		this.person = person;
		this.street = street;
		this.building = building;
		this.office = office;
		this.cityId = cityId;
	}
	public Address(Long id, String person, String street, Integer building, Integer office, Long cityId, City city) {
		this.id = id;
		this.person = person;
		this.street = street;
		this.building = building;
		this.office = office;
		this.cityId = cityId;
		this.city = city;
	}
	public Address(Long id, String person, String street, Integer building, Integer office, City city) {
		this.id = id;
		this.person = person;
		this.street = street;
		this.building = building;
		this.office = office;
		this.city = city;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getBuilding() {
		return building;
	}
	public void setBuilding(Integer building) {
		this.building = building;
	}

	public Integer getOffice() {
		return office;
	}
	public void setOffice(Integer office) {
		this.office = office;
	}

	public City city () {
		return city;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city.getCity();
	}
	public void setCity(City city) {
		this.city = city;
	}



	@Override
	public String toString() {
		return "Address {id=" + id + ", person=" + person + ", street=" + street + ", building=" + building +
				", office=" + office + ", cityId=" + cityId + ", city=" + city + "}";
	}
}
