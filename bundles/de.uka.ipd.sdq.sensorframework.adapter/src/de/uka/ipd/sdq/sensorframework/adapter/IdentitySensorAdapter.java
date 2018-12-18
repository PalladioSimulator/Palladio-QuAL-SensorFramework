package de.uka.ipd.sdq.sensorframework.adapter;

/**
 * Provides an adapter that allows mapping the identity.
 * 
 * @author Henning Groenda
 * @deprecated Superseded by EDP2.
 */
public class IdentitySensorAdapter extends DataAdapter {

    /** Object to adapt */
    private final Object o;

    /**
     * Initializes a new adapter which provides access to the provided object.
     * 
     * @param adaptee
     *            The object to adapt.
     */
    public IdentitySensorAdapter(final Object adaptee) {
        super();
        this.o = adaptee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAdaptedObject() {
        return o;
    }
}
