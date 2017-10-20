package pl.mm.election.imports.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;

import pl.mm.election.model.User;

public class UserImportSkipListener implements SkipListener<UserImport, User> {
	
	private static final Logger log = LoggerFactory.getLogger(UserImportProcessor.class);

	@Override
	public void onSkipInRead(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInWrite(User item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInProcess(UserImport item, Throwable t) {
		log.info("Line {}, login {}", item.getLineNumber(), item.getLogin());
	}

}
