/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.dao.memory;

import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.dao.IExperimentDAO;
import de.uka.ipd.sdq.sensorframework.entities.dao.IExperimentRunDAO;
import de.uka.ipd.sdq.sensorframework.entities.dao.IMeasurementDAO;
import de.uka.ipd.sdq.sensorframework.entities.dao.ISensorDAO;
import de.uka.ipd.sdq.sensorframework.entities.dao.IStateDAO;

/**
 * @author Steffen Becker
 *
 * @deprecated Superseded by EDP2.
 */
public class MemoryDAOFactory implements IDAOFactory {

    private IExperimentDAO experimentDAO;
    private IExperimentRunDAO experimentRunDAO;
    private IMeasurementDAO measurementDAO;
    private ISensorDAO sensorDAO;
    private IStateDAO stateDAO;
    private long id;

    public MemoryDAOFactory(long id) {
        this.id = id;
    }

    public MemoryDAOFactory(String config) {
        this(IDAOFactory.ID_NOT_SET);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.sensorfactory.entities.dao.IDAOFactory#createExperimentDAO()
     */
    @Override
    public IExperimentDAO createExperimentDAO() {
        if (this.experimentDAO == null) {
            this.experimentDAO = new MemoryExperimentDAO(this);
        }
        return this.experimentDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.sensorfactory.entities.dao.IDAOFactory#createExperimentRunDAO()
     */
    @Override
    public IExperimentRunDAO createExperimentRunDAO() {
        if (this.experimentRunDAO == null) {
            this.experimentRunDAO = new MemoryExperimentRunDAO(this);
        }
        return this.experimentRunDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.sensorfactory.entities.dao.IDAOFactory#createMeasurementDAO()
     */
    @Override
    public IMeasurementDAO createMeasurementDAO() {
        if (this.measurementDAO == null) {
            this.measurementDAO = new MemoryMeasurementDAO(this);
        }
        return this.measurementDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.sensorfactory.entities.dao.IDAOFactory#createSensorDAO()
     */
    @Override
    public ISensorDAO createSensorDAO() {
        if (this.sensorDAO == null) {
            this.sensorDAO = new MemorySensorDAO(this);
        }
        return this.sensorDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.sensorfactory.entities.dao.IDAOFactory#createStateDAO()
     */
    @Override
    public IStateDAO createStateDAO() {
        if (this.stateDAO == null) {
            this.stateDAO = new MemoryStateDAO(this);
        }
        return this.stateDAO;
    }

    @Override
    public String getDescription() {
        return "A datasource stored in memory. Changes are lost, when unsafed";
    }

    @Override
    public String getName() {
        return "Memory Datasource";
    }

    @Override
    public void finalizeAndClose() {
    }

    @Override
    public long getID() {
        return this.id;
    }

    @Override
    public void setID(long i) {
        this.id = i;
    }

    @Override
    public String getPersistendInfo() {
        return "";
    }

    @Override
    public void reload() {
        // Nothing to do here
    }

    @Override
    public void store() {
        // Nothing to do here
    }
}
