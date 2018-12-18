/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.dao.file.entities;

import de.uka.ipd.sdq.sensorframework.dao.file.FileDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @author Ihssane El-Oudghiri
 * @deprecated Superseded by EDP2.
 */
public class StateImpl extends AbstractFileEntity implements State, SerializableEntity {

    private static final long serialVersionUID = -458584924706735994L;
    private long stateID;
    private String stateLiteral;

    public StateImpl(IDAOFactory factory) {
        super(factory);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) {
            return false;
        }
        State s = (State) obj;
        if (!(stateID == s.getStateID() && stateLiteral.equals(s.getStateLiteral()))) {
            return false;
        }
        return true;
    }

    public String getFileName() {
        return FileDAOFactory.STATE_FILE_NAME_PREFIX + getStateID();
    }

    @Override
    public long getID() {
        return this.stateID;
    }

    @Override
    public long getStateID() {
        return stateID;
    }

    @Override
    public String getStateLiteral() {
        return stateLiteral;
    }

    public void serializeChildren() {
        // Nothing to serialize here
    }

    @Override
    public void setFactory(FileDAOFactory factory) {
    }

    @Override
    public void setStateID(long value) {
        this.stateID = value;
    }

    @Override
    public void setStateLiteral(String value) {
        this.stateLiteral = value;
    }
}
