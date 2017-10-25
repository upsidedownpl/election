package pl.mm.election.service.address;

import pl.mm.election.model.to.AddressTo;
import pl.mm.election.model.to.CityTo;
import pl.mm.election.model.to.CountryTo;
import pl.mm.election.model.to.StreetTo;

public interface AddressService {

	CountryTo createCountry(String name);
	
	CountryTo getCountryByName(String name);

	CityTo createCity(String name, String zip, CountryTo country);

	StreetTo createStreet(String name, CityTo city);

	AddressTo createAddress(String number, StreetTo street);
 
	AddressTo getAddressByStreetAndNumber(StreetTo street, String number);

	StreetTo getStreetByNameAndCity(String name, CityTo city);

	CityTo getCityByZipAndCountry(String zip, CountryTo country);

	CountryTo save(CountryTo country);
	
	CityTo save(CityTo city);
	
	StreetTo save(StreetTo street);
	
	AddressTo save(AddressTo address);
}
