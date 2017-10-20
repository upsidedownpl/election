package pl.mm.election.model.to;

public class StreetTo {

	private Integer id;
	
	private String name;
	
	private CityTo city;

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

	public CityTo getCity() {
		return city;
	}

	public void setCity(CityTo city) {
		this.city = city;
	}
	
}
