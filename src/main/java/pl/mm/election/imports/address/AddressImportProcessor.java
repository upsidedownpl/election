package pl.mm.election.imports.address;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import pl.mm.election.dao.AddressDao;
import pl.mm.election.model.po.Address;
import pl.mm.election.model.po.City;
import pl.mm.election.model.po.Country;
import pl.mm.election.model.po.Street;

public class AddressImportProcessor implements ItemProcessor<AddressImport, AddressImportTo>{

	private static final Logger log = LoggerFactory.getLogger(AddressImportProcessor.class);
	
	private AddressDao addressDao;
	
	public AddressImportProcessor(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	@Override
	public AddressImportTo process(AddressImport a) throws Exception {
		log.info("Processing address line {}", a);
		AddressImportTo addressImportTo = new AddressImportTo();
		
		country(a, addressImportTo);
		city(a, addressImportTo);
		street(a, addressImportTo);
		address(a, addressImportTo);
		
		return addressImportTo;
	}

	private void address(AddressImport a, AddressImportTo addressImportTo)
			throws AlreadyExistsAddressImportException, AddressImportException {
		if(!StringUtils.isEmpty(a.getNumber())) {
			if(addressImportTo.getStreet().getId() != null
					&& addressDao.getAddressByStreetAndNumber(addressImportTo.getStreet(), a.getNumber()) != null) {
				throw new AlreadyExistsAddressImportException();
			} else {
				Address address = new Address();
				address.setNumber(a.getNumber());
				address.setStreet(addressImportTo.getStreet());
				addressImportTo.setAddress(address);
			}
		} else {
			log.warn("Row with empty number");
			throw new AddressImportException();
		}
	}

	private void street(AddressImport a, AddressImportTo addressImportTo) throws AddressImportException {
		if(!StringUtils.isEmpty(a.getStreet())) {
			Street street = 
				addressImportTo.getCity().getId() != null
				? addressDao.getStreetByNameAndCity(a.getStreet(), addressImportTo.getCity())
				: null;
				
			if(street != null) {
				log.info("Street {} already exists", a.getStreet());
			} else {
				street = new Street();
				street.setName(a.getStreet());
				street.setCity(addressImportTo.getCity());
			}
			addressImportTo.setStreet(street);
		} else {
			log.warn("Row with empty street");
			throw new AddressImportException();
		}
	}

	private void city(AddressImport a, AddressImportTo addressImportTo) throws AddressImportException {
		if(!StringUtils.isEmpty(a.getZip()) && !StringUtils.isEmpty(a.getCity())) {
			City city = 
				addressImportTo.getCountry().getId() != null
				? addressDao.getCityByZipAndCountry(a.getZip(), addressImportTo.getCountry())
				: null;
				
			if(city != null) {
				log.info("City {}/{} already exists", a.getCity(), a.getZip());
			} else {
				city = new City();
				city.setName(a.getCity());
				city.setZip(a.getZip());
				city.setCountry(addressImportTo.getCountry());
			}
			addressImportTo.setCity(city);
		} else {
			log.warn("Row with empty city/zip");
			throw new AddressImportException();
		}
	}

	private void country(AddressImport a, AddressImportTo addressImportTo) throws AddressImportException {
		if(!StringUtils.isEmpty(a.getCountry())) {
			Country country = addressDao.getCountryByName(a.getCountry());
			if(country != null) {
				log.info("Country {} already exists", a.getCountry());
			} else {
				country = new Country();
				country.setName(a.getCountry());
			}
			addressImportTo.setCountry(country);
		} else {
			log.warn("Row with empty country");
			throw new AddressImportException();
		}
	}

}
