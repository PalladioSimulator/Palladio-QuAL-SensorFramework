package de.uka.ipd.sdq.sensorframework.entities.impl;

import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
@javax.persistence.Entity
public class StateSensorImpl extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractStateSensor {

    public StateSensorImpl(IDAOFactory myFactory) {
        super(myFactory);
    }
}
