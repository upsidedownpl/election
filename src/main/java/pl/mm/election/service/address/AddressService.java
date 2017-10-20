package pl.mm.election.service.address;

import pl.mm.election.model.po.Address;
import pl.mm.election.model.po.City;
import pl.mm.election.model.po.Country;
import pl.mm.election.model.po.Street;

public interface AddressService {

	Country createCountry(String name);
	
	Country getCountryByName(String name);

	City createCity(String name, String zip, Country country);

	Street createStreet(String name, City city);

	Address createAddress(String number, Street street);

}
