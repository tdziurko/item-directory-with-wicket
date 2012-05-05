package pl.tomaszdziurko.itemdirectory.service.items;

import java.util.List;
import pl.tomaszdziurko.itemdirectory.domain.entities.items.Location;


public interface LocationService {
	
	Location findById(Long id);

	void save(Location location);
	
	Location update(Location location);
	
	void remove(Location location);
	
	long size();

	Location findByName(String locationName);

	List<Location> findAll();		
	
}
