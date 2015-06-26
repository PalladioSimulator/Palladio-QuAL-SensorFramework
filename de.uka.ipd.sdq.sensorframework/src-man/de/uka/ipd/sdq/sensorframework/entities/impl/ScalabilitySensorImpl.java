package de.uka.ipd.sdq.sensorframework.entities.impl;

import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
public class ScalabilitySensorImpl extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractScalabilitySensor {

    public ScalabilitySensorImpl(final IDAOFactory myFactory) {
        super(myFactory);
    }

    @Override
    public int getNbParams() {
        return 1;
    }
}
