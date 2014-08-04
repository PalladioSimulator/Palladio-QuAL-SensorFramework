package de.uka.ipd.sdq.sensorframework.adapter;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.Utilization;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;

public class StateSensorToUtilizationFactory implements IAdapterFactory {

    public boolean canAdapt(Object adaptee, Class<?> targetClass) {
        if (adaptee instanceof SensorAndMeasurements) {
            SensorAndMeasurements sam = (SensorAndMeasurements) adaptee;
            if (sam.getSensor() instanceof StateSensor && Utilization.class == targetClass)
                return true;
        }
        return false;
    }

    public DataAdapter getAdapter(Object adaptee) {
        return new StateToUtilizationAdapter((SensorAndMeasurements) adaptee);
    }

    public String getMetricLabel() {
        return "Utilization";
    }

    public String getAdapterFactoryID() {
        return "StateSensorToUtilizationFactory";
    }

    public boolean createsAdaptersFor(Class<?> targetClass) {
        return targetClass.isAssignableFrom(Utilization.class);
    }
}
