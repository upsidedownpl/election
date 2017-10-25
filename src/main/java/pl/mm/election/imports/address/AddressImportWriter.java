package pl.mm.election.imports.address;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import pl.mm.election.service.address.AddressService;

public class AddressImportWriter implements ItemWriter<AddressImportTo>, InitializingBean {

	protected static final Log logger = LogFactory.getLog(AddressImportWriter.class);

	private AddressService addressService;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(addressService, "An addressService is required");
	}

	@Override
	public void write(List<? extends AddressImportTo> items) { 
		for(AddressImportTo i : items) {
			if(i.getCountry().getId() == null) {
				i.setCountry(
						addressService.save(i.getCountry())
					);
			}
			if(i.getCity().getId() == null) {
				i.getCity().setCountry(i.getCountry());
				i.setCity(
						addressService.save(i.getCity())
					);
			}
			if(i.getStreet().getId() == null) {
				i.getStreet().setCity(i.getCity());
				i.setStreet(
						addressService.save(i.getStreet())
					);
			}
			if(i.getAddress().getId() == null) {
				i.getAddress().setStreet(i.getStreet());
				i.setAddress(
						addressService.save(i.getAddress())
					);
			}
		}
		
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

}
