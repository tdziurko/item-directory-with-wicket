package pl.tomaszdziurko.itemdirectory.domain.entities.items;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pl.tomaszdziurko.itemdirectory.domain.entities.AbstractEntity;

@javax.persistence.Entity
@Table(name = "locations")
public class Location extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=255, nullable=false)
    private String name;

	public Location() {
		super();
	}

	public Location(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
