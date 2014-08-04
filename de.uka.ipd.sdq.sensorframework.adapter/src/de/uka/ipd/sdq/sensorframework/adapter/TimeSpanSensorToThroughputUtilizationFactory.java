package de.uka.ipd.sdq.sensorframework.adapter;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.Utilization;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;

public class TimeSpanSensorToThroughputUtilizationFactory implements IAdapterFactory {

    public boolean canAdapt(Object adaptee, Class<?> targetClass) {
        // if (adaptee instanceof SensorAndMeasurements){
        // SensorAndMeasurements sam = (SensorAndMeasurements) adaptee;
        // if (sam.getSensor() instanceof TimeSpanSensor && Utilization.class == targetClass)
        // return true;
        // }
        return false;
    }

    public DataAdapter getAdapter(Object adaptee) {
        return new TimeSpanToThroughputUtilizationAdapter((SensorAndMeasurements) adaptee);
    }

    public String getMetricLabel() {
        return "Throughput";
    }

    public String getAdapterFactoryID() {
        return "TimeSpanSensorToThroughputUtilizationFactory";
    }

    public boolean createsAdaptersFor(Class<?> targetClass) {
        return targetClass.isAssignableFrom(Utilization.class);
    }
}
