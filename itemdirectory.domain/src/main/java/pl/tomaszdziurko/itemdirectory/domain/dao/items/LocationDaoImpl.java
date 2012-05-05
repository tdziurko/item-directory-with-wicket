package pl.tomaszdziurko.itemdirectory.domain.dao.items;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pl.tomaszdziurko.itemdirectory.domain.dao.AbstractDAO;
import pl.tomaszdziurko.itemdirectory.domain.entities.items.Location;

@Repository(value = "locationDao")
public class LocationDaoImpl extends AbstractDAO<Location> implements LocationDao {
	
	@PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

	public Class<Location> getClazz() {
		return Location.class;
	}

	public Location findByName(String locationName) {
		Query findByNameQuery = entityManager.createQuery("from " + getClazz().getSimpleName() 
				+ "  entity where entity.name = :aName");
		findByNameQuery.setParameter("aName", locationName);
		
		return (Location) findByNameQuery.getSingleResult();
	}

}
