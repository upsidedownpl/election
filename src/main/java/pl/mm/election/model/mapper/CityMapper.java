package pl.mm.election.model.mapper;

import pl.mm.election.model.po.City;
import pl.mm.election.model.to.CityTo;

public class CityMapper {
	
	public static City toPersistent(CityTo to) {
		if(to == null) {
			return null;
		}
		
		City po = new City();
		po.setName(to.getName());
		po.setId(to.getId());
		po.setZip(to.getZip());
		po.setCountry(CountryMapper.toPersistent(to.getCountry()));
		return po;
	}
	
	public static CityTo toTransfer(City po) {
		if(po == null) {
			return null;
		}
		
		CityTo to = new CityTo();
		to.setName(po.getName());
		to.setId(po.getId());
		to.setZip(po.getZip());
		to.setCountry(CountryMapper.toTransfer(po.getCountry()));
		return to;
	}
	
}
