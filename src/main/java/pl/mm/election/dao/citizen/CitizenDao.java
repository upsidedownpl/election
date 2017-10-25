package pl.mm.election.dao.citizen;

import java.util.List;

import pl.mm.election.model.po.Citizen;
import pl.mm.election.model.po.Dates;
import pl.mm.election.model.po.Names;
import pl.mm.election.model.po.Numbers;

public interface CitizenDao {

	void save(Citizen citizen);

	Citizen getBy(Numbers numbers, Names names, Dates dates) throws NonUniqueCitizenQuery;
	
	List<Citizen> queryBy(Numbers numbers, Names names, Dates dates);
	
} 
