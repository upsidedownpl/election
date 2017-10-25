package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Street;
import pl.mm.election.model.to.StreetTo;
import static pl.mm.election.model.mapper.MapperFactory.map;

class StreetMapper implements Mapper<StreetTo, Street>{
	
	StreetMapper() {
		//
	}
	
	public Street toPersistent(StreetTo to) {
		if(to == null) {
			return null;
		}
		
		Street po = new Street();
		po.setName(to.getName());
		po.setId(to.getId());
		po.setCity(map(to.getCity()));
		return po;
	}
	
	public StreetTo toTransfer(Street po) {
		if(po == null) {
			return null;
		}
		
		StreetTo to = new StreetTo();
		to.setName(po.getName());
		to.setId(po.getId());
		to.setCity(map(po.getCity()));
		return to;
	}
	
	@Override
	public Class<Street> persistentClass() {
		return Street.class;
	}
	
	@Override
	public Class<StreetTo> transferClass() {
		return StreetTo.class;
	}
	
}
