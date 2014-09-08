package de.uka.ipd.sdq.sensorframework.adapter;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.Histogram;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;

/**
 * @deprecated Superseded by EDP2.
 */
public class TimeSpanSensorToThroughputHistogramFactory implements IAdapterFactory {

    @Override
    public boolean canAdapt(Object adaptee, Class<?> targetClass) {
        // if (adaptee instanceof SensorAndMeasurements){
        // SensorAndMeasurements sam = (SensorAndMeasurements) adaptee;
        // if (sam.getSensor() instanceof TimeSpanSensor && Histogram.class == targetClass)
        // return true;
        // }
        return false;
    }

    @Override
    public DataAdapter getAdapter(Object adaptee) {
        return new TimeSpanToThroughputHistogramAdapter((SensorAndMeasurements) adaptee);
    }

    @Override
    public String getMetricLabel() {
        return "Throughput";
    }

    @Override
    public String getAdapterFactoryID() {
        return "TimeSpanSensorToThroughputHistrogramFactory";
    }

    @Override
    public boolean createsAdaptersFor(Class<?> targetClass) {
        return targetClass.isAssignableFrom(Histogram.class);
    }
}
