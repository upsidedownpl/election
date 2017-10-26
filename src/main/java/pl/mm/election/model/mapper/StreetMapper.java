package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Street;
import pl.mm.election.model.to.StreetTo;
import static pl.mm.election.model.mapper.MapperFactory.map;

class StreetMapper extends MapperImpl<StreetTo, Street> implements Mapper<StreetTo, Street>{
	
	StreetMapper() {
		//
	}
	
	@Override
	protected void mapTransferToPersistent(StreetTo to, Street po) {
		po.setName(to.getName());
		po.setId(to.getId());
		po.setCity(map(to.getCity()));
	}
	
	@Override
	protected void mapPersistentToTransfer(Street po, StreetTo to) {
		to.setName(po.getName());
		to.setId(po.getId());
		to.setCity(map(po.getCity()));
	}
	
	@Override
	public Class<Street> persistentClass() {
		return Street.class;
	}
	
	@Override
	public Class<StreetTo> transferClass() {
		return StreetTo.class;
	}
	
	@Override
	protected Street createPersistent() {
		return new Street();
	}
	
	@Override
	protected StreetTo createTransfer() {
		return new StreetTo();
	}
}
