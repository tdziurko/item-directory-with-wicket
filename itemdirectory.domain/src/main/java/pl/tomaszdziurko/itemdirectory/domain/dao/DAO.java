package pl.tomaszdziurko.itemdirectory.domain.dao;

import javax.persistence.EntityManager;
import pl.tomaszdziurko.itemdirectory.domain.entities.IEntity;
import java.util.List;

public interface DAO<EntityClass extends IEntity> {

	List<EntityClass> findAll();

	EntityClass findById(Long id);
	
	EntityClass findByProperty(String propertyName, Object propertyValue);

	void remove(EntityClass entity);

	void removeSafely(EntityClass entity);

	void persist(EntityClass entity);

	EntityClass merge(EntityClass entity);

	EntityManager getEntityManager();

	long size();

	Class<EntityClass> getClazz();

	EntityClass getReference(EntityClass entity);
}
