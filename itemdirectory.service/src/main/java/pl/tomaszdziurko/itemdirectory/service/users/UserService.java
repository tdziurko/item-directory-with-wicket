package pl.tomaszdziurko.itemdirectory.service.users;

import pl.tomaszdziurko.itemdirectory.domain.entities.users.User;

public interface UserService {
	
	User findByLogin(String login);

	long size();
	
}
