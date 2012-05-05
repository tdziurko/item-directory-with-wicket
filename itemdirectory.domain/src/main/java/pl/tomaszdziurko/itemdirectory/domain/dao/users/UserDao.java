package pl.tomaszdziurko.itemdirectory.domain.dao.users;

import pl.tomaszdziurko.itemdirectory.domain.dao.DAO;
import pl.tomaszdziurko.itemdirectory.domain.entities.users.User;

public interface UserDao extends DAO<User> {

	User findByLogin(String login);
	
}
