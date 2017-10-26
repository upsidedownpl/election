package pl.mm.election.model.mapper;

import pl.mm.election.model.po.User;
import pl.mm.election.model.to.UserTo;

class UserMapper extends MapperImpl<UserTo, User> implements Mapper<UserTo, User>{
	
	UserMapper() {
		//
	}
	
	@Override
	protected void mapTransferToPersistent(UserTo to, User po) {
		po.setLogin(to.getLogin());
	}
	
	@Override
	protected void mapPersistentToTransfer(User po, UserTo to) {
		to.setLogin(po.getLogin());
	}
	
	@Override
	public Class<User> persistentClass() {
		return User.class;
	}
	
	@Override
	public Class<UserTo> transferClass() {
		return UserTo.class;
	}
	
	@Override
	protected User createPersistent() {
		return new User();
	}
	
	@Override
	protected UserTo createTransfer() {
		return new UserTo();
	}
}
