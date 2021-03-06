package de.uka.ipd.sdq.sensorframework.entities.base;

/**
 * @deprecated Superseded by EDP2.
 */
public abstract class AbstractTimeSpanMeasurement

extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractMeasurement

implements de.uka.ipd.sdq.sensorframework.entities.TimeSpanMeasurement

{

    public AbstractTimeSpanMeasurement(final de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myFactory) {
        super(myFactory);
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are not a composition */

    private double m_timeSpan;

    @Override
    public double getTimeSpan() {
        return m_timeSpan;
    }

    @Override
    public void setTimeSpan(final double value) {
        this.m_timeSpan = value;
    };

    private de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor m_sensor;

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor getSensor() {
        return m_sensor;
    }

    @Override
    public void setSensor(final de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor value) {
        this.m_sensor = value;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are not a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are a composition */

    /* Abstract Operations */

}
