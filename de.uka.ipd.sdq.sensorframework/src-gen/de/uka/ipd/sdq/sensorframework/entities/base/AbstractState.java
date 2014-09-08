package de.uka.ipd.sdq.sensorframework.entities.base;

/**
 * @deprecated Superseded by EDP2.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "STATE")
public abstract class AbstractState

implements de.uka.ipd.sdq.sensorframework.entities.State

{

    protected transient de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myDAOFactory = null;

    public AbstractState(de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory myFactory) {
        this.myDAOFactory = myFactory;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are not a composition */

    @javax.persistence.Id
    @javax.persistence.Column(name = "STATEID")
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long m_stateID;

    @Override
    public long getStateID() {
        return m_stateID;
    }

    @Override
    public void setStateID(long value) {
        this.m_stateID = value;
    };

    @javax.persistence.Column(name = "STATELITERAL")
    private String m_stateLiteral;

    @Override
    public String getStateLiteral() {
        return m_stateLiteral;
    }

    @Override
    public void setStateLiteral(String value) {
        this.m_stateLiteral = value;
    }

    /* Getter and Setter for Properties with cardinality 0..1 or 1 which are a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are not a composition */

    /* Getter and Setter for Properties with cardinality 0..* which are a composition */

    /* Abstract Operations */

}
