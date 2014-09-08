package de.uka.ipd.sdq.sensorframework.entities.impl;

import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
@javax.persistence.Entity
public class TimeSpanSensorImpl extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractTimeSpanSensor {

    public TimeSpanSensorImpl(IDAOFactory myFactory) {
        super(myFactory);
    }
}
