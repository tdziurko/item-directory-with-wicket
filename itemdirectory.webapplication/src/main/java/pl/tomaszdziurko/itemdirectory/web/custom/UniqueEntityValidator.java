package pl.tomaszdziurko.itemdirectory.web.custom;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.apache.wicket.validation.validator.StringValidator;

import pl.tomaszdziurko.itemdirectory.domain.dao.DAO;
import pl.tomaszdziurko.itemdirectory.domain.entities.IEntity;

public class UniqueEntityValidator<PropertyClass> extends AbstractValidator<PropertyClass> {
	
	private DAO<? extends IEntity> dao;
	private String propertyName;
	private long entityIdToIgnore;

	public UniqueEntityValidator(DAO<? extends IEntity> dao, long entityIdToIgnore, String propertyName) {
		this(dao, propertyName);
		this.entityIdToIgnore = entityIdToIgnore;
	}
	
	public UniqueEntityValidator(DAO<? extends IEntity> dao, String propertyName) {
		this.dao = dao;
		this.propertyName = propertyName;
	}

	@Override
	protected void onValidate(IValidatable<PropertyClass> validatable) {
		IEntity entity = dao.findByProperty(propertyName, validatable.getValue());
		
		if(entity != null && entity.getId().longValue() != entityIdToIgnore) {
			error(validatable);
		}
		
		StringValidator a;
	}
	
	
}