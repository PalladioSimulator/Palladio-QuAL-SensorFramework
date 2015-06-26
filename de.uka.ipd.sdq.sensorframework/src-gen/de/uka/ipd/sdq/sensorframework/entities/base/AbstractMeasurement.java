package de.uka.ipd.sdq.sensorframework.entities.base;

/**
 * @deprecated Superseded by EDP2.
 */
public abstract class AbstractMeasurement

implements de.uka.ipd.sdq.sensorframework.entities.Measurement

{

    protected transient de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myDAOFactory = null;

    public AbstractMeasurement(final de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myFactory) {
        this.myDAOFactory = myFactory;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are not a composition */

    private long m_measurementID;

    @Override
    public long getMeasurementID() {
        return m_measurementID;
    }

    @Override
    public void setMeasurementID(final long value) {
        this.m_measurementID = value;
    };

    private double m_eventTime;

    @Override
    public double getEventTime() {
        return m_eventTime;
    }

    @Override
    public void setEventTime(final double value) {
        this.m_eventTime = value;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are not a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are a composition */

    /* Abstract Operations */

}
