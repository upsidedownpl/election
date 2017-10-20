package pl.mm.election.imports.user;

import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Streams;
import com.google.common.primitives.Bytes;

import pl.mm.election.model.po.User;

public class UserImportProcessor implements ItemProcessor<UserImport, User>{

	private static final Logger log = LoggerFactory.getLogger(UserImportProcessor.class);
	
	private EntityManagerFactory entityManagerFactory;
	
	public UserImportProcessor(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public User process(UserImport u) throws Exception {
		EntityManager entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
		if (entityManager == null) {
			throw new DataAccessResourceFailureException("Unable to obtain a transactional EntityManager");
		}
		
		log.info("Processing user {}", u.getLogin());
		User user = new User();
		if(!StringUtils.isEmpty(u.getLogin())) {
			user.setLogin(u.getLogin());
		} else {
			log.warn("Row with empty login");
			throw new UserImportException();
		}
		
		if(entityManager.find(User.class, user.getLogin()) != null) {
			log.info("User {} already exists", user.getLogin());
			throw new AlreadyExistsUserImportException();
		}
		
		if(u.getPassword() != null) {
			user.setPassword(csvToBytes(u.getPassword()));
		}
		
		if(u.getSalt() != null) {
			user.setSalt(csvToBytes(u.getSalt()));
		}
		
		return user;
	}

	private byte[] csvToBytes(String csv) {
		Iterable<String> p = Splitter.on(",")
		        .trimResults(CharMatcher.whitespace().or(CharMatcher.anyOf("[]"))).split(csv);
		byte[] pBytes = Bytes.toArray(Streams.stream(p)
				.map(s -> Byte.parseByte(s))
				.collect(Collectors.toList()));
		return pBytes;
	}

}
