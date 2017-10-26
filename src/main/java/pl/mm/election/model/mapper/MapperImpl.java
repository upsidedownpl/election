package pl.mm.election.model.mapper;


import pl.mm.election.model.po.Persistent;
import pl.mm.election.model.to.Transfer;

public abstract class MapperImpl<T extends Transfer, P extends Persistent> implements pl.mm.election.model.mapper.Mapper<T, P> {

	protected abstract T createTransfer();
	
	protected abstract P createPersistent();
	
	protected abstract void mapTransferToPersistent(T to, P po);
	
	protected abstract void mapPersistentToTransfer(P po, T to);
	
	public P toPersistent(T to) {
		if(to == null) {
			return null;
		}
		
		P po = createPersistent();
		mapTransferToPersistent(to, po);
		return po;
	}
	
	public T toTransfer(P po) {
		if(po == null) {
			return null;
		}
		
		T to = createTransfer();
		mapPersistentToTransfer(po, to);
		return to;
	}
	
}
