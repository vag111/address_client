package main.java.domain;

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
	public String getCountry() {
		return country.getCountryShort();
	}
	public void setCountry(Country country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "Region {id=" + id + ", region=" + region + ", countryId=" + countryId + ", country=" + country + "}";
	}
}
