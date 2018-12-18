package de.uka.ipd.sdq.sensorframework.entities.base;

/**
 * @deprecated Superseded by EDP2.
 */
public abstract class AbstractExperiment

implements de.uka.ipd.sdq.sensorframework.entities.Experiment

{

    protected transient de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myDAOFactory = null;

    public AbstractExperiment(final de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myFactory) {
        this.myDAOFactory = myFactory;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are not a composition */

    private long m_experimentID;

    @Override
    public long getExperimentID() {
        return m_experimentID;
    }

    @Override
    public void setExperimentID(final long value) {
        this.m_experimentID = value;
    };

    private String m_experimentName;

    @Override
    public String getExperimentName() {
        return m_experimentName;
    }

    @Override
    public void setExperimentName(final String value) {
        this.m_experimentName = value;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are not a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are a composition */

    private final java.util.Collection<de.uka.ipd.sdq.sensorframework.entities.Sensor> m_sensors = new java.util.ArrayList<de.uka.ipd.sdq.sensorframework.entities.Sensor>();

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.StateSensor addStateSensor(

            final de.uka.ipd.sdq.sensorframework.entities.State p_initialstate

            ,

            final String p_sensorname) {

        final de.uka.ipd.sdq.sensorframework.entities.StateSensor result = myDAOFactory.createSensorDAO().addStateSensor(

                p_initialstate

                ,

                p_sensorname);

        m_sensors.add(result);
        return result;
    }

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor addTimeSpanSensor(

            final String p_sensorname) {

        final de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor result = myDAOFactory.createSensorDAO()
                .addTimeSpanSensor(

                        p_sensorname);

        m_sensors.add(result);
        return result;
    }

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.ScalabilitySensor addScalabilitySensor(

            final String p_sensorname) {

        final de.uka.ipd.sdq.sensorframework.entities.ScalabilitySensor result = myDAOFactory.createSensorDAO()
                .addScalabilitySensor(

                        p_sensorname);

        m_sensors.add(result);
        return result;
    }

    @Override
    public void addSensor(final de.uka.ipd.sdq.sensorframework.entities.Sensor value) {
        this.m_sensors.add(value);
    }

    @Override
    public java.util.Collection<de.uka.ipd.sdq.sensorframework.entities.Sensor> getSensors() {
        return this.m_sensors;
    };

    private final java.util.Collection<de.uka.ipd.sdq.sensorframework.entities.ExperimentRun> m_experimentRuns = new java.util.ArrayList<de.uka.ipd.sdq.sensorframework.entities.ExperimentRun>();

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.ExperimentRun addExperimentRun(

            final String p_experimentdatetime) {

        final de.uka.ipd.sdq.sensorframework.entities.ExperimentRun result = myDAOFactory.createExperimentRunDAO()
                .addExperimentRun(

                        p_experimentdatetime);

        m_experimentRuns.add(result);
        return result;
    }

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.ExperimentRun addScalabilityExperimentRun(

            final String p_experimentdatetime) {

        final de.uka.ipd.sdq.sensorframework.entities.ExperimentRun result = myDAOFactory.createExperimentRunDAO()
                .addScalabilityExperimentRun(

                        p_experimentdatetime);

        m_experimentRuns.add(result);
        return result;
    }

    @Override
    public void addExperimentRun(final de.uka.ipd.sdq.sensorframework.entities.ExperimentRun value) {
        this.m_experimentRuns.add(value);
    }

    @Override
    public java.util.Collection<de.uka.ipd.sdq.sensorframework.entities.ExperimentRun> getExperimentRuns() {
        return this.m_experimentRuns;
    }

    /* Abstract Operations */

}
