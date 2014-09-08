package de.uka.ipd.sdq.sensorframework.adapter;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.TimeSeries;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;

/**
 * @deprecated Superseded by EDP2.
 */
public class TimeSpanSensorToQuantilFactory implements IAdapterFactory {

    @Override
    public boolean canAdapt(Object adaptee, Class<?> targetClass) {
        if (adaptee instanceof SensorAndMeasurements) {
            SensorAndMeasurements sam = (SensorAndMeasurements) adaptee;
            if (sam.getSensor() instanceof TimeSpanSensor && TimeSeries.class == targetClass) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DataAdapter getAdapter(Object adaptee) {
        return new TimeSpanToQuantilAdapter((SensorAndMeasurements) adaptee);
    }

    @Override
    public String getMetricLabel() {
        return "Quantil";
    }

    @Override
    public String getAdapterFactoryID() {
        return "TimeSpanSensorToQuantilFactory";
    }

    @Override
    public boolean createsAdaptersFor(Class<?> targetClass) {
        return targetClass.isAssignableFrom(TimeSeries.class);
    }
}
