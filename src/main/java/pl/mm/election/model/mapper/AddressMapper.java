package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Address;
import pl.mm.election.model.to.AddressTo;

public class AddressMapper {
	
	public static Address toPersistent(AddressTo to) {
		if(to == null) {
			return null;
		}
		
		Address po = new Address();
		po.setId(to.getId());
		po.setNumber(to.getNumber());
		po.setStreet(StreetMapper.toPersistent(to.getStreet()));
		return po;
	}
	
	public static AddressTo toTransfer(Address po) {
		if(po == null) {
			return null;
		}
		
		AddressTo to = new AddressTo();
		to.setId(po.getId());
		to.setNumber(po.getNumber());
		to.setStreet(StreetMapper.toTransfer(po.getStreet()));
		return to;
	}
	
}
