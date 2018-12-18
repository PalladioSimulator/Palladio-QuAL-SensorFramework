package de.uka.ipd.sdq.sensorframework.entities.base;

/**
 * @deprecated Superseded by EDP2.
 */
public abstract class AbstractScalabilityMeasurement

extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractMeasurement

implements de.uka.ipd.sdq.sensorframework.entities.ScalabilityMeasurement

{

    public AbstractScalabilityMeasurement(final de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myFactory) {
        super(myFactory);
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are not a composition */

    private Double[] m_timeSpan;

    @Override
    public Double[] getParameters() {
        return m_timeSpan;
    }

    @Override
    public void setParameters(final Double[] value) {
        this.m_timeSpan = value;
    };

    private de.uka.ipd.sdq.sensorframework.entities.ScalabilitySensor m_sensor;

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.ScalabilitySensor getSensor() {
        return m_sensor;
    }

    @Override
    public void setSensor(final de.uka.ipd.sdq.sensorframework.entities.ScalabilitySensor value) {
        this.m_sensor = value;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are not a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are a composition */

    /* Abstract Operations */

}
