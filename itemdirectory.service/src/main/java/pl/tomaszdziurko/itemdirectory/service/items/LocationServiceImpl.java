package pl.tomaszdziurko.itemdirectory.service.items;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.tomaszdziurko.itemdirectory.domain.dao.items.LocationDao;
import pl.tomaszdziurko.itemdirectory.domain.entities.items.Location;

@Service(value = "locationService")
@Transactional(rollbackFor = Exception.class)
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationDao locationDao;

	public long size() {
		return locationDao.size();
	}

	public Location findById(Long id) {
		return locationDao.findById(id);
	}

	public void remove(Location location) {
		locationDao.removeSafely(location);
	}

	public void save(Location location) {
		locationDao.persist(location);
	}

	public Location update(Location location) {
		return locationDao.merge(location);
	}

	public Location findByName(String locationName) {
		return locationDao.findByName(locationName);
	}

	public List<Location> findAll() {
		return locationDao.findAll();
	}

}
