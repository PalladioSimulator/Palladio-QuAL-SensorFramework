/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.dao.file.entities;

import de.uka.ipd.sdq.sensorframework.entities.Measurement;

/**
 * @author Ihssane El-Oudghiri
 * @deprecated Superseded by EDP2.
 */
public class MeasurementImpl implements Measurement {

    protected long measurementID;
    protected double eventTime;

    public MeasurementImpl(long id, double eventTime) {
        this.eventTime = eventTime;
        this.measurementID = id;
    }

    @Override
    public double getEventTime() {
        return eventTime;
    }

    @Override
    public long getMeasurementID() {
        return measurementID;
    }

    @Override
    public void setEventTime(double value) {
        this.eventTime = value;
    }

    @Override
    public void setMeasurementID(long value) {
        this.measurementID = value;
    }

}
