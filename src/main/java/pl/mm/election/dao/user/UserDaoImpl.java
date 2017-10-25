package pl.mm.election.dao.user;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import pl.mm.election.model.po.User;

@Service
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext(unitName="pu")
	private EntityManager em;

	@Override
	public User read(String login) {
		return em.find(User.class, login);
	}
	
	@Override
	public void save(User user) {
		em.persist(user);
	}
	
	@Override
	public List<User> read() {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> from = cq.from(User.class);

		cq.select(from);
		TypedQuery<User> q = em.createQuery(cq);
		return q.getResultList();
	}
	
	@Override
	public long count() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<User> root = cq.from(User.class);
		
		cq.select(cb.count(root	));
		return em.createQuery(cq).getSingleResult();
	}
}
