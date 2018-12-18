/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.visualisation.dialogs;

import org.eclipse.jface.viewers.ICellModifier;

import de.uka.ipd.sdq.sensorframework.entities.Sensor;
import de.uka.ipd.sdq.sensorframework.visualisation.editor.ConfigEntry;

/**
 * @author admin
 * @deprecated Superseded by EDP2.
 */
public class SensorsDialogCellModifier implements ICellModifier {

    private final ConfigEntry entry;
    private Sensor selectedSensor;

    public SensorsDialogCellModifier(ConfigEntry entry) {
        this.entry = entry;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean canModify(Object element, String property) {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
     */
    @Override
    public Object getValue(Object element, String property) {
        selectedSensor = (Sensor) element;
        return new Boolean(entry.isSensorChecked(selectedSensor));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String,
     * java.lang.Object)
     */
    @Override
    public void modify(Object element, String property, Object value) {
        boolean checked = (((Boolean) value).booleanValue());

        if (checked) {
            entry.setSensorChecked(selectedSensor);
        }
        if (!checked) {
            entry.setSensorUnchecked(selectedSensor);
        }
    }

}
