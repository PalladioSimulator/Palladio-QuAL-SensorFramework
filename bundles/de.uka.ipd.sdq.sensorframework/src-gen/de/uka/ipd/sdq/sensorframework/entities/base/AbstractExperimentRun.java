package de.uka.ipd.sdq.sensorframework.entities.base;

/**
 * @deprecated Superseded by EDP2.
 */
public abstract class AbstractExperimentRun

implements de.uka.ipd.sdq.sensorframework.entities.ExperimentRun

{

    protected transient de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myDAOFactory = null;

    public AbstractExperimentRun(final de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myFactory) {
        this.myDAOFactory = myFactory;
    }

    /*
     * Getter and Setter for Properties with cardinality 0..1 or 1 which are not a composition
     */

    private String m_experimentDateTime;

    @Override
    public String getExperimentDateTime() {
        return m_experimentDateTime;
    }

    @Override
    public void setExperimentDateTime(final String value) {
        this.m_experimentDateTime = value;
    };

    private long m_experimentRunID;

    @Override
    public long getExperimentRunID() {
        return m_experimentRunID;
    }

    @Override
    public void setExperimentRunID(final long value) {
        this.m_experimentRunID = value;
    }

    /*
     * Getter and Setter for Properties with cardinality 0..1 or 1 which are a composition
     */

    /*
     * Getter and Setter for Properties with cardinality 0..* which are not a composition
     */

    /*
     * Getter and Setter for Properties with cardinality 0..* which are a composition
     */

    private final java.util.Collection<de.uka.ipd.sdq.sensorframework.entities.Measurement> m_measurements = new java.util.ArrayList<de.uka.ipd.sdq.sensorframework.entities.Measurement>();

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.StateMeasurement addStateMeasurement(

            final de.uka.ipd.sdq.sensorframework.entities.StateSensor p_sensor,

            final de.uka.ipd.sdq.sensorframework.entities.State p_sensorstate

            ,

            final double p_eventtime) {

        final de.uka.ipd.sdq.sensorframework.entities.StateMeasurement result = myDAOFactory.createMeasurementDAO()
                .addStateMeasurement(

                        p_sensor, p_sensorstate

                        ,

                        p_eventtime);

        m_measurements.add(result);
        return result;
    }

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.TimeSpanMeasurement addTimeSpanMeasurement(

            final de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor p_sensor

            ,

            final double p_eventtime,

            final double p_timespan) {

        final de.uka.ipd.sdq.sensorframework.entities.TimeSpanMeasurement result = myDAOFactory.createMeasurementDAO()
                .addTimeSpanMeasurement(

                        p_sensor

                        ,

                        p_eventtime, p_timespan);

        m_measurements.add(result);
        return result;
    }

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.ScalabilityMeasurement addScalabilityMeasurement(

            final de.uka.ipd.sdq.sensorframework.entities.ScalabilitySensor p_sensor

            , final Double[] p_parameters,

            final double p_result

            ) {

        final de.uka.ipd.sdq.sensorframework.entities.ScalabilityMeasurement result = myDAOFactory.createMeasurementDAO()
                .addScalabilityMeasurement(

                        p_sensor

                        ,

                        p_parameters, p_result);

        m_measurements.add(result);
        return result;
    }

    @Override
    public void addMeasurement(final de.uka.ipd.sdq.sensorframework.entities.Measurement value) {
        this.m_measurements.add(value);
    }

    @Override
    public java.util.Collection<de.uka.ipd.sdq.sensorframework.entities.Measurement> getMeasurements() {
        return this.m_measurements;
    }

    /* Abstract Operations */

    @Override
    public abstract de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements

    getMeasurementsOfSensor(

            de.uka.ipd.sdq.sensorframework.entities.Sensor sensor

            );

}
