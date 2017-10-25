package pl.mm.election.dao.citizen;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import pl.mm.election.model.po.Citizen;
import pl.mm.election.model.po.Citizen_;
import pl.mm.election.model.po.Dates;
import pl.mm.election.model.po.Dates_;
import pl.mm.election.model.po.Names;
import pl.mm.election.model.po.Names_;
import pl.mm.election.model.po.Numbers;
import pl.mm.election.model.po.Numbers_;

@Service
public class CitizenDaoImpl implements CitizenDao {
	
	@PersistenceContext(unitName="pu")
	private EntityManager em;
	
	@Override
	public void save(Citizen citizen) {
		em.persist(citizen);
	}
	
	@Override
	public List<Citizen> queryBy(Numbers numbers, Names names, Dates dates) {
		return byQueryResult(numbers, names, dates);
	}
	
	@Override
	public Citizen getBy(Numbers numbers, Names names, Dates dates) throws NonUniqueCitizenQuery {
		List<Citizen> result = byQueryResult(numbers, names, dates);
		if(result.isEmpty()) {
			return null;
		} else if(result.size() > 1) {
			throw new NonUniqueCitizenQuery();
		} else {
			return result.get(0);
		}
	}

	private List<Citizen> byQueryResult(Numbers numbers, Names names, Dates dates) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Citizen> cq = cb.createQuery(Citizen.class);
		Root<Citizen> from = cq.from(Citizen.class);
		
		List<Predicate> where = new ArrayList<>();
		
		if(numbers != null) {
			if(numbers.getPesel() != null) {
				where.add(cb.equal(from.get(Citizen_.numbers).get(Numbers_.pesel), numbers.getPesel()));
			}
		}
		
		if(names != null) {
			if(names.getFirstName() != null) {
				where.add(cb.equal(from.get(Citizen_.names).get(Names_.firstName), names.getFirstName()));
			}
			if(names.getLastName() != null) {
				where.add(cb.equal(from.get(Citizen_.names).get(Names_.lastName), names.getLastName()));
			}
		}
		
		if(dates != null) {
			if(dates.getBirthDate() != null) {
				where.add(cb.equal(from.get(Citizen_.dates).get(Dates_.birthDate), dates.getBirthDate()));
			}
		}
		
		cq.where(cb.and(where.toArray(new Predicate[0])));
		List<Citizen> result = em.createQuery(cq).getResultList();
		return result;
	}
}
