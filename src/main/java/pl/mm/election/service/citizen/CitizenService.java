package pl.mm.election.service.citizen;

import java.util.List;

import pl.mm.election.dao.citizen.NonUniqueCitizenQuery;
import pl.mm.election.model.to.CitizenTo;
import pl.mm.election.model.to.DatesTo;
import pl.mm.election.model.to.DatesTo.DatesToBuilder;
import pl.mm.election.model.to.NamesTo;
import pl.mm.election.model.to.NumbersTo;

public interface CitizenService {

	CitizenTo getBy(NumbersTo numbers, NamesTo names, DatesTo dates) throws NonUniqueCitizenQuery;

	CitizenTo save(CitizenTo citizen);
	
}
