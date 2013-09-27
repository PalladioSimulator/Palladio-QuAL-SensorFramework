/**
 * 
 */
package de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes;

/**This class contains the information about one bucket of a Utilization.
 * It stores the value at which a bucket starts and the Utilization for
 * that bucket.
 * @author admin
 * @author khameershaik
 */
public class UtilizationBucketInformation {
	
	/** The Utilization for the bucket */
	private double utilization;
	
	/** The value at which the bucket starts */
	private double value;

	/**Creates a new bucket information.
	 * @param utilization The Utilization of the bucket.
	 * @param value The value at which the bucket starts.
	 */
	public UtilizationBucketInformation(double utilization, double value) {
		this.utilization = utilization;
		this.value = value;
	}
	
	/**Receives the Utilization for the bucket.
	 * @return the Utilization
	 */
	public double getUtilization() {
		return utilization;
	}
	/**Sets the probability for the bucket.
	 * @param probability the new utilization for the bucket.
	 */
	public void setUtilization(double utilization) {
		this.utilization = utilization;
	}
	/**Receives the value at which the bucket starts.
	 * @return the value at which the bucket starts.
	 */
	public double getValue() {
		return value;
	}
	/**Sets the value at which the bucket starts.
	 * @param value the new start value
	 */
	public void setValue(double value) {
		this.value = value;
	}
}
