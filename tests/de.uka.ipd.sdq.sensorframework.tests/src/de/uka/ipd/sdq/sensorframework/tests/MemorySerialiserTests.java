package de.uka.ipd.sdq.sensorframework.tests;

import java.io.IOException;

import de.uka.ipd.sdq.sensorframework.dao.memory.MemoryDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
public class MemorySerialiserTests extends AbstractSerialiserTests {

    private MemoryDAOFactory lastDAO;

    public MemorySerialiserTests() {
    }

    @Override
    protected IDAOFactory createCleanDAOFactory() throws IOException {
        this.lastDAO = new MemoryDAOFactory(-1);
        return lastDAO;
    }

    @Override
    protected IDAOFactory createDAOFactory() {
        return lastDAO;
    }

}
