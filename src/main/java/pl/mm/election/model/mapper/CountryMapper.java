package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Country;
import pl.mm.election.model.to.CountryTo;

class CountryMapper implements Mapper<CountryTo, Country>{
	
	public Country toPersistent(CountryTo to) {
		if(to == null) {
			return null;
		}
		
		Country po = new Country();
		po.setName(to.getName());
		po.setId(to.getId());
		return po;
	}
	
	public CountryTo toTransfer(Country po) {
		if(po == null) {
			return null;
		}
		
		CountryTo to = new CountryTo();
		to.setName(po.getName());
		to.setId(po.getId());
		return to;
	}
	
	@Override
	public Class<Country> persistentClass() {
		return Country.class;
	}
	
	@Override
	public Class<CountryTo> transferClass() {
		return CountryTo.class;
	}
	
}
