package pl.mm.election.imports.address;

import pl.mm.election.model.to.AddressTo;
import pl.mm.election.model.to.CityTo;
import pl.mm.election.model.to.CountryTo;
import pl.mm.election.model.to.StreetTo;

public class AddressImportTo {

	private CountryTo country;
	private StreetTo street;
	private CityTo city;
	private AddressTo address;

	public CountryTo getCountry() {
		return country;
	}

	public void setCountry(CountryTo country) {
		this.country = country;
	}

	public StreetTo getStreet() {
		return street;
	}

	public void setStreet(StreetTo street) {
		this.street = street;
	}

	public CityTo getCity() {
		return city;
	}

	public void setCity(CityTo city) {
		this.city = city;
	}

	public AddressTo getAddress() {
		return address;
	}

	public void setAddress(AddressTo address) {
		this.address = address;
	}
	
}
