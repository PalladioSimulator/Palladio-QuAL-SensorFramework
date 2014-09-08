package de.uka.ipd.sdq.sensorframework.entities.impl;

import java.util.HashMap;
import java.util.Map;

import de.uka.ipd.sdq.sensorframework.entities.ScalabilityExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
@javax.persistence.Entity
public class ScalabilityExperimentRunImpl extends ExperimentRunImpl implements ScalabilityExperimentRun {

    private String[] paramNames = null;

    private final Map<String, Integer> constParameters = new HashMap<String, Integer>();

    public ScalabilityExperimentRunImpl(IDAOFactory myFactory) {
        super(myFactory);
    }

    @Override
    public void setVarParameterNames(String[] names) {
        paramNames = names;
    }

    @Override
    public String[] getVarParameterNames() {
        return paramNames;
    }

    @Override
    public Map<String, Integer> getConstParameters() {
        return constParameters;
    }

    @Override
    public void addConstParameter(String parameterName, int parameterValue) {
        constParameters.put(parameterName, parameterValue);
    }

}
