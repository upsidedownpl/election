package pl.mm.election.model.mapper;

import java.util.HashMap;
import java.util.Map;

import pl.mm.election.model.po.Persistent;
import pl.mm.election.model.to.Transfer;

public class MapperFactory {
	
	private static Map<Class<? extends Transfer>, Mapper<? extends Transfer, ? extends Persistent>> transferToPersistent
		= new HashMap<>();
	
	private static Map<Class<? extends Persistent>, Mapper<? extends Transfer, ? extends Persistent>> persistentToTransfer
		= new HashMap<>();
	
	private MapperFactory() {
		//
	}
	
	static {
		register(new AddressMapper());
		register(new CitizenMapper());
		register(new CityMapper());
		register(new CountryMapper());
		register(new DatesMapper());
		register(new NamesMapper());
		register(new NumbersMapper());
		register(new StreetMapper());
		register(new UserMapper());
		register(new PersonCandidateMapper());
		register(new VoteMapper());
	}
	
	public static <T extends Transfer, P extends Persistent> void register(Mapper<T, P> mapper) {
		transferToPersistent.put(mapper.transferClass(), mapper);
		persistentToTransfer.put(mapper.persistentClass(), mapper);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Transfer, P extends Persistent> Mapper<T, P> mapper(T transfer) {
		return (Mapper<T, P>) transferToPersistent.get(transfer.getClass());
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Transfer, P extends Persistent> Mapper<T, P> mapper(P persistent) {
		return (Mapper<T, P>) persistentToTransfer.get(persistent.getClass());
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Transfer, P extends Persistent> T map(P persistent) {
		if(persistent == null) {
			return null;
		}
		
		Mapper<Transfer, P> mapper = mapper(persistent);
		if(mapper == null) {
			throw new IllegalArgumentException("Unknown mapper for class " + persistent);
		}
		
		return (T) mapper.toTransfer(persistent);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Transfer, P extends Persistent> P map(T transfer) {
		if(transfer == null) {
			return null;
		}
		
		Mapper<T, Persistent> mapper = mapper(transfer);
		if(mapper == null) {
			throw new IllegalArgumentException("Unknown mapper for class " + transfer);
		}
		
		return (P) mapper(transfer).toPersistent(transfer);
	}
}
