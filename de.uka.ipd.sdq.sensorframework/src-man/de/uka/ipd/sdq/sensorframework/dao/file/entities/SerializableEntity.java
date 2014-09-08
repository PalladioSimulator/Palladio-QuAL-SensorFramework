/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.dao.file.entities;

import de.uka.ipd.sdq.sensorframework.dao.file.FileDAOFactory;

/**
 * TODO names of interfaces should start with "I"
 * 
 * @author Ihssane El-Oudghiri
 * @deprecated Superseded by EDP2.
 */
public interface SerializableEntity extends java.io.Serializable {
    public long getID();

    public void setFactory(FileDAOFactory factory);
}
