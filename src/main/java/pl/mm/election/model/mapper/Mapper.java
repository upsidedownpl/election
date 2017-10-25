package pl.mm.election.model.mapper;


import pl.mm.election.model.po.Persistent;
import pl.mm.election.model.to.Transfer;

public interface Mapper<T extends Transfer, P extends Persistent> {

	T toTransfer(P persistent);
	
	P toPersistent(T transfer);
	
	Class<T> transferClass();
	
	Class<P> persistentClass();
}
