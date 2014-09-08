/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.dao.file.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import de.uka.ipd.sdq.sensorframework.dao.file.FileDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.ScalabilitySensor;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @author Ihssane El-Oudghiri
 * @deprecated Superseded by EDP2.
 */
public class ExperimentImpl extends AbstractFileEntity implements Experiment, SerializableEntity {

    /**
     * Serialisation ID
     */
    private static final long serialVersionUID = 2145970711062278629L;

    /**
     * ID of this Experiment
     */
    private long experimentID;

    /**
     * Name of this experiment
     */
    private String experimentName;

    /**
     * Collection of IDs of sensors in this experiment.
     */
    private final Collection<Long> sensors;

    /**
     * Collection of IDs of ExperimentRuns of this experiment.
     */
    private final Collection<Long> experimentRuns;

    public ExperimentImpl(IDAOFactory factory) {
        super(factory);
        sensors = new ArrayList<Long>();
        experimentRuns = new ArrayList<Long>();
    }

    @Override
    public void addExperimentRun(ExperimentRun experimentRun) {
        experimentRuns.add(experimentRun.getExperimentRunID());
    }

    @Override
    public ExperimentRun addExperimentRun(String experimentdatetime) {
        ExperimentRun expRun = factory.createExperimentRunDAO().addExperimentRun(experimentdatetime);
        experimentRuns.add(expRun.getExperimentRunID());
        ((ExperimentRunImpl) expRun).setParentExperimentID(this.getID());

        return expRun;
    }

    @Override
    public ExperimentRun addScalabilityExperimentRun(String experimentdatetime) {
        ExperimentRun expRun = factory.createExperimentRunDAO().addScalabilityExperimentRun(experimentdatetime);
        experimentRuns.add(expRun.getExperimentRunID());
        ((ExperimentRunImpl) expRun).setParentExperimentID(this.getID());

        return expRun;
    }

    @Override
    public ScalabilitySensor addScalabilitySensor(String sensorName) {
        ScalabilitySensor scals = factory.createSensorDAO().addScalabilitySensor(sensorName);
        sensors.add(scals.getSensorID());
        return scals;
    }

    @Override
    public void addSensor(Sensor value) {
        sensors.add(value.getSensorID());
    }

    @Override
    public StateSensor addStateSensor(State p_initialstate, String p_sensorname) {
        StateSensor stsen = factory.createSensorDAO().addStateSensor(p_initialstate, p_sensorname);
        sensors.add(stsen.getSensorID());
        return stsen;
    }

    @Override
    public TimeSpanSensor addTimeSpanSensor(String sensorName) {
        TimeSpanSensor tss = factory.createSensorDAO().addTimeSpanSensor(sensorName);
        sensors.add(tss.getSensorID());
        return tss;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ExperimentImpl)) {
            return false;
        }
        ExperimentImpl exp = (ExperimentImpl) obj;

        if (!(experimentID == exp.getExperimentID() && experimentName.equals(exp.getExperimentName()))) {
            return false;
        }

        if (!(experimentRuns.equals(exp.getExperimentRuns()))) {
            return false;
        }

        if (!(sensors.equals(exp.getSensors()))) {
            return false;
        }

        return true;
    }

    @Override
    public long getExperimentID() {
        return experimentID;
    }

    @Override
    public String getExperimentName() {
        return experimentName;
    }

    @Override
    public Collection<ExperimentRun> getExperimentRuns() {
        ArrayList<ExperimentRun> result = new ArrayList<ExperimentRun>();
        for (Long id : experimentRuns) {
            result.add(factory.createExperimentRunDAO().get(id));
        }
        return Collections.unmodifiableCollection(result);
    }

    public String getFileName() {
        return FileDAOFactory.EXP_FILE_NAME_PREFIX + getExperimentID();
    }

    @Override
    public long getID() {
        return this.getExperimentID();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.sensorframework.entities.Experiment#getSensors()
     */
    @Override
    public Collection<Sensor> getSensors() {
        ArrayList<Sensor> result = new ArrayList<Sensor>();
        for (Long id : sensors) {
            result.add(factory.createSensorDAO().get(id));
        }
        return Collections.unmodifiableCollection(result);
    }

    @Override
    public void setExperimentID(long experimentID) {
        this.experimentID = experimentID;
    }

    @Override
    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }

    @Override
    public void setFactory(FileDAOFactory factory) {
        this.factory = factory;
    }

}
