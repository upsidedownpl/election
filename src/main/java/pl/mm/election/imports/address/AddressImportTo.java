package pl.mm.election.imports.address;

import pl.mm.election.model.po.Address;
import pl.mm.election.model.po.City;
import pl.mm.election.model.po.Country;
import pl.mm.election.model.po.Street;

public class AddressImportTo {

	private Country country;
	private Street street;
	private City city;
	private Address address;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
