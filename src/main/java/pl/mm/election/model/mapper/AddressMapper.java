package pl.mm.election.model.mapper;

import static pl.mm.election.model.mapper.MapperFactory.map;

import pl.mm.election.model.po.Address; 
import pl.mm.election.model.to.AddressTo;

class AddressMapper implements Mapper<AddressTo, Address>{
	
	public Address toPersistent(AddressTo to) {
		if(to == null) {
			return null;
		}
		
		Address po = new Address();
		po.setId(to.getId());
		po.setNumber(to.getNumber());
		po.setStreet(map(to.getStreet()));
		return po;
	}
	
	public AddressTo toTransfer(Address po) {
		if(po == null) {
			return null;
		}
		
		AddressTo to = new AddressTo();
		to.setId(po.getId());
		to.setNumber(po.getNumber());
		to.setStreet(map(po.getStreet()));
		return to;
	}
	
	@Override
	public Class<Address> persistentClass() {
		return Address.class;
	}
	
	@Override
	public Class<AddressTo> transferClass() {
		return AddressTo.class;
	}
}
