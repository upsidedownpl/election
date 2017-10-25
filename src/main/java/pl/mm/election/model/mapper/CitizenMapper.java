package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Citizen;
import pl.mm.election.model.to.CitizenTo;

import static pl.mm.election.model.mapper.MapperFactory.map;

class CitizenMapper implements Mapper<CitizenTo, Citizen>{
	
	public Citizen toPersistent(CitizenTo to) {
		if(to == null) {
			return null;
		}
		
		Citizen po = new Citizen();
		po.setDates(map(to.getDates()));
		po.setNames(map(to.getNames()));
		po.setNumbers(map(to.getNumbers()));
		po.setId(to.getId());
		po.setUser(map(to.getUser()));
		return po;
	}
	
	public CitizenTo toTransfer(Citizen po) {
		if(po == null) {
			return null;
		}
		
		CitizenTo to = new CitizenTo();
		to.setDates(map(po.getDates()));
		to.setNames(map(po.getNames()));
		to.setNumbers(map(po.getNumbers()));
		to.setId(po.getId());
		to.setUser(map(po.getUser()));
		return to;
	}
	
	@Override
	public Class<Citizen> persistentClass() {
		return Citizen.class;
	}
	
	@Override
	public Class<CitizenTo> transferClass() {
		return CitizenTo.class;
	}
}
