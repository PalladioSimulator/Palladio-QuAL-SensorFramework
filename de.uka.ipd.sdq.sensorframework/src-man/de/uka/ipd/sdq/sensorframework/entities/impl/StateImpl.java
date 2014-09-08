package de.uka.ipd.sdq.sensorframework.entities.impl;

import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
@javax.persistence.Entity
public class StateImpl extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractState {

    public StateImpl(IDAOFactory myFactory) {
        super(myFactory);
    }
}
