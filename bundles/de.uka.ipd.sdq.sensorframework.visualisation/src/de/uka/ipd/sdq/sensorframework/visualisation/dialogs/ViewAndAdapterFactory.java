package de.uka.ipd.sdq.sensorframework.visualisation.dialogs;

import org.eclipse.core.runtime.IConfigurationElement;

import de.uka.ipd.sdq.sensorframework.adapter.IAdapterFactory;

/**
 * @deprecated Superseded by EDP2.
 */
public class ViewAndAdapterFactory {

    private final IConfigurationElement view;
    private final IAdapterFactory factory;

    public IConfigurationElement getView() {
        return view;
    }

    public IAdapterFactory getFactory() {
        return factory;
    }

    public ViewAndAdapterFactory(IConfigurationElement view, IAdapterFactory factory) {
        super();
        this.view = view;
        this.factory = factory;
    }

}
