package de.uka.ipd.sdq.sensorframework.visualisation.views;

import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
public class ExperimentAndDAO {
    private final IDAOFactory datasource;
    private final Experiment experiment;

    public ExperimentAndDAO(IDAOFactory datasource, Experiment experiment) {
        super();
        this.datasource = datasource;
        this.experiment = experiment;
    }

    public IDAOFactory getDatasource() {
        return datasource;
    }

    public Experiment getExperiment() {
        return experiment;
    }
}
