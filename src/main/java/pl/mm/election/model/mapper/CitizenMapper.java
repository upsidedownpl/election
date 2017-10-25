package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Citizen;
import pl.mm.election.model.to.CitizenTo;

public class CitizenMapper {
	
	public static Citizen toPersistent(CitizenTo to) {
		if(to == null) {
			return null;
		}
		
		Citizen po = new Citizen();
		po.setDates(DatesMapper.toPersistent(to.getDates()));
		po.setNames(NamesMapper.toPersistent(to.getNames()));
		po.setNumbers(NumbersMapper.toPersistent(to.getNumbers()));
		po.setId(to.getId());
		po.setUser(UserMapper.toPersistent(to.getUser()));
		return po;
	}
	
	public static CitizenTo toTransfer(Citizen po) {
		if(po == null) {
			return null;
		}
		
		CitizenTo to = new CitizenTo();
		to.setDates(DatesMapper.toTransfer(po.getDates()));
		to.setNames(NamesMapper.toTransfer(po.getNames()));
		to.setNumbers(NumbersMapper.toTransfer(po.getNumbers()));
		to.setId(po.getId());
		to.setUser(UserMapper.toTransfer(po.getUser()));
		return to;
	}
	
}
