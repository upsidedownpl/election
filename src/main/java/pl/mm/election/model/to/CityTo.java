package pl.mm.election.model.to;

public class CityTo {

	private Integer id;

	private String name;
	
	private CountryTo country;

	private String zip;
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CountryTo getCountry() {
		return country;
	}

	public void setCountry(CountryTo country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
