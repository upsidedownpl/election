package pl.mm.election.model.mapper;

import pl.mm.election.model.po.User;
import pl.mm.election.model.to.UserTo;

public class UserMapper {
	
	public static User toPersistent(UserTo to) {
		if(to == null) {
			return null;
		}
		
		User po = new User();
		po.setLogin(to.getLogin());
		return po;
	}
	
	public static UserTo toTransfer(User po) {
		if(po == null) {
			return null;
		}
		
		UserTo to = new UserTo();
		to.setLogin(po.getLogin());
		return to;
	}
	
}
