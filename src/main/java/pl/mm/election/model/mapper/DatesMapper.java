package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Dates;
import pl.mm.election.model.to.DatesTo;

public class DatesMapper {
	
	public static Dates toPersistent(DatesTo to) {
		if(to == null) {
			return null;
		}
		
		Dates po = new Dates();
		po.setBirthDate(to.getBirthDate());
		po.setDeathDate(to.getDeathDate());
		return po;
	}
	
	public static DatesTo toTransfer(Dates po) {
		if(po == null) {
			return null;
		}
		
		DatesTo to = new DatesTo();
		to.setBirthDate(po.getBirthDate());
		to.setDeathDate(po.getDeathDate());
		return to;
	}
	
}
