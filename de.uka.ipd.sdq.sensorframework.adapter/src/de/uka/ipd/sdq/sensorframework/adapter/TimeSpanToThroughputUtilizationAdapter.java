package de.uka.ipd.sdq.sensorframework.adapter;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.Utilization;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;

/**
 * Adapter for TimeSpanSensors to Utilization.
 * 
 * @author khameershaik
 */
public class TimeSpanToThroughputUtilizationAdapter extends DataAdapter {

    /** Width of the utilization classes. */
    public static final String UTILIZATION_WIDTH = "UTILIZATION_WIDTH";
    /** Width of the time span. */
    private static final String TIME_SPAN_WIDTH = "TIME_SPAN_WIDTH";
    /** Default width of the time span. */
    private static final double DEFAULT_TIME_SPAN_WIDTH = 10.0;

    /** Information about the TimeSpanSensor and the measurements. */
    private SensorAndMeasurements samInformation;

    /**
     * Initializes the adapter with the provided TimeSpanSensor.
     * 
     * @param samInformation
     *            Information about the TimeSpanSensor and the measurements.
     */
    public TimeSpanToThroughputUtilizationAdapter(final SensorAndMeasurements samInformation) {
        super();
        this.samInformation = samInformation;
        this.adapterProperties.put(UTILIZATION_WIDTH, Utilization.DEFAULT_BUCKET_WIDTH);
        this.adapterProperties.put(TIME_SPAN_WIDTH, DEFAULT_TIME_SPAN_WIDTH);
    }

    /**
     * {@inheritDoc}
     */
    public Object getAdaptedObject() {
        throw new RuntimeException("Is not correctly implemented.");

        // int maxHistClass = 0;
        // HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        // double histWidth = (Double) adapterProperties.get(UTILIZATION_WIDTH);
        // double spanLenght = (Double) (
        // adapterProperties.get(TIME_SPAN_WIDTH) == null
        // ? DEFAULT_TIME_SPAN_WIDTH
        // : adapterProperties.get(TIME_SPAN_WIDTH));
        // if (histWidth == 0) {
        // throw new RuntimeException("Utilization width must be > 0");
        // }
        // Utilization hist =
        // new Utilization(samInformation.getSensor().getSensorName());
        // ArrayList<Integer> count = new ArrayList<Integer>();
        // int currentCount = 0;
        // double currentTime = 0;
        // double nextLimit = spanLenght;
        // boolean first = true;
        // for (Measurement m : samInformation.getMeasurements()) {
        // currentTime = m.getEventTime();
        // if (first) {
        // nextLimit += currentTime;
        // first = false;
        // }
        // if (currentTime < nextLimit) {
        // currentCount++;
        // } else {
        // count.add(currentCount);
        // currentCount = 1;
        // nextLimit += spanLenght;
        // }
        // }
        // for (Integer singleCount : count) {
        // int utilizationClass = (int) ((singleCount + histWidth / 2)
        // / histWidth);
        // Object o = map.get(utilizationClass);
        // if (o != null) {
        // Integer oldValue = (Integer) o;
        // map.put(utilizationClass, oldValue + 1);
        // } else {
        // map.put(utilizationClass, 1);
        // }
        // if (maxHistClass < utilizationClass) {
        // maxHistClass = utilizationClass;
        // }
        // }
        // boolean firstValueFound = false;
        // for (int i = 0; i <= maxHistClass; i++) {
        // Object o = map.get(i);
        // if (o != null) {
        // firstValueFound = true;
        // hist.addEntity(new utilizationBucketInformation((Integer) o
        // / (double) samInformation.getMeasurements().size(),
        // i * histWidth));
        // } else {
        // if (firstValueFound) {
        // hist.addEntity(new UtilizationBucketInformation(0.0,
        // i * histWidth));
        // }
        // }
        // }
        // return hist;
    }
}
