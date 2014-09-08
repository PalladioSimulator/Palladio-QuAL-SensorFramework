/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.dao.file.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import de.uka.ipd.sdq.sensorframework.dao.file.FileDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.ScalabilityMeasurement;
import de.uka.ipd.sdq.sensorframework.entities.ScalabilitySensor;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;
import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.StateMeasurement;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanMeasurement;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * Entity implementation for Experiment Runs. Contains BackgroundMemoryLists to quickly store the
 * huge amounts of sensor data generated by simulations.
 * 
 * @author Ihssane El-Oudghiri
 * @author Steffen Becker
 * @deprecated Superseded by EDP2.
 */
public class ExperimentRunImpl extends AbstractFileEntity implements ExperimentRun, Serializable {

    private static final long serialVersionUID = 6496657460961660218L;

    /**
     * Persistent ID of this entity
     */
    private long experimentRunID;

    /**
     * Date and Time of the experiment run
     */
    private String experimentDateTime;

    /**
     * Contains the measurements and event times for each sensor in this experiment run. Hashes
     * sensor ID on the sensor's measurements. It is transient as it uses fast BackgroundMemoryLists
     * which persist themselves
     */
    private transient HashMap<Long, AbstractSensorAndMeasurements> measurementsForSensor;

    /**
     * ID of the Experiment of this Experiment Run. Used for internal checks
     */
    public long idOfParentExperiment;

    public ExperimentRunImpl(IDAOFactory factory) {
        super(factory);
        measurementsForSensor = new HashMap<Long, AbstractSensorAndMeasurements>();
    }

    /*
     * Operation is unsupported as the file provider relies on fast SensorAndMeasurement objects
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.sensorframework.entities.ExperimentRun#addMeasurement(de.uka.ipd.sdq.
     * sensorframework.entities.Measurement)
     */
    @Override
    public void addMeasurement(Measurement value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ScalabilityMeasurement addScalabilityMeasurement(ScalabilitySensor p_sensor, Double[] p_parameters,
            double p_result) {
        AbstractSensorAndMeasurements sam = saveGetSensorAndMeasurements(p_sensor);
        ((ScalabilitySensorAndMeasurement) sam).addResult(p_parameters, p_result);

        // We do not really have a DAO, hence we do not support returning an measurement entity.
        // This is uncritical as our caller is also not expecting to get one
        return null;
    }

    @Override
    public StateMeasurement addStateMeasurement(StateSensor p_sensor, State p_sensorstate, double p_eventtime) {
        if (p_sensor == null || p_sensorstate == null) {
            throw new IllegalArgumentException("p_sensor or p_sensorstate is null, eventtime is " + p_eventtime);
        }

        AbstractSensorAndMeasurements sam = saveGetSensorAndMeasurements(p_sensor);
        ((StateSensorAndMeasurement) sam).addState(p_eventtime, p_sensorstate);

        // We do not really have a DAO, hence we do not support returning an measurement entity.
        // This is uncritical as our caller is also not expecting to get one
        return null;
    }

    @Override
    public TimeSpanMeasurement addTimeSpanMeasurement(TimeSpanSensor p_sensor, double p_eventtime, double p_timespan) {
        AbstractSensorAndMeasurements sam = saveGetSensorAndMeasurements(p_sensor);
        ((TimeSpanSensorAndMeasurement) sam).addTimeSpan(p_eventtime, p_timespan);

        // We do not really have a DAO, hence we do not support returning an measurement entity.
        // This is uncritical as our caller is also not expecting to get one
        return null;
    }

    private AbstractSensorAndMeasurements createMeasurementStorage(Sensor sensor) {
        AbstractSensorAndMeasurements sam = null;
        try {
            if (sensor instanceof TimeSpanSensor) {
                sam = new TimeSpanSensorAndMeasurement(((FileDAOFactory) factory).getFileManager(), this, sensor);
            } else if (sensor instanceof StateSensor) {
                sam = new StateSensorAndMeasurement(((FileDAOFactory) factory).getFileManager(), this, sensor);
            } else if (sensor instanceof ScalabilitySensor) {
                sam = new ScalabilitySensorAndMeasurement(((FileDAOFactory) factory).getFileManager(), this, sensor);
            } else {
                throw new RuntimeException("Invalid sensor type found: " + sensor.getClass()
                        + " is not a TimeSpanSensor, a StateSensor, "
                        + "or a Scalability sensor - fix your implementation, please!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        measurementsForSensor.put(sensor.getSensorID(), sam);
        return sam;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ExperimentRunImpl)) {
            return false;
        }
        ExperimentRunImpl er = (ExperimentRunImpl) obj;

        if (!(experimentRunID == er.getExperimentRunID() && experimentDateTime.equals(er.getExperimentDateTime()))) {
            return false;
        }

        return true;
    }

    @Override
    public String getExperimentDateTime() {
        return experimentDateTime;
    }

    @Override
    public long getExperimentRunID() {
        return experimentRunID;
    }

    @Override
    public long getID() {
        return this.getExperimentRunID();
    }

    @Override
    public Collection<Measurement> getMeasurements() {
        ArrayList<Measurement> m = new ArrayList<Measurement>();

        for (Sensor s : getParentExperiment().getSensors()) {
            m.addAll(getMeasurementsOfSensor(s).getMeasurements());
        }
        return m;
    }

    @Override
    public SensorAndMeasurements getMeasurementsOfSensor(Sensor sensor) {
        if (!getParentExperiment().getSensors().contains(sensor)) {
            throw new IllegalArgumentException("Error: Sensor given is not part of this experiment "
                    + sensor.getSensorName());
        }
        AbstractSensorAndMeasurements sam = saveGetSensorAndMeasurements(sensor);
        return new SensorAndMeasurements(sensor, sam.getMeasurements());
    }

    private Experiment getParentExperiment() {
        return factory.createExperimentDAO().get(idOfParentExperiment);
    }

    /**
     * Returns the sensor and measurement object to the given sensor - creating it if neccessary
     * 
     * @param sensor
     *            The sensor of the SAM
     * @return The created SAM
     */
    private AbstractSensorAndMeasurements saveGetSensorAndMeasurements(Sensor sensor) {
        AbstractSensorAndMeasurements sam = measurementsForSensor.get(sensor.getSensorID());
        if (sam == null) {
            sam = createMeasurementStorage(sensor);
        }
        return sam;
    }

    @Override
    public void setExperimentDateTime(String experimetDateTime) {
        this.experimentDateTime = experimetDateTime;
    }

    @Override
    public void setExperimentRunID(long experimentRunID) {
        this.experimentRunID = experimentRunID;
    }

    @Override
    public void setFactory(FileDAOFactory factory) {
        super.setFactory(factory);
        if (measurementsForSensor == null) {
            measurementsForSensor = new HashMap<Long, AbstractSensorAndMeasurements>();
        }
    }

    public void setParentExperimentID(long id) {
        this.idOfParentExperiment = id;
    }

}
