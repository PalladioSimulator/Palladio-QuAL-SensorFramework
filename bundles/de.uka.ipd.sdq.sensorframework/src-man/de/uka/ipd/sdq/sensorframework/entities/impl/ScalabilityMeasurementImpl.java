package de.uka.ipd.sdq.sensorframework.entities.impl;

import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
public class ScalabilityMeasurementImpl extends
de.uka.ipd.sdq.sensorframework.entities.base.AbstractScalabilityMeasurement {

    /**
     * Difference up to which two values are considered as equal.
     */
    public static final double EPSILON_ERROR = 1e-5;

    public ScalabilityMeasurementImpl(final IDAOFactory myFactory) {
        super(myFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParameters(final Double[] value) {
        // if (value < -EPSILON_ERROR)
        // throw new
        // RuntimeException("TimeSpan Measurements are not allowed to be smaller than 0.");
        // if (value < 0)
        // value = 0;

        super.setParameters(value);
    }
}
