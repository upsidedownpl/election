package pl.mm.election.service.address;

import static pl.mm.election.model.mapper.MapperFactory.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mm.election.dao.address.AddressDao;
import pl.mm.election.model.po.Address;
import pl.mm.election.model.po.City;
import pl.mm.election.model.po.Country;
import pl.mm.election.model.po.Street;
import pl.mm.election.model.to.AddressTo;
import pl.mm.election.model.to.CityTo;
import pl.mm.election.model.to.CountryTo;
import pl.mm.election.model.to.StreetTo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;
	
	@Override
	public CountryTo getCountryByName(String name) {
		return map(addressDao.getCountryByName(name));
	}
	
	@Override
	public StreetTo getStreetByNameAndCity(String name, CityTo city) {
		return map(
				addressDao.getStreetByNameAndCity(name, 
						map(city)));
	}
	
	@Override
	public CityTo getCityByZipAndCountry(String zip, CountryTo country) {
		return map(
				addressDao.getCityByZipAndCountry(zip, 
						map(country)));
	}
	
	@Override
	public AddressTo getAddressByStreetAndNumber(StreetTo street, String number) {
		return map(
				addressDao.getAddressByStreetAndNumber(
						map(street), 
						number));
	}
	
	@Transactional
	@Override
	public CountryTo createCountry(String name) {
		Country country = new Country();
		country.setName(name);
		addressDao.save(country);
		return map(country);
	}
	
	@Transactional
	@Override
	public CityTo createCity(String name, String zip, CountryTo country) {
		CityTo city = new CityTo();
		city.setName(name);
		city.setZip(zip);
		city.setCountry(country);
		
		City persistent = map(city);
		
		addressDao.save(persistent);
		return map(persistent);
	}
	
	@Transactional
	@Override
	public StreetTo createStreet(String name, CityTo city) {
		StreetTo street = new StreetTo();
		street.setName(name);
		street.setCity(city);
		
		Street persistent = map(street);
		addressDao.save(persistent);
		return map(persistent);
	}
	
	@Transactional
	@Override
	public AddressTo createAddress(String number, StreetTo street) {
		AddressTo address = new AddressTo();
		address.setNumber(number);
		address.setStreet(street);
		
		Address persistent = map(address);
		addressDao.save(persistent);
		return map(persistent);
	}
	
	@Override
	public CountryTo save(CountryTo country) {
		Country persistent = map(country);
		addressDao.save(persistent);
		return map(persistent);
	}
	
	@Override
	public AddressTo save(AddressTo address) {
		Address persistent = map(address);
		addressDao.save(persistent);
		return map(persistent);
	}
	
	@Override
	public StreetTo save(StreetTo street) {
		Street persistent = map(street);
		addressDao.save(persistent);
		return map(persistent);
	}
	
	@Override
	public CityTo save(CityTo city) {
		City persistent = map(city);
		addressDao.save(persistent);
		return map(persistent);
	}
}
