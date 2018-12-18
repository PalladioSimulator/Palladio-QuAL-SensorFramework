/**
 * 
 */
package de.uka.ipd.sdq.sensorframework.visualisation.tabs.sensors;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ICellModifier;

/**
 * @author admin
 * @deprecated Superseded by EDP2.
 */
public class SensorsTabCellModifier implements ICellModifier {

    private final List<String> columnNames;

    public SensorsTabCellModifier() {
        this.columnNames = Arrays.asList(SensorsPropertySection.columnNames);
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
        return (new SensorsTabLabelProvider()).getColumnText(element, columnNames.indexOf(property));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String,
     * java.lang.Object)
     */
    @Override
    public void modify(Object element, String property, Object value) {
        // The implementation is not necessary.
    }

}
