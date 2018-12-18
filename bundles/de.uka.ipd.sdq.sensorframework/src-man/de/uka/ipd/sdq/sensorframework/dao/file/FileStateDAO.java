/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.dao.file;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import de.uka.ipd.sdq.sensorframework.dao.file.entities.StateImpl;
import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.dao.IStateDAO;

/**
 * @author Ihssane El-Oudghiri
 * @author Steffen Becker
 * 
 *         Data Access Object (DAO) for persistence of State Objects.
 * @deprecated Superseded by EDP2.
 */
public class FileStateDAO extends AbstractFileDAO<State> implements IStateDAO {

    public FileStateDAO(FileDAOFactory factory, IDGenerator idGen) {
        super(factory, idGen, FileDAOFactory.STATE_FILE_NAME_PREFIX);
    }

    @Override
    public State addState(String p_stateliteral) {
        State state = new StateImpl(this.factory);
        state.setStateID(idGen.getNextStateID());
        state.setStateLiteral(p_stateliteral);

        this.putEntity(state);

        return state;
    }

    @Override
    public Collection<State> findByStateLiteral(String searchKey) {
        Collection<State> result = new ArrayList<State>();
        for (State state : getAllEntities()) {
            if (state.getStateLiteral().equals(searchKey)) {
                result.add(state);
            }
        }
        return Collections.unmodifiableCollection(result);
    }

    @Override
    public Collection<State> getStates() {
        return Collections.unmodifiableCollection(getAllEntities());
    }

    @Override
    public void removeState(State state, boolean doCascade) {
        this.removeEntity(state, doCascade);
    }
}
