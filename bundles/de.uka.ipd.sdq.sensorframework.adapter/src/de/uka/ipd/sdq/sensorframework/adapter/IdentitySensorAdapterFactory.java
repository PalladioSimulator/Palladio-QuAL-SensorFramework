package de.uka.ipd.sdq.sensorframework.adapter;

import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;

/**
 * @deprecated Superseded by EDP2.
 */
public class IdentitySensorAdapterFactory implements IAdapterFactory {

    @Override
    public boolean canAdapt(Object adaptee, Class<?> targetClass) {
        if (adaptee instanceof SensorAndMeasurements) {
            SensorAndMeasurements sam = (SensorAndMeasurements) adaptee;
            if (targetClass.isInstance(sam.getSensor())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DataAdapter getAdapter(Object adaptee) {
        return new IdentitySensorAdapter(adaptee);
    }

    @Override
    public String getMetricLabel() {
        return "";
    }

    @Override
    public String getAdapterFactoryID() {
        return "IdentityAdapterFactory";
    }

    @Override
    public boolean createsAdaptersFor(Class<?> targetClass) {
        return true;
    }

}
