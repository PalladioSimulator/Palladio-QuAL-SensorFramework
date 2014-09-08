/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.dao.file.entities;

import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.StateMeasurement;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;

/**
 * @author Ihssane El-Oudghiri
 * @deprecated Superseded by EDP2.
 */
public class StateMeasurementImpl extends MeasurementImpl implements StateMeasurement {

    private StateSensor sensor;
    private State state;

    public StateMeasurementImpl(long id, double eventTime, State state) {
        super(id, eventTime);
        this.state = state;
    }

    @Override
    public StateSensor getSensor() {
        return sensor;
    }

    @Override
    public State getSensorState() {
        return state;
    }

    @Override
    public void setSensor(StateSensor value) {
        this.sensor = value;
    }

    @Override
    public void setSensorState(State value) {
        this.state = value;
    }

}
