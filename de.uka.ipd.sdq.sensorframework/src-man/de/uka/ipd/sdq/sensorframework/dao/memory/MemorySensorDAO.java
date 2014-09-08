package de.uka.ipd.sdq.sensorframework.dao.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import de.uka.ipd.sdq.sensorframework.entities.ScalabilitySensor;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.dao.ISensorDAO;
import de.uka.ipd.sdq.sensorframework.entities.impl.ScalabilitySensorImpl;
import de.uka.ipd.sdq.sensorframework.entities.impl.StateSensorImpl;
import de.uka.ipd.sdq.sensorframework.entities.impl.TimeSpanSensorImpl;

/**
 * TODO
 *
 * @deprecated Superseded by EDP2.
 */
public class MemorySensorDAO implements ISensorDAO {

    private final IDAOFactory myFactory;
    private long nextID = 0;
    private final HashMap<Long, Sensor> index = new HashMap<Long, Sensor>();

    public MemorySensorDAO(IDAOFactory memoryDAOFactory) {
        this.myFactory = memoryDAOFactory;
    }

    @Override
    public synchronized StateSensor addStateSensor(State p_initialstate, String p_sensorname) {
        StateSensor result = new StateSensorImpl(myFactory);
        result.setSensorID(nextID++);
        result.setInitialState(p_initialstate);
        result.setSensorName(p_sensorname);

        index.put(result.getSensorID(), result);
        return result;
    }

    @Override
    public synchronized TimeSpanSensor addTimeSpanSensor(String p_sensorname) {
        TimeSpanSensor result = new TimeSpanSensorImpl(myFactory);
        result.setSensorID(nextID++);
        result.setSensorName(p_sensorname);

        index.put(result.getSensorID(), result);
        return result;
    }

    @Override
    public synchronized ScalabilitySensor addScalabilitySensor(String p_sensorname) {
        ScalabilitySensor result = new ScalabilitySensorImpl(myFactory);
        result.setSensorID(nextID++);
        result.setSensorName(p_sensorname);

        index.put(result.getSensorID(), result);
        return result;
    }

    @Override
    public synchronized Sensor get(long id) {
        if (!index.containsKey(id)) {
            throw new RuntimeException("Attempt to retrieve non-existing sensor.");
        }
        return index.get(id);
    }

    @Override
    public synchronized Collection<Sensor> getSensors() {
        return Collections.unmodifiableCollection(index.values());
    }

    @Override
    public synchronized Collection<Sensor> findBySensorName(String searchKey) {
        ArrayList<Sensor> result = new ArrayList<Sensor>();
        for (Sensor e : this.index.values()) {
            if (e.getSensorName().equals(searchKey)) {
                result.add(e);
            }
        }
        return Collections.unmodifiableCollection(result);
    }

    @Override
    public synchronized void removeSensor(Sensor sensor, boolean doCascade) {
        if (sensor == null) {
            return;
        }

        if (doCascade == true) {
            if (sensor instanceof StateSensor) {
                // remove the states
                for (State state : ((StateSensor) sensor).getSensorStates()) {
                    myFactory.createStateDAO().removeState(state, true);
                }
            }
        }

        index.remove(sensor.getSensorID());
    }

    public void store(Sensor s) {
    }

    @Override
    public void storeAll() {
        // Nothing to do here
    }

}
