package pl.tomaszdziurko.itemdirectory.web.view.locations;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import pl.tomaszdziurko.itemdirectory.domain.entities.items.Location;
import pl.tomaszdziurko.itemdirectory.service.items.LocationService;
import pl.tomaszdziurko.itemdirectory.web.BasePage;

public class LocationsPage extends BasePage {
	
	@SpringBean
	private LocationService locationService;

	public LocationsPage() {
		initGui();
	}

	private void initGui() {
		addLocationsmodule();
		addCreateNewLocationLink();
	}

	private void addLocationsmodule() {
		ListView<Location> locations = new ListView<Location>("locations", createModelForLocations()) {
			@Override
			protected void populateItem(final ListItem<Location> item) {
				item.add(new Label("id", new PropertyModel<Location>(item.getModel(), "id")));	
				item.add(new Label("name", new PropertyModel<Location>(item.getModel(), "name")));	
				
				Link<BasePage> editLocationLink = new Link<BasePage>("editLocationLink") {
					@Override
					public void onClick() {
						setResponsePage(new AddEditLocationPage(item.getModelObject().getId()));
					}
				};
				
				item.add(editLocationLink);
				item.add(new RemoveLocationLink("removeLocationLink", item.getModelObject()));
			}
		};
		
		locations.setVisible(!locations.getList().isEmpty());
		add(locations);
		
		Label noLocationsLabel = new Label("noLocationsLabel", "There are no locations in the database. Maybe you can add one?");
		noLocationsLabel.setVisible(!locations.isVisible());
		add(noLocationsLabel);
		
	}
	
	private LoadableDetachableModel<List<Location>> createModelForLocations() {
		
		return new LoadableDetachableModel<List<Location>>() {

			@Override
			protected List<Location> load() {
				return locationService.findAll();
			}
		};
	}
	

	private void addCreateNewLocationLink() {
		add(new Link<BasePage>("addLocationPageLink") {

			@Override
			public void onClick() {
				setResponsePage(new AddEditLocationPage());
			}
		});
	}

}
