package pl.mm.election.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mm.election.dao.AddressDao;
import pl.mm.election.model.mapper.AddressMapper;
import pl.mm.election.model.mapper.CityMapper;
import pl.mm.election.model.mapper.CountryMapper;
import pl.mm.election.model.mapper.StreetMapper;
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
	@Transactional
	public CountryTo getCountryByName(String name) {
		return CountryMapper.toTransfer(addressDao.getCountryByName(name));
	}
	
	@Transactional
	@Override
	public CountryTo createCountry(String name) {
		Country country = new Country();
		country.setName(name);
		addressDao.save(country);
		return CountryMapper.toTransfer(country);
	}
	
	@Transactional
	@Override
	public CityTo createCity(String name, String zip, CountryTo country) {
		CityTo city = new CityTo();
		city.setName(name);
		city.setZip(zip);
		city.setCountry(country);
		
		City persistent = CityMapper.toPersistent(city);
		
		addressDao.save(persistent);
		return CityMapper.toTransfer(persistent);
	}
	
	@Transactional
	@Override
	public StreetTo createStreet(String name, CityTo city) {
		StreetTo street = new StreetTo();
		street.setName(name);
		street.setCity(city);
		
		Street persistent = StreetMapper.toPersistent(street);
		addressDao.save(persistent);
		return StreetMapper.toTransfer(persistent);
	}
	
	@Transactional
	@Override
	public AddressTo createAddress(String number, StreetTo street) {
		AddressTo address = new AddressTo();
		address.setNumber(number);
		address.setStreet(street);
		
		Address persistent = AddressMapper.toPersistent(address);
		addressDao.save(persistent);
		return AddressMapper.toTransfer(persistent);
	}
}
