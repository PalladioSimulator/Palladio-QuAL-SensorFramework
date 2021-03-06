/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.dao.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.dao.IStateDAO;
import de.uka.ipd.sdq.sensorframework.entities.impl.StateImpl;

/**
 * @author Steffen Becker
 *
 * @deprecated Superseded by EDP2.
 */
public class MemoryStateDAO implements IStateDAO {

    private long nextID = 0;
    private final IDAOFactory myFactory;
    private final HashMap<Long, State> index = new HashMap<Long, State>();

    public MemoryStateDAO(IDAOFactory myFactory) {
        this.myFactory = myFactory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.sensorfactory.entities.dao.IStateDAO#addState(java.lang.String)
     */
    @Override
    public synchronized State addState(String p_stateliteral) {
        State result = new StateImpl(myFactory);
        result.setStateID(nextID++);
        result.setStateLiteral(p_stateliteral);

        index.put(result.getStateID(), result);
        return result;
    }

    @Override
    public synchronized State get(long id) {
        return index.get(id);
    }

    @Override
    public synchronized Collection<State> getStates() {
        return Collections.unmodifiableCollection(index.values());
    }

    @Override
    public synchronized Collection<State> findByStateLiteral(String searchKey) {
        ArrayList<State> result = new ArrayList<State>();
        for (State e : this.index.values()) {
            if (e.getStateLiteral().equals(searchKey)) {
                result.add(e);
            }
        }
        return Collections.unmodifiableCollection(result);
    }

    public void store(StateSensor stateSen) {
    }

    @Override
    public synchronized void removeState(State state, boolean doCascade) {
        if (state == null) {
            return;
        }

        index.remove(state.getStateID());
    }

    public void store(State st) {
    }

    @Override
    public void storeAll() {
        // Nothing to do here
    }

}
