/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.adapter.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes.Utilization;
import de.uka.ipd.sdq.sensorframework.adapter.StateToUtilizationAdapter;
import de.uka.ipd.sdq.sensorframework.dao.file.entities.StateImpl;
import de.uka.ipd.sdq.sensorframework.dao.file.entities.StateMeasurementImpl;
import de.uka.ipd.sdq.sensorframework.dao.file.entities.StateSensorImpl;
import de.uka.ipd.sdq.sensorframework.entities.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;
import de.uka.ipd.sdq.sensorframework.entities.State;

/**Tests the calculation of CPU utilization in time intervals.
 * @author groenda
 *
 */
public class StateToUtlizationAdapterTest {
	/** Accuracy for comparing two floating point values for equality. */
	static final double EPSILON = 0.000001;
	static final State idle = new StateImpl(null);
	static final State busy = new StateImpl(null);
	
	public StateToUtlizationAdapterTest() {
		idle.setStateLiteral("Idle");
		busy.setStateLiteral("Non-Idle/Busy");
	}

	/**Creates and adapter for the provided sensor and initializes it.
	 * @param sam The sensor and its measurements.
	 * @param bucketWidth The width of a single time interval for which the utilization is calculated.
	 * @return The adapter.
	 */
	protected StateToUtilizationAdapter createAndInitializeAdapter(
			SensorAndMeasurements sam, Double bucketWidth) {
		StateToUtilizationAdapter testedAdapter = new StateToUtilizationAdapter(sam);
		testedAdapter.setProperties(new Properties());
		testedAdapter.getProperties().put(StateToUtilizationAdapter.UTILIZATION_WIDTH, bucketWidth);
		return testedAdapter;
	}

	@Test
	public void testNonZeroStartTimeEndsWithBusy() {
		// prepare sensor and measurements
		Sensor stateSensor = new StateSensorImpl(null);
		// add measurements themselves
		Collection<Measurement> measurements = new ArrayList<Measurement>();
		measurements.add(new StateMeasurementImpl(0, 0.10, idle));
		measurements.add(new StateMeasurementImpl(1, 0.14, busy));
		SensorAndMeasurements sam = new SensorAndMeasurements(stateSensor, measurements);
		// test
		Double bucketWidth = new Double(0.1);
		StateToUtilizationAdapter testedAdapter = createAndInitializeAdapter(
				sam, bucketWidth);
		// check result
		Utilization result = (Utilization) testedAdapter.getAdaptedObject();
		Assert.assertEquals( (double) bucketWidth, result.getBucketWidth(), EPSILON);
		Assert.assertEquals("There must be exactly the number of buckets for the test data", 2, result.getBucketInformation().size());
		Assert.assertEquals("Bucket 0 must have value.", 0.0, result.getBucketInformation().get(0).getUtilization(), EPSILON);
		Assert.assertEquals("Bucket 1 must have value.", 0.6, result.getBucketInformation().get(1).getUtilization(), EPSILON);
	}

	@Test
	public void testNonZeroStartOnlyIdle() {
		// prepare sensor and measurements
		Sensor stateSensor = new StateSensorImpl(null);
		// add measurements themselves
		Collection<Measurement> measurements = new ArrayList<Measurement>();
		measurements.add(new StateMeasurementImpl(0, 0.10, idle));
		measurements.add(new StateMeasurementImpl(1, 0.14, idle));
		SensorAndMeasurements sam = new SensorAndMeasurements(stateSensor, measurements);
		// test
		Double bucketWidth = new Double(0.1);
		StateToUtilizationAdapter testedAdapter = createAndInitializeAdapter(
				sam, bucketWidth);
		// check result
		Utilization result = (Utilization) testedAdapter.getAdaptedObject();
		Assert.assertEquals( (double) bucketWidth, result.getBucketWidth(), EPSILON);
		Assert.assertEquals("There must be exactly the number of buckets for the test data", 2, result.getBucketInformation().size());
		Assert.assertEquals("Bucket 0 must have value.", 0.0, result.getBucketInformation().get(0).getUtilization(), EPSILON);
		Assert.assertEquals("Bucket 1 must have value.", 0.0, result.getBucketInformation().get(1).getUtilization(), EPSILON);
	}

	@Test
	public void testNonZeroStartOnlyBusy() {
		// prepare sensor and measurements
		Sensor stateSensor = new StateSensorImpl(null);
		// add measurements themselves
		Collection<Measurement> measurements = new ArrayList<Measurement>();
		measurements.add(new StateMeasurementImpl(0, 0.02, busy));
		measurements.add(new StateMeasurementImpl(1, 0.10, busy));
		SensorAndMeasurements sam = new SensorAndMeasurements(stateSensor, measurements);
		// test
		Double bucketWidth = new Double(0.1);
		StateToUtilizationAdapter testedAdapter = createAndInitializeAdapter(
				sam, bucketWidth);
		// check result
		Utilization result = (Utilization) testedAdapter.getAdaptedObject();
		Assert.assertEquals( (double) bucketWidth, result.getBucketWidth(), EPSILON);
		Assert.assertEquals("There must be exactly the number of buckets for the test data", 2, result.getBucketInformation().size());
		Assert.assertEquals("Bucket 0 must have value.", 0.8, result.getBucketInformation().get(0).getUtilization(), EPSILON);
		Assert.assertEquals("Bucket 1 must have value.", 1.0, result.getBucketInformation().get(1).getUtilization(), EPSILON);
	}
	
	@Test
	public void testNonZeroStartTimeEndsWithIdle() {
		// prepare sensor and measurements
		Sensor stateSensor = new StateSensorImpl(null);
		// add measurements themselves
		Collection<Measurement> measurements = new ArrayList<Measurement>();
		measurements.add(new StateMeasurementImpl(0, 0.01, busy));
		measurements.add(new StateMeasurementImpl(1, 0.05, idle));
		SensorAndMeasurements sam = new SensorAndMeasurements(stateSensor, measurements);
		// test
		Double bucketWidth = new Double(0.1);
		StateToUtilizationAdapter testedAdapter = createAndInitializeAdapter(
				sam, bucketWidth);
		// check result
		Utilization result = (Utilization) testedAdapter.getAdaptedObject();
		Assert.assertEquals( (double) bucketWidth, result.getBucketWidth(), EPSILON);
		Assert.assertEquals("There must be exactly the number of buckets for the test data", 1, result.getBucketInformation().size());
		Assert.assertEquals("Bucket 0 must have value.", 0.4, result.getBucketInformation().get(0).getUtilization(), EPSILON);
	}	
	
	@Test
	public void testZeroStartTime() {
		// prepare sensor and measurements
		Sensor stateSensor = new StateSensorImpl(null);
		// add measurements themselves
		Collection<Measurement> measurements = new ArrayList<Measurement>();
		measurements.add(new StateMeasurementImpl(0, 0.0, idle));
		measurements.add(new StateMeasurementImpl(1, 0.1, busy));
		measurements.add(new StateMeasurementImpl(2, 0.14, idle));
		SensorAndMeasurements sam = new SensorAndMeasurements(stateSensor, measurements);
		// test
		Double bucketWidth = new Double(0.1);
		StateToUtilizationAdapter testedAdapter = createAndInitializeAdapter(
				sam, bucketWidth);
		// check result
		Utilization result = (Utilization) testedAdapter.getAdaptedObject();
		Assert.assertEquals( (double) bucketWidth, result.getBucketWidth(), EPSILON);
		Assert.assertEquals("There must be exactly the number of buckets for the test data", 2, result.getBucketInformation().size());
		Assert.assertEquals("Bucket 0 must have value.", 0.0, result.getBucketInformation().get(0).getUtilization(), EPSILON);
		Assert.assertEquals("Bucket 1 must have value.", 0.4, result.getBucketInformation().get(1).getUtilization(), EPSILON);
	}
	
	@Test
	public void testEndsAtBucketWidth() {
		// prepare sensor and measurements
		Sensor stateSensor = new StateSensorImpl(null);
		// add measurements themselves
		Collection<Measurement> measurements = new ArrayList<Measurement>();
		measurements.add(new StateMeasurementImpl(0, 0.0, busy));
		measurements.add(new StateMeasurementImpl(1, 0.1, idle));
		SensorAndMeasurements sam = new SensorAndMeasurements(stateSensor, measurements);
		// test
		Double bucketWidth = new Double(0.1);
		StateToUtilizationAdapter testedAdapter = createAndInitializeAdapter(
				sam, bucketWidth);
		// check result
		Utilization result = (Utilization) testedAdapter.getAdaptedObject();
		Assert.assertEquals( (double) bucketWidth, result.getBucketWidth(), EPSILON);
		Assert.assertEquals("There must be exactly the number of buckets for the test data", 2, result.getBucketInformation().size());
		Assert.assertEquals("Bucket 0 must have value.", 1.0, result.getBucketInformation().get(0).getUtilization(), EPSILON);
		Assert.assertEquals("Bucket 1 must have value.", 0.0, result.getBucketInformation().get(1).getUtilization(), EPSILON);
	}
	
	@Test
	public void testNoMeasurements() {
		// prepare sensor and measurements
		Sensor stateSensor = new StateSensorImpl(null);
		// add measurements themselves
		Collection<Measurement> measurements = new ArrayList<Measurement>();
		SensorAndMeasurements sam = new SensorAndMeasurements(stateSensor, measurements);
		// test
		Double bucketWidth = new Double(0.1);
		StateToUtilizationAdapter testedAdapter = createAndInitializeAdapter(
				sam, bucketWidth);
		// check result
		Utilization result = (Utilization) testedAdapter.getAdaptedObject();
		Assert.assertEquals( (double) bucketWidth, result.getBucketWidth(), EPSILON);
		Assert.assertEquals("There must be exactly the number of buckets for the test data", 1, result.getBucketInformation().size());
		Assert.assertEquals("Bucket 0 must have value.", 0.0, result.getBucketInformation().get(0).getUtilization(), EPSILON);
	}
	
}
