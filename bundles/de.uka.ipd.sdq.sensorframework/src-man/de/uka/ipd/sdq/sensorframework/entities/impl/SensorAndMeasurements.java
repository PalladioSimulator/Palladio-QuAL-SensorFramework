package de.uka.ipd.sdq.sensorframework.entities.impl;

import java.util.Collection;

import de.uka.ipd.sdq.sensorframework.entities.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;

/**
 * @deprecated Superseded by EDP2.
 */
public class SensorAndMeasurements {// TODO why dow it not implement AbstractSensorAndMeasurements?
    private final Sensor mySensor;
    private final Collection<Measurement> myMeasurements;

    public SensorAndMeasurements(Sensor s, Collection<Measurement> m) {// TODO checks for null-ness?
        this.mySensor = s;
        this.myMeasurements = m;
    }

    public Sensor getSensor() {
        return mySensor;
    }

    public Collection<Measurement> getMeasurements() {
        return myMeasurements;
    }
}
