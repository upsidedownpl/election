package pl.mm.election.dao;

import pl.mm.election.model.po.Address;
import pl.mm.election.model.po.City;
import pl.mm.election.model.po.Country;
import pl.mm.election.model.po.Street;

public interface AddressDao {

	Country getCountryByName(String name);

	City getCityByZipAndCountry(String zip, Country country);
	
	Address getAddressByStreetAndNumber(Street street, String number);

	Street getStreetByNameAndCity(String name, City city);

	long countCountry();

	long countCity();

	long countStreet();
	
	long countAddress();

	void save(Country country);

	void save(City city);

	void save(Street street);
	
	void save(Address address);

}
