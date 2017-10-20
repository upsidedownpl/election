package pl.mm.election.imports.address;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class AddressImportWriter implements ItemWriter<AddressImportTo>, InitializingBean {

	protected static final Log logger = LogFactory.getLog(AddressImportWriter.class);

	private EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(entityManagerFactory, "An EntityManagerFactory is required");
	}

	@Override
	public void write(List<? extends AddressImportTo> items) { 
		EntityManager entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
		if (entityManager == null) {
			throw new DataAccessResourceFailureException("Unable to obtain a transactional EntityManager");
		}
		
		for(AddressImportTo i : items) {
			List<Object> toSave = new ArrayList<>();
			if(i.getCountry().getId() == null) {
				toSave.add(i.getCountry());
			}
			if(i.getCity().getId() == null) {
				toSave.add(i.getCity());
			}
			if(i.getStreet().getId() == null) {
				toSave.add(i.getStreet());
			}
			if(i.getAddress().getId() == null) {
				toSave.add(i.getAddress());
			}
			doWrite(entityManager, toSave);
		}
		
		entityManager.flush();
	}

	protected void doWrite(EntityManager entityManager, List<Object> items) {

		if (logger.isDebugEnabled()) {
			logger.debug("Writing to JPA with " + items.size() + " items.");
		}

		if (!items.isEmpty()) {
			long mergeCount = 0;
			for (Object item : items) {
				if (!entityManager.contains(item)) {
					entityManager.persist(item);
					mergeCount++;
				}
			}
			if (logger.isDebugEnabled()) {
				logger.debug(mergeCount + " entities merged.");
				logger.debug((items.size() - mergeCount) + " entities found in persistence context.");
			}
		}

	}

}
