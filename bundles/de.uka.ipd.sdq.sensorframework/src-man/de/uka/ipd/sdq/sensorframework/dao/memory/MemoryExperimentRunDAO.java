package de.uka.ipd.sdq.sensorframework.dao.memory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.dao.IExperimentRunDAO;
import de.uka.ipd.sdq.sensorframework.entities.impl.ExperimentRunImpl;
import de.uka.ipd.sdq.sensorframework.entities.impl.ScalabilityExperimentRunImpl;

/**
 * TODO
 *
 * @deprecated Superseded by EDP2.
 */
public class MemoryExperimentRunDAO implements IExperimentRunDAO {

    private long nextID = 0;
    private final IDAOFactory myFactory;
    private final HashMap<Long, ExperimentRun> index = new HashMap<Long, ExperimentRun>();

    public MemoryExperimentRunDAO(IDAOFactory memoryDAOFactory) {
        this.myFactory = memoryDAOFactory;
    }

    @Override
    public synchronized ExperimentRun addExperimentRun(String p_experimentdatetime) {
        ExperimentRun result = new ExperimentRunImpl(this.myFactory);
        result.setExperimentRunID(nextID++);
        result.setExperimentDateTime(p_experimentdatetime);

        index.put(result.getExperimentRunID(), result);
        return result;
    }

    @Override
    public synchronized ExperimentRun addScalabilityExperimentRun(String p_experimentdatetime) {
        ExperimentRun result = new ScalabilityExperimentRunImpl(this.myFactory);
        result.setExperimentRunID(nextID++);
        result.setExperimentDateTime(p_experimentdatetime);

        index.put(result.getExperimentRunID(), result);
        return result;
    }

    @Override
    public synchronized ExperimentRun get(long id) {
        return index.get(id);
    }

    @Override
    public synchronized Collection<ExperimentRun> getExperimentRuns() {
        return Collections.unmodifiableCollection(index.values());
    }

    public void store(ExperimentRun er) {
    }

    @Override
    public synchronized void removeExperimentRun(ExperimentRun experimentRun, boolean doCascade) {
        if (experimentRun == null) {
            return;
        }

        if (doCascade == true) {
            // remove all measurements
            for (Measurement measurement : experimentRun.getMeasurements()) {
                myFactory.createMeasurementDAO().removeMeasurement(measurement, true);
            }
        }
        index.remove(experimentRun.getExperimentRunID());
    }

    @Override
    public void storeAll() {
        // Nothing to do here
    }

}
