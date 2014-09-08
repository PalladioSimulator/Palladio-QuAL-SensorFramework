package de.uka.ipd.sdq.sensorframework.filter;

import java.util.Collection;
import java.util.Properties;

import de.uka.ipd.sdq.sensorframework.entities.Measurement;

/**
 * @deprecated Superseded by EDP2.
 */
public class OutlierFilteredFactory implements IFilteredCollectionFactory {

    /** The properties settings for this filtered collection */
    protected Properties filterProperties = new Properties();

    /** The property "outlier". */
    private static final String OUTLIER = "Outlier removal";
    /** Default outlier to use. */
    private static final double DEFAULT_OUTLIER = 0.1;

    /**
     * Initializes a new OutlierFilteredFactory without properties.
     */
    public OutlierFilteredFactory() {
        filterProperties.put(OUTLIER, DEFAULT_OUTLIER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractMeasurementsCollection getFilteredCollection(Collection<Measurement> filtrate) {
        return new OutlierFilteredCollection(filtrate, DEFAULT_OUTLIER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Properties getProperties() {
        return filterProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProperties(Properties newProperties) {
        filterProperties = newProperties;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canFilter(Collection<Measurement> filtrate, Number attribute) {
        return attribute.doubleValue() < 1.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractMeasurementsCollection getFilteredCollection(Collection<Measurement> filtrate, Number parameter) {
        filterProperties.put(OUTLIER, parameter.doubleValue());
        return new OutlierFilteredCollection(filtrate, parameter.doubleValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFilterFactoryID() {
        return "Outlier Filter";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double convertToType(String type) {
        return Double.parseDouble(type);
    }
}
