package pl.mm.election.imports.user;

import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Streams;
import com.google.common.primitives.Bytes;

import pl.mm.election.model.to.UserTo;
import pl.mm.election.service.user.UserService;

public class UserImportProcessor implements ItemProcessor<UserImport, UserImportTo>{

	private static final Logger log = LoggerFactory.getLogger(UserImportProcessor.class);
	
	private final UserService userService;
	
	public UserImportProcessor(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserImportTo process(UserImport u) throws Exception {
		log.info("Processing user {}", u.getLogin());
		UserImportTo user = new UserImportTo();
		if(!StringUtils.isEmpty(u.getLogin())) {
			UserTo newUser = new UserTo();
			newUser.setLogin(u.getLogin());
			user.setUser(newUser);
		} else {
			log.warn("Row with empty login");
			throw new UserImportException();
		}
		
		if(userService.getByLogin(u.getLogin()) != null) {
			log.info("User {} already exists", u.getLogin());
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
