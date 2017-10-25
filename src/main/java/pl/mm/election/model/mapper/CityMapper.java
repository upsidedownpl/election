package pl.mm.election.model.mapper;

import pl.mm.election.model.po.City;
import pl.mm.election.model.to.CityTo;
import static pl.mm.election.model.mapper.MapperFactory.map;

class CityMapper implements Mapper<CityTo, City>{
	
	public City toPersistent(CityTo to) {
		if(to == null) {
			return null;
		}
		
		City po = new City();
		po.setName(to.getName());
		po.setId(to.getId());
		po.setZip(to.getZip());
		po.setCountry(map(to.getCountry()));
		return po;
	}
	
	public CityTo toTransfer(City po) {
		if(po == null) {
			return null;
		}
		
		CityTo to = new CityTo();
		to.setName(po.getName());
		to.setId(po.getId());
		to.setZip(po.getZip());
		to.setCountry(map(po.getCountry()));
		return to;
	}
	
	@Override
	public Class<City> persistentClass() {
		return City.class;
	}
	
	@Override
	public Class<CityTo> transferClass() {
		return CityTo.class;
	}
}
