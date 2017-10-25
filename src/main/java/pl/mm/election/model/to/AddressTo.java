package pl.mm.election.model.to;

public class AddressTo implements Transfer {

	private Integer id;
	
	private StreetTo street;
	
	private String number;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StreetTo getStreet() {
		return street;
	}

	public void setStreet(StreetTo street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}
