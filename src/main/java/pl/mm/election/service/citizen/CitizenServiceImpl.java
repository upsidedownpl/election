package pl.mm.election.service.citizen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mm.election.dao.citizen.CitizenDao;
import pl.mm.election.dao.citizen.NonUniqueCitizenQuery;
import pl.mm.election.model.mapper.CitizenMapper;
import pl.mm.election.model.mapper.DatesMapper;
import pl.mm.election.model.mapper.NamesMapper;
import pl.mm.election.model.mapper.NumbersMapper;
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
		Citizen persistent = CitizenMapper.toPersistent(citizen);
		citizenDao.save(persistent);
		return CitizenMapper.toTransfer(persistent);
	}
	
	@Override
	public CitizenTo getBy(NumbersTo numbers, NamesTo names, DatesTo dates) throws NonUniqueCitizenQuery{
		return CitizenMapper.toTransfer(citizenDao.getBy(
					NumbersMapper.toPersistent(numbers),
					NamesMapper.toPersistent(names),
					DatesMapper.toPersistent(dates)
				));
	}
}
