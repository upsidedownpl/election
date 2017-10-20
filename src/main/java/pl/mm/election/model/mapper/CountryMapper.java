package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Country;
import pl.mm.election.model.to.CountryTo;

public class CountryMapper {
	
	public static Country toPersistent(CountryTo to) {
		if(to == null) {
			return null;
		}
		
		Country po = new Country();
		po.setName(to.getName());
		po.setId(to.getId());
		return po;
	}
	
	public static CountryTo toTransfer(Country po) {
		if(po == null) {
			return null;
		}
		
		CountryTo to = new CountryTo();
		to.setName(po.getName());
		to.setId(po.getId());
		return to;
	}
	
}
