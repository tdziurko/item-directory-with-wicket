package pl.tomaszdziurko.itemdirectory.web.view.locations;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

import pl.tomaszdziurko.itemdirectory.domain.dao.items.LocationDao;
import pl.tomaszdziurko.itemdirectory.domain.entities.items.Location;
import pl.tomaszdziurko.itemdirectory.service.items.LocationService;
import pl.tomaszdziurko.itemdirectory.web.BasePage;
import pl.tomaszdziurko.itemdirectory.web.custom.UniqueEntityValidator;

public class AddEditLocationPage extends BasePage {
	
	private static final int MIN_LOCATION_NAME_LENGTH = 5;
	private static final int MAX_LOCATION_NAME_LENGTH = 255;
	
	@SpringBean
	private LocationService locationService;
	
	@SpringBean
	private LocationDao locationDao;
	
	public AddEditLocationPage() {
		setDefaultModel(new Model<Location>(new Location()));
		initGui();
	}
	
	public AddEditLocationPage(Long locationId) {
		Location location = locationService.findById(locationId);
		setDefaultModel(new Model<Location>(location));
		initGui();
	}

	private void initGui() {

		Form<Location> addLocationForm = new Form<Location>("addLocationForm",
				new CompoundPropertyModel<Location>((Model<Location>)getDefaultModel()));
		add(addLocationForm);
		
		Label nameLabel = new Label("nameLabel", new StringResourceModel("locationName", this, null));
		addLocationForm.add(nameLabel);

		addLocationForm.add(createLabelFieldWithValidation());
		
		Button submitButton = new Button("submitButton") {
			@Override
			public void onSubmit() {
				Location location = getLocationFromPageModel();
				
				if(location.isNew()) {
					locationService.save(location);
					getSession().info(new StringResourceModel("locationAdded", this, null).getString());
				}
				else {
					locationService.update(location);
                    getSession().info(new StringResourceModel("locationUpdated", this, null).getString());
				}
				
				setResponsePage(LocationsPage.class);
			}
		};
		addLocationForm.add(submitButton);
	}

	private RequiredTextField<String> createLabelFieldWithValidation() {
		RequiredTextField<String> nameField = new RequiredTextField<String>("name");
		nameField.setLabel(new StringResourceModel("locationName", this, null));
		nameField.add(StringValidator.lengthBetween(MIN_LOCATION_NAME_LENGTH, MAX_LOCATION_NAME_LENGTH));
		
		nameField.add(createUniqueLocationNameValidator());
		
		return nameField;
	}

	private UniqueEntityValidator<String> createUniqueLocationNameValidator() {
		UniqueEntityValidator<String> locationNameValidator = null;
		if(getLocationFromPageModel().isNew()) {
			locationNameValidator = new UniqueEntityValidator<String>(locationDao, "name");
		}
		else {
			locationNameValidator = new UniqueEntityValidator<String>(locationDao, getLocationFromPageModel().getId(), "name");
		}
		
		return locationNameValidator;
	}
	
	@SuppressWarnings("unchecked")
	private Location getLocationFromPageModel() {
		return (Location) getDefaultModel().getObject();
	}
}
