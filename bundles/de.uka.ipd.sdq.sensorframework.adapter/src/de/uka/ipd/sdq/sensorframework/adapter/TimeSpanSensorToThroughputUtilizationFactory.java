package de.uka.ipd.sdq.sensorframework.adapter;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.Utilization;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;

/**
 * @deprecated Superseded by EDP2.
 */
public class TimeSpanSensorToThroughputUtilizationFactory implements IAdapterFactory {

    @Override
    public boolean canAdapt(Object adaptee, Class<?> targetClass) {
        // if (adaptee instanceof SensorAndMeasurements){
        // SensorAndMeasurements sam = (SensorAndMeasurements) adaptee;
        // if (sam.getSensor() instanceof TimeSpanSensor && Utilization.class == targetClass)
        // return true;
        // }
        return false;
    }

    @Override
    public DataAdapter getAdapter(Object adaptee) {
        return new TimeSpanToThroughputUtilizationAdapter((SensorAndMeasurements) adaptee);
    }

    @Override
    public String getMetricLabel() {
        return "Throughput";
    }

    @Override
    public String getAdapterFactoryID() {
        return "TimeSpanSensorToThroughputUtilizationFactory";
    }

    @Override
    public boolean createsAdaptersFor(Class<?> targetClass) {
        return targetClass.isAssignableFrom(Utilization.class);
    }
}
