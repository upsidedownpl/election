package pl.mm.election.model.mapper;

import pl.mm.election.model.po.Dates;
import pl.mm.election.model.to.DatesTo;

class DatesMapper extends MapperImpl<DatesTo, Dates> implements Mapper<DatesTo, Dates>{
	
	@Override
	protected void mapTransferToPersistent(DatesTo to, Dates po) {
		po.setBirthDate(to.getBirthDate());
		po.setDeathDate(to.getDeathDate());
	}
	
	@Override
	protected void mapPersistentToTransfer(Dates po, DatesTo to) {
		to.setBirthDate(po.getBirthDate());
		to.setDeathDate(po.getDeathDate());
	}
	
	@Override
	public Class<Dates> persistentClass() {
		return Dates.class;
	}
	
	@Override
	public Class<DatesTo> transferClass() {
		return DatesTo.class;
	}
	
	@Override
	protected Dates createPersistent() {
		return new Dates();
	}
	
	@Override
	protected DatesTo createTransfer() {
		return new DatesTo();
	}
}
