package pl.mm.election.model.mapper;

import pl.mm.election.model.po.City;
import pl.mm.election.model.to.CityTo;
import static pl.mm.election.model.mapper.MapperFactory.map;

class CityMapper extends MapperImpl<CityTo, City> implements Mapper<CityTo, City>{
	
	@Override
	protected void mapTransferToPersistent(CityTo to, City po) {
		po.setName(to.getName());
		po.setId(to.getId());
		po.setZip(to.getZip());
		po.setCountry(map(to.getCountry()));
	}
	
	@Override
	protected void mapPersistentToTransfer(City po, CityTo to) {
		to.setName(po.getName());
		to.setId(po.getId());
		to.setZip(po.getZip());
		to.setCountry(map(po.getCountry()));
	}
	
	@Override
	public Class<City> persistentClass() {
		return City.class;
	}
	
	@Override
	public Class<CityTo> transferClass() {
		return CityTo.class;
	}
	
	@Override
	protected City createPersistent() {
		return new City();
	}
	
	@Override
	protected CityTo createTransfer() {
		return new CityTo();
	}
}
