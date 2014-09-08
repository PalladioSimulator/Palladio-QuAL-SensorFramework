package de.uka.ipd.sdq.sensorframework.adapter;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.TimeSeries;
import de.uka.ipd.sdq.sensorframework.entities.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanMeasurement;

/**
 * Adapter for TimeSpanSensor to TimeSeries.
 * 
 * @author groenda
 * @deprecated Superseded by EDP2.
 */
public class TimeSpanToTimeSeriesAdapter extends DataAdapter {

    // private FilteredMeasurementsCollection measurements;
    // private static final String ACTIVEDE_FILTERS = "ACTIVEDE_FILTERS";
    /** Information about the TimeSpanSensor and the measurements. */
    private final SensorAndMeasurements samInformation;

    /**
     * Initializes the adapter with the provided TimeSpanSensor.
     * 
     * @param samInformation
     *            Information about the TimeSpanSensor and the measurements.
     */
    public TimeSpanToTimeSeriesAdapter(final SensorAndMeasurements samInformation) {
        super();
        this.samInformation = samInformation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAdaptedObject() {
        TimeSeries series = new TimeSeries(samInformation.getSensor().getSensorName());
        for (Measurement m : samInformation.getMeasurements()) {
            series.add(((TimeSpanMeasurement) m).getEventTime(), ((TimeSpanMeasurement) m).getTimeSpan());
        }
        return series;
    }
}
