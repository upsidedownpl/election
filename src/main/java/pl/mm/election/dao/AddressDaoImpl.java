package pl.mm.election.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import pl.mm.election.model.Address;
import pl.mm.election.model.Address_;
import pl.mm.election.model.City;
import pl.mm.election.model.City_;
import pl.mm.election.model.Country;
import pl.mm.election.model.Country_;
import pl.mm.election.model.Street;
import pl.mm.election.model.Street_;

@Service
public class AddressDaoImpl implements AddressDao {
	
	@PersistenceContext(unitName="pu")
	private EntityManager em;

	@Override
	public Address getAddressByStreetAndNumber(Street street, String number) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Address> cq = cb.createQuery(Address.class);
		Root<Address> from = cq.from(Address.class);
		
		cq.where(cb.and(
					cb.equal(from.get(Address_.street), street),
					cb.equal(from.get(Address_.number), number)
				)
			);
		List<Address> result = em.createQuery(cq).getResultList();
		return
				result.isEmpty()
				? null
						: result.get(0);
	}
	
	@Override
	public Country getCountryByName(String name) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Country> cq = cb.createQuery(Country.class);
		Root<Country> from = cq.from(Country.class);
		
		cq.where(cb.equal(from.get(Country_.name), name));
		List<Country> result = em.createQuery(cq).getResultList();
		return
				result.isEmpty()
				? null
				: result.get(0);
	}
	
	@Override
	public City getCityByZipAndCountry(String zip, Country country) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<City> cq = cb.createQuery(City.class);
		Root<City> from = cq.from(City.class);
		
		cq.where(cb.and(
					cb.equal(from.get(City_.country), country),
					cb.equal(from.get(City_.zip), zip)
				)
			);
		List<City> result = em.createQuery(cq).getResultList();
		return
				result.isEmpty()
				? null
						: result.get(0);
	}
	
	@Override
	public Street getStreetByNameAndCity(String name, City city) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Street> cq = cb.createQuery(Street.class);
		Root<Street> from = cq.from(Street.class);
		
		cq.where(cb.and(
				cb.equal(from.get(Street_.city), city),
				cb.equal(from.get(Street_.name), name)
				)
				);
		List<Street> result = em.createQuery(cq).getResultList();
		return
				result.isEmpty()
				? null
						: result.get(0);
	}
	
	@Override
	public long countCountry() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Country> root = cq.from(Country.class);
		
		cq.select(cb.count(root	));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public long countAddress() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Address> root = cq.from(Address.class);
		
		cq.select(cb.count(root	));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public long countCity() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<City> root = cq.from(City.class);
		
		cq.select(cb.count(root	));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public long countStreet() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Street> root = cq.from(Street.class);
		
		cq.select(cb.count(root	));
		return em.createQuery(cq).getSingleResult();
	}
	
	@Override
	public void save(Country country) {
		em.persist(country);
	}
	
	@Override
	public void save(City city) {
		em.persist(city);
	}
	
	@Override
	public void save(Street street) {
		em.persist(street);
	}
	
	@Override
	public void save(Address address) {
		em.persist(address);
	}
}
