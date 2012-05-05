package pl.tomaszdziurko.itemdirectory.domain.entities;

import java.io.Serializable;

public interface IEntity extends Serializable {

    Long getId();

    boolean isNew();

    boolean isPersisted();

}
