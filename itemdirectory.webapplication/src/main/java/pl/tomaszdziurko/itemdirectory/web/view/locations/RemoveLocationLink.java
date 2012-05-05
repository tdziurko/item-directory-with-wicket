package pl.tomaszdziurko.itemdirectory.web.view.locations;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import pl.tomaszdziurko.itemdirectory.domain.entities.items.Location;
import pl.tomaszdziurko.itemdirectory.service.items.LocationService;
import sun.jdbc.odbc.ee.ObjectPool;

public class RemoveLocationLink extends Link<Void> {
	
	@SpringBean
	LocationService locationService;
	private final Location location;
	

	public RemoveLocationLink(String componentId, Location location) {
		super(componentId);
		this.location = location;
	}

	@Override
	public void onClick() {
		locationService.remove(location);
        getSession().info(new StringResourceModel("locationRemoved", this, null, new Object[] {location.getName()}).getString());
		setResponsePage(LocationsPage.class);
	}

}
