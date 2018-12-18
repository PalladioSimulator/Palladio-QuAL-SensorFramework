package de.uka.ipd.sdq.sensorframework.entities.impl;

import java.util.ArrayList;

import de.uka.ipd.sdq.sensorframework.entities.Measurement;
import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.entities.SensorAndMeasurements;
import de.uka.ipd.sdq.sensorframework.entities.StateMeasurement;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanMeasurement;
import de.uka.ipd.sdq.sensorframework.entities.dao.IDAOFactory;

/**
 * @deprecated Superseded by EDP2.
 */
public class ExperimentRunImpl extends de.uka.ipd.sdq.sensorframework.entities.base.AbstractExperimentRun {

    public ExperimentRunImpl(final IDAOFactory myFactory) {
        super(myFactory);
    }

    @Override
    public SensorAndMeasurements getMeasurementsOfSensor(final Sensor sensor) {
        // TODO: Fix this conceptually!!!
        // EntityManager em =
        // ExperimentDAO.singleton().getEntityManagerFactory().createEntityManager();
        // Query q =
        // em.createQuery("SELECT m FROM Measurement m WHERE FK_SENSOR = "+sensor.getSensorID());
        // Collection <Measurement> result = q.getResultList();
        // em.close();
        final ArrayList<Measurement> result = new ArrayList<Measurement>();
        for (final Measurement m : this.getMeasurements()) {
            if (m instanceof TimeSpanMeasurement) {
                final TimeSpanMeasurement tsm = (TimeSpanMeasurement) m;
                if (tsm.getSensor() == sensor) {
                    result.add(m);
                }
            }
            if (m instanceof StateMeasurement) {
                final StateMeasurement tsm = (StateMeasurement) m;
                if (tsm.getSensor() == sensor) {
                    result.add(m);
                }
            }
        }
        return new SensorAndMeasurements(sensor, result);
    }
}
