package de.uka.ipd.sdq.sensorframework.dao.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IExperimentDAO;
import de.uka.ipd.sdq.sensorframework.entities.impl.ExperimentImpl;

/**
 * @author Steffen Becker
 *
 * @deprecated Superseded by EDP2.
 */
public class MemoryExperimentDAO implements IExperimentDAO {

    private long nextID = 0;
    private final HashMap<Long, Experiment> index = new HashMap<Long, Experiment>();

    private final MemoryDAOFactory factory;

    public MemoryExperimentDAO(MemoryDAOFactory memoryDAOFactory) {
        this.factory = memoryDAOFactory;
    }

    @Override
    public synchronized Experiment addExperiment(String p_experimentname) {
        ExperimentImpl result = new ExperimentImpl(factory);
        result.setExperimentID(nextID++);
        result.setExperimentName(p_experimentname);

        index.put(result.getExperimentID(), result);

        return result;
    }

    @Override
    public synchronized Experiment get(long id) {
        return index.get(id);
    }

    @Override
    public synchronized Collection<Experiment> getExperiments() {
        return Collections.unmodifiableCollection(index.values());
    }

    /** {@inheritDoc} */
    @Override
    public synchronized Collection<Experiment> findByExperimentName(String searchKey) {
        ArrayList<Experiment> result = new ArrayList<Experiment>();
        for (Experiment e : this.index.values()) {
            if (e.getExperimentName().equals(searchKey)) {
                result.add(e);
            }
        }
        return Collections.unmodifiableCollection(result);
    }

    public void store(Experiment e) {
    }

    @Override
    public synchronized void removeExperiment(Experiment experiment, boolean doCascade) {
        if (experiment == null) {
            return;
        }

        if (doCascade == true) {
            // remove all experiment runs
            for (ExperimentRun expRun : experiment.getExperimentRuns()) {
                factory.createExperimentRunDAO().removeExperimentRun(expRun, true);
            }
            // remove all sensors
            for (Sensor sensor : experiment.getSensors()) {
                factory.createSensorDAO().removeSensor(sensor, true);
            }
        }
        index.remove(experiment.getExperimentID());
    }

    @Override
    public void storeAll() {
        // Nothing to do here
    }

}
