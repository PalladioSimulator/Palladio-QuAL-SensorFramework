package de.uka.ipd.sdq.sensorframework.entities.base;

/**
 * @deprecated Superseded by EDP2.
 */
public abstract class AbstractStateSensor

extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractSensor

implements de.uka.ipd.sdq.sensorframework.entities.StateSensor

{

    public AbstractStateSensor(final de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myFactory) {
        super(myFactory);
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are not a composition */

    private de.uka.ipd.sdq.sensorframework.entities.State m_initialState;

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.State getInitialState() {
        return m_initialState;
    }

    @Override
    public void setInitialState(final de.uka.ipd.sdq.sensorframework.entities.State value) {
        this.m_initialState = value;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are not a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are a composition */

    private final java.util.Collection<de.uka.ipd.sdq.sensorframework.entities.State> m_sensorStates = new java.util.ArrayList<de.uka.ipd.sdq.sensorframework.entities.State>();

    @Override
    public de.uka.ipd.sdq.sensorframework.entities.State addState(

            final String p_stateliteral) {

        final de.uka.ipd.sdq.sensorframework.entities.State result = myDAOFactory.createStateDAO().addState(

                p_stateliteral);

        m_sensorStates.add(result);
        return result;
    }

    @Override
    public void addSensorState(final de.uka.ipd.sdq.sensorframework.entities.State value) {
        this.m_sensorStates.add(value);
    }

    @Override
    public java.util.Collection<de.uka.ipd.sdq.sensorframework.entities.State> getSensorStates() {
        return this.m_sensorStates;
    }

    /* Abstract Operations */

}
