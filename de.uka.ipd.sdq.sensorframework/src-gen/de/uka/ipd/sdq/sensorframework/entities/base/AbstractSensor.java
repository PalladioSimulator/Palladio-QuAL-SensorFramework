package de.uka.ipd.sdq.sensorframework.entities.base;

/**
 * @deprecated Superseded by EDP2.
 */
public abstract class AbstractSensor

implements de.uka.ipd.sdq.sensorframework.entities.Sensor

{

    protected transient de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myDAOFactory = null;

    public AbstractSensor(final de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myFactory) {
        this.myDAOFactory = myFactory;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are not a composition */

    private String m_sensorName;

    @Override
    public String getSensorName() {
        return m_sensorName;
    }

    @Override
    public void setSensorName(final String value) {
        this.m_sensorName = value;
    };

    private long m_sensorID;

    @Override
    public long getSensorID() {
        return m_sensorID;
    }

    @Override
    public void setSensorID(final long value) {
        this.m_sensorID = value;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are not a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are a composition */

    /* Abstract Operations */

}
