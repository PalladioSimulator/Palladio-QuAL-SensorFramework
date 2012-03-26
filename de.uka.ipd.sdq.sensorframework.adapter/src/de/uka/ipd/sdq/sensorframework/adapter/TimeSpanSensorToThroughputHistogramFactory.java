package de.uka.ipd.sdq.sensorframework.adapter;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.Histogram;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;

public class TimeSpanSensorToThroughputHistogramFactory implements IAdapterFactory {

	public boolean canAdapt(Object adaptee, Class<?> targetClass) {
		if (adaptee instanceof SensorAndMeasurements){
			SensorAndMeasurements sam = (SensorAndMeasurements) adaptee;
			if (sam.getSensor() instanceof TimeSpanSensor && Histogram.class == targetClass)
				return true;
		}
		return false;
	}
	
	public DataAdapter getAdapter(Object adaptee) {
		return new TimeSpanToThroughputHistogramAdapter((SensorAndMeasurements) adaptee);
	}

	public String getMetricLabel() {
		return "Throughput";
	}

	public String getAdapterFactoryID() {
		return "TimeSpanSensorToThroughputHistrogramFactory";
	}
	
	public boolean createsAdaptersFor(Class<?> targetClass) {
		return targetClass.isAssignableFrom(Histogram.class);
	}
}
