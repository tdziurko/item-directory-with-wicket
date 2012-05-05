package pl.tomaszdziurko.itemdirectory.domain.dao.items;

import pl.tomaszdziurko.itemdirectory.domain.dao.DAO;
import pl.tomaszdziurko.itemdirectory.domain.entities.items.Location;
import pl.tomaszdziurko.itemdirectory.domain.entities.users.User;

public interface LocationDao extends DAO<Location> {

	Location findByName(String locationName);
	
}
