package de.uka.ipd.sdq.sensorframework.dao.file.entities;

import de.uka.ipd.sdq.sensorframework.dao.file.FileDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @author Ihssane El-Oudghiri
 * 
 * @deprecated Superseded by EDP2.
 */
public abstract class SensorImpl extends AbstractFileEntity implements Sensor, SerializableEntity {

    /**
     * For Serialization
     */
    private static final long serialVersionUID = 1L;

    protected long sensorID;

    protected String sensorName;

    public SensorImpl(IDAOFactory factory) {
        super(factory);
    }

    public String getFileName() {
        return FileDAOFactory.SENSOR_FILE_NAME_PREFIX + getSensorID();
    }

    @Override
    public long getID() {
        return this.sensorID;
    }

    @Override
    public long getSensorID() {
        return sensorID;
    }

    @Override
    public String getSensorName() {
        return sensorName;
    }

    @Override
    public void setSensorID(long sensorID) {
        this.sensorID = sensorID;
    }

    @Override
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
