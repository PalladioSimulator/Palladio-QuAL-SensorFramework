package de.uka.ipd.sdq.sensorframework.entities.base;

/**
 * @deprecated Superseded by EDP2.
 */
public abstract class AbstractStateMeasurement

extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractMeasurement

implements de.uka.ipd.sdq.sensorframework.entities.StateMeasurement

{

    public AbstractStateMeasurement(final de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myFactory) {
        super(myFactory);
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are not a composition */

    private de.uka.ipd.sdq.sensorframework.entities.State m_sensorState;

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.State getSensorState() {
        return m_sensorState;
    }

    @Override
    public void setSensorState(final de.uka.ipd.sdq.sensorframework.entities.State value) {
        this.m_sensorState = value;
    };

    private de.uka.ipd.sdq.sensorframework.entities.StateSensor m_sensor;

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.StateSensor getSensor() {
        return m_sensor;
    }

    @Override
    public void setSensor(final de.uka.ipd.sdq.sensorframework.entities.StateSensor value) {
        this.m_sensor = value;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are not a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are a composition */

    /* Abstract Operations */

}
