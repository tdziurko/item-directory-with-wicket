package pl.tomaszdziurko.itemdirectory.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.tomaszdziurko.itemdirectory.domain.dao.users.UserDao;
import pl.tomaszdziurko.itemdirectory.domain.entities.users.User;

@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public User findByLogin(String login) {
		return userDao.findByLogin(login);
	}

	public long size() {
		return userDao.size();
	}

}
