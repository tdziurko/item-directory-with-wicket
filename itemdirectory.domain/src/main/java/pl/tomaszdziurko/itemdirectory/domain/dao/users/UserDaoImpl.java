package pl.tomaszdziurko.itemdirectory.domain.dao.users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pl.tomaszdziurko.itemdirectory.domain.dao.AbstractDAO;
import pl.tomaszdziurko.itemdirectory.domain.entities.users.User;

@Repository(value = "userDao")
public class UserDaoImpl extends AbstractDAO<User> implements UserDao {
	
	@PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

	public User findByLogin(String login) {
		Query query = getEntityManager().createQuery("from " + getClazz() + " e where e.login = :aLogin");
		query.setParameter("aLogin", login);
		
		return (User) query.getSingleResult();
	}

	public Class<User> getClazz() {
		return User.class;
	}


}
