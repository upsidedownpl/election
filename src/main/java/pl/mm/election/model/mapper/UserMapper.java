package pl.mm.election.model.mapper;

import pl.mm.election.model.po.User;
import pl.mm.election.model.to.UserTo;

class UserMapper implements Mapper<UserTo, User>{
	
	UserMapper() {
		//
	}
	
	public User toPersistent(UserTo to) {
		if(to == null) {
			return null;
		}
		
		User po = new User();
		po.setLogin(to.getLogin());
		return po;
	}
	
	public UserTo toTransfer(User po) {
		if(po == null) {
			return null;
		}
		
		UserTo to = new UserTo();
		to.setLogin(po.getLogin());
		return to;
	}
	
	@Override
	public Class<User> persistentClass() {
		return User.class;
	}
	
	@Override
	public Class<UserTo> transferClass() {
		return UserTo.class;
	}
}
