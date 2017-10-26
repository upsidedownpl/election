package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Country;
import pl.mm.election.model.to.CountryTo;

class CountryMapper extends MapperImpl<CountryTo, Country> implements Mapper<CountryTo, Country>{
	
	@Override
	protected void mapTransferToPersistent(CountryTo to, Country po) {
		po.setName(to.getName());
		po.setId(to.getId());
	}

	@Override
	protected void mapPersistentToTransfer(Country po, CountryTo to) {
		to.setName(po.getName());
		to.setId(po.getId());
	}
	
	@Override
	public Class<Country> persistentClass() {
		return Country.class;
	}
	
	@Override
	public Class<CountryTo> transferClass() {
		return CountryTo.class;
	}
	
	@Override
	protected Country createPersistent() {
		return new Country();
	}
	
	@Override
	protected CountryTo createTransfer() {
		return new CountryTo();
	}
}
