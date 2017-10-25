package pl.mm.election.service.citizen;

import static pl.mm.election.model.mapper.MapperFactory.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mm.election.dao.citizen.CitizenDao;
import pl.mm.election.dao.citizen.NonUniqueCitizenQuery;
import pl.mm.election.model.po.Citizen;
import pl.mm.election.model.to.CitizenTo;
import pl.mm.election.model.to.DatesTo;
import pl.mm.election.model.to.NamesTo;
import pl.mm.election.model.to.NumbersTo;

@Service
public class CitizenServiceImpl implements CitizenService {

	@Autowired
	private CitizenDao citizenDao;
	
	@Override
	public CitizenTo save(CitizenTo citizen) {
		Citizen persistent = map(citizen);
		citizenDao.save(persistent);
		return map(persistent);
	}
	
	@Override
	public CitizenTo getBy(NumbersTo numbers, NamesTo names, DatesTo dates) throws NonUniqueCitizenQuery{
		return map(citizenDao.getBy(
					map(numbers),
					map(names),
					map(dates)
				));
	}
}
