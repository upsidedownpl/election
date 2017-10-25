package pl.mm.election.imports.user;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import pl.mm.election.service.user.UserService;

public class UserImportWriter implements ItemWriter<UserImportTo>, InitializingBean {

	protected static final Log logger = LogFactory.getLog(UserImportWriter.class);

	private UserService userService;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(userService, "An userService is required");
	}

	@Override
	public void write(List<? extends UserImportTo> items) {
		for (UserImportTo i : items) {
			userService.create(i.getUser(), i.getPassword(), i.getSalt());
		}

	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
