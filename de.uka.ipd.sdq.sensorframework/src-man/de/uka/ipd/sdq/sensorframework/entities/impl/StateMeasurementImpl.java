package de.uka.ipd.sdq.sensorframework.entities.impl;

import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
@javax.persistence.Entity
public class StateMeasurementImpl extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractStateMeasurement {

    public StateMeasurementImpl(IDAOFactory myFactory) {
        super(myFactory);
    }
}
