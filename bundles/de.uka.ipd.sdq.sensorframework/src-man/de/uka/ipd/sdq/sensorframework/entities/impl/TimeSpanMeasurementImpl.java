package de.uka.ipd.sdq.sensorframework.entities.impl;

import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
public class TimeSpanMeasurementImpl extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractTimeSpanMeasurement {

    /**
     * Difference up to which two values are considered as equal.
     */
    public static final double EPSILON_ERROR = 1e-5;

    public TimeSpanMeasurementImpl(final IDAOFactory myFactory) {
        super(myFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTimeSpan(double value) {
        if (value < -EPSILON_ERROR) {
            throw new RuntimeException("TimeSpan Measurements are not allowed to be smaller than 0.");
        }
        if (value < 0) {
            value = 0;
        }

        super.setTimeSpan(value);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TimeSpanMeasurementImpl: ID=" + this.getMeasurementID() + ", " + "eventTime=" + this.getEventTime()
                + ", timeSpan=" + this.getTimeSpan();
    }
}
