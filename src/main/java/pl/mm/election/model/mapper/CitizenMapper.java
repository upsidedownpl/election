package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Citizen;
import pl.mm.election.model.to.CitizenTo;

import static pl.mm.election.model.mapper.MapperFactory.map;

class CitizenMapper extends MapperImpl<CitizenTo, Citizen> implements Mapper<CitizenTo, Citizen>{
	
	@Override
	protected void mapTransferToPersistent(CitizenTo to, Citizen po) {
		po.setDates(map(to.getDates()));
		po.setNames(map(to.getNames()));
		po.setNumbers(map(to.getNumbers()));
		po.setId(to.getId());
		po.setUser(map(to.getUser()));
	}
	
	@Override
	protected void mapPersistentToTransfer(Citizen po, CitizenTo to) {
		to.setDates(map(po.getDates()));
		to.setNames(map(po.getNames()));
		to.setNumbers(map(po.getNumbers()));
		to.setId(po.getId());
		to.setUser(map(po.getUser()));
	}
	
	@Override
	public Class<Citizen> persistentClass() {
		return Citizen.class;
	}
	
	@Override
	public Class<CitizenTo> transferClass() {
		return CitizenTo.class;
	}
	
	@Override
	protected Citizen createPersistent() {
		return new Citizen();
	}
	
	@Override
	protected CitizenTo createTransfer() {
		return new CitizenTo();
	}
}
