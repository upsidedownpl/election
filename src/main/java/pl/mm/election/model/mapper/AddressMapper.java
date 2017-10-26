package pl.mm.election.model.mapper;

import static pl.mm.election.model.mapper.MapperFactory.map;

import pl.mm.election.model.po.Address; 
import pl.mm.election.model.to.AddressTo;

class AddressMapper extends MapperImpl<AddressTo,Address> implements Mapper<AddressTo, Address>{
	
	@Override
	protected void mapTransferToPersistent(AddressTo to, Address po) {
		po.setId(to.getId());
		po.setNumber(to.getNumber());
		po.setStreet(map(to.getStreet()));
	}
	
	@Override
	protected void mapPersistentToTransfer(Address po, AddressTo to) {
		to.setId(po.getId());
		to.setNumber(po.getNumber());
		to.setStreet(map(po.getStreet()));
	}
	
	@Override
	protected Address createPersistent() {
		return new Address();
	}
	
	@Override
	protected AddressTo createTransfer() {
		return new AddressTo();
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
