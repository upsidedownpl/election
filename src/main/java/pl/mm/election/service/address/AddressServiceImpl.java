package pl.mm.election.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mm.election.dao.AddressDao;
import pl.mm.election.model.Address;
import pl.mm.election.model.City;
import pl.mm.election.model.Country;
import pl.mm.election.model.Street;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@Transactional
	@Override
	public Country createCountry(String name) {
		Country country = new Country();
		country.setName(name);
		addressDao.save(country);
		return country;
	}
	
	@Transactional
	@Override
	public City createCity(String name, String zip, Country country) {
		City city = new City();
		city.setName(name);
		city.setZip(zip);
		city.setCountry(country);
		addressDao.save(city);
		return city;
	}
	
	@Transactional
	@Override
	public Street createStreet(String name, City city) {
		Street street = new Street();
		street.setName(name);
		street.setCity(city);
		addressDao.save(street);
		return street;
	}
	
	@Transactional
	@Override
	public Address createAddress(String number, Street street) {
		Address address = new Address();
		address.setNumber(number);
		address.setStreet(street);
		addressDao.save(address);
		return address;
	}
}
