package de.uka.ipd.sdq.sensorframework.adapter;

import java.util.Collection;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.Utilization;
import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.UtilizationBucketInformation;
import de.uka.ipd.sdq.sensorframework.entities.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;
import de.uka.ipd.sdq.sensorframework.entities.StateMeasurement;

/**
 * Adapter converting {@link StateMeasurement}, e.g. for CPU Resource consumption, to Utilization
 * measurements.
 * 
 * @author groenda
 */
public class StateToUtilizationAdapter extends DataAdapter {

    /**
     * The textual identifier used in the state description to determine an idling resource. Please
     * note that any other state is considered non-idling.
     */
    public static final String IDLE_STATE_REPRESENTATION = "Idle";

    /** The identifier for the property "Utilization Width". */
    public static final String UTILIZATION_WIDTH = "UTILIZATION_WIDTH";

    /** Information about the TimeSpanSensor and the measurements. */
    private SensorAndMeasurements samInformation;

    /**
     * Initializes a new adapter for the provided TimeSpanSensor.
     * 
     * @param sensorAndMeasurements
     *            Information about the TimeSpanSensor and the measurements.
     */
    public StateToUtilizationAdapter(final SensorAndMeasurements sensorAndMeasurements) {
        super();
        this.samInformation = sensorAndMeasurements;
        initializeUtilizationInterval();
    }

    /**
     * Sets the initial size of a bucket for which a utilization value is calculated.
     */
    protected void initializeUtilizationInterval() {
        /*
         * Check if there would be at least to different buckets for the Utilization as JFreeChart
         * otherwise displays a bar with default width and the small values are very difficult to
         * identify.
         */
        if (samInformation.getMeasurements().size() <= 1) {
            this.adapterProperties.put(UTILIZATION_WIDTH, Utilization.DEFAULT_BUCKET_WIDTH);
            return;
        }
        StateMeasurement stateMeasurement = null;
        double minEventTimeValue = Double.MAX_VALUE, maxEventTimeValue = Double.MIN_VALUE;
        for (Measurement measurement : samInformation.getMeasurements()) {
            stateMeasurement = (StateMeasurement) measurement;
            minEventTimeValue = (minEventTimeValue < stateMeasurement.getEventTime()) ? minEventTimeValue
                    : stateMeasurement.getEventTime();
            maxEventTimeValue = (maxEventTimeValue > stateMeasurement.getEventTime()) ? maxEventTimeValue
                    : stateMeasurement.getEventTime();
        }
        if (maxEventTimeValue - minEventTimeValue < Utilization.DEFAULT_BUCKET_WIDTH) {
            // whole utilization would be in one bucket, thus set bucket width to 1/2 of total
            // width.
            this.adapterProperties.put(UTILIZATION_WIDTH, (maxEventTimeValue - minEventTimeValue)
                    / Utilization.DEFAULT_NUMBER_BUCKETS);
        } else {
            double currentNumberOfBuckets = (maxEventTimeValue - minEventTimeValue) / Utilization.DEFAULT_BUCKET_WIDTH;
            if (currentNumberOfBuckets > Utilization.MAXIMUM_NUMBER_OF_BUCKETS) {
                this.adapterProperties.put(UTILIZATION_WIDTH, (maxEventTimeValue - minEventTimeValue)
                        / Utilization.MAXIMUM_NUMBER_OF_BUCKETS);
            } else {
                this.adapterProperties.put(UTILIZATION_WIDTH, Utilization.DEFAULT_BUCKET_WIDTH);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Object getAdaptedObject() {
        double utilWidth = (Double) adapterProperties.get(UTILIZATION_WIDTH);
        if (utilWidth <= 0) {
            throw new RuntimeException("Utilization width must be > 0");
        }
        Utilization utilization = new Utilization(samInformation.getSensor().getSensorName(), utilWidth);

        Collection<Measurement> measurements = samInformation.getMeasurements();
        StateMeasurement stateMmt;
        double lastEventTime = 0.0;
        boolean lastStateIsIdle = true;
        /** Utilization of the current bucket. */
        double currentUtilization = 0.0;
        long currentInterval = 0;
        for (Measurement measurement : measurements) {
            // read
            stateMmt = (StateMeasurement) measurement;
            // process
            if (stateMmt.getEventTime() >= (currentInterval + 1) * utilWidth) { // measurement
                                                                                // belongs to
                                                                                // subsequent
                                                                                // bucket. Finish
                                                                                // current bucket
                // finish current bucket
                if (!lastStateIsIdle) {
                    currentUtilization += (currentInterval + 1) * utilWidth - lastEventTime;
                }
                utilization.addEntity(new UtilizationBucketInformation(currentUtilization / utilWidth, currentInterval
                        * utilWidth));
                currentInterval++;
                // finish buckets until the one containing the current measurement is reached
                while (stateMmt.getEventTime() > (currentInterval + 1) * utilWidth) { // bucket
                                                                                      // without
                                                                                      // measurements
                    if (lastStateIsIdle) {
                        currentUtilization = 0;
                    } else {
                        currentUtilization = 1;
                    }
                    utilization.addEntity(new UtilizationBucketInformation(currentUtilization / utilWidth,
                            currentInterval * utilWidth));
                    currentInterval++;
                }
                // start processing bucket which contains the current measurement
                if (lastStateIsIdle) {
                    currentUtilization = 0;
                } else {
                    currentUtilization = stateMmt.getEventTime() - (currentInterval * utilWidth);
                }
            } else { // measurement belongs to current bucket
                if (!lastStateIsIdle) {
                    currentUtilization += stateMmt.getEventTime() - lastEventTime;
                }
            }
            // continue
            lastEventTime = stateMmt.getEventTime();
            lastStateIsIdle = stateMmt.getSensorState().getStateLiteral().equals(IDLE_STATE_REPRESENTATION);
        }
        // finish currently open bucket
        if (!lastStateIsIdle) {
            currentUtilization += (currentInterval + 1) * utilWidth - lastEventTime;
        }
        utilization.addEntity(new UtilizationBucketInformation(currentUtilization / utilWidth, currentInterval
                * utilWidth));
        return utilization;
    }

}
