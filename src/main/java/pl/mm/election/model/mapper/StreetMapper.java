package pl.mm.election.model.mapper;

import pl.mm.election.model.po.City;
import pl.mm.election.model.po.Street;
import pl.mm.election.model.to.CityTo;
import pl.mm.election.model.to.StreetTo;

public class StreetMapper {
	
	public static Street toPersistent(StreetTo to) {
		if(to == null) {
			return null;
		}
		
		Street po = new Street();
		po.setName(to.getName());
		po.setId(to.getId());
		po.setCity(CityMapper.toPersistent(to.getCity()));
		return po;
	}
	
	public static StreetTo toTransfer(Street po) {
		if(po == null) {
			return null;
		}
		
		StreetTo to = new StreetTo();
		to.setName(po.getName());
		to.setId(po.getId());
		to.setCity(CityMapper.toTransfer(po.getCity()));
		return to;
	}
	
}
