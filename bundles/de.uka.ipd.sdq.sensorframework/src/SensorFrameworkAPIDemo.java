import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import de.uka.ipd.sdq.sensorframework.SensorFrameworkDataset;
import de.uka.ipd.sdq.sensorframework.dao.memory.MemoryDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.State;
import de.uka.ipd.sdq.sensorframework.entities.StateSensor;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * This class demonstrates the use of the API of the Sensorframework Plugin. It creates an
 * experiment, some sensors, and finally adds measurements to it. For additional examples, see the
 * JUnit tests of the sensor framework in their own plugin.
 * 
 * @author Steffen Becker
 * @deprecated Superseded by EDP2.
 */
public class SensorFrameworkAPIDemo {

    private static final Logger LOGGER = Logger.getLogger(SensorFrameworkAPIDemo.class);

    /**
     * TODO should be called as a test by the test suite, too! E.g. with try/catch, where the catch
     * clause sets a variable to false befor Assert.assertEquals on booleans is called.
     * 
     * @param args
     */
    public static void main(String[] args) {
        SensorFrameworkDataset.singleton().addDataSource(new MemoryDAOFactory(IDAOFactory.ID_NOT_SET));
        IDAOFactory f = SensorFrameworkDataset.singleton().getDataSourceByID(1);
        Experiment e = f.createExperimentDAO().addExperiment("Test");
        ExperimentRun er = e.addExperimentRun("TestRun " + new Date().toString());
        TimeSpanSensor s = e.addTimeSpanSensor("ATimeSpanSensor");
        State busyState = f.createStateDAO().addState("Busy");
        State idleState = f.createStateDAO().addState("Idle");
        StateSensor stateSen = e.addStateSensor(idleState, "AStateSensor");
        stateSen.addSensorState(busyState);
        stateSen.addSensorState(idleState);
        boolean flag = false;
        double valueSum = 0;
        long start = System.nanoTime();
        for (int i = 0; i < 200000; i++) {
            if (i % 1000 == 0) {
                System.out.print(".");// progress bar simulation... not to be replaced by proper
                                      // logging...
            }
            if (i % 100000 == 0) {
                System.out.print("\n");
            }
            double value = Math.random() * 5000;
            er.addTimeSpanMeasurement(s, value, value);// 2nd param: event time, 3rd param: timespan
            er.addStateMeasurement(stateSen, flag ? busyState : idleState, valueSum);// the last
                                                                                     // parameter is
                                                                                     // used as
                                                                                     // event time
                                                                                     // :-(
            flag = !flag;
            valueSum += value;
        }

        if (LOGGER.isEnabledFor(Level.INFO)) {
            LOGGER.info("\nDone creating measurements: " + (System.nanoTime() - start) / Math.pow(10, 9) + " s");
        }
        f.finalizeAndClose();
        if (LOGGER.isEnabledFor(Level.INFO)) {
            LOGGER.info("Done storing: " + (System.nanoTime() - start) / Math.pow(10, 9) + " s");
            LOGGER.info(er.getMeasurementsOfSensor(s).getMeasurements().size());
            LOGGER.info("Done " + (System.nanoTime() - start) / Math.pow(10, 9) + " s");
        }
    }

}
