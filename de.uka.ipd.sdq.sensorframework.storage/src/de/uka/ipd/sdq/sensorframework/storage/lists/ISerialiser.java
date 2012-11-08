package de.uka.ipd.sdq.sensorframework.storage.lists;

/**
 * Interface used by the BackgroundMemoryList to serialise the elements of the list into bytestreams 
 * and vice versa. The elements serialised form has to have a constant memory footprint. Otherwise using the 
 * BackgroundMemoryList will not work as it relies on the constant sizes to seek in the background storage and
 * calculate the number of elements. 
 * @author Steffen Becker
 */
public interface ISerialiser<T> {

	/** 
	 * @return The size of each element in serialised form, e.g., for doubles it could be 8 bytes (IEEE format)
	 */
	long getElementLength();
	
	/** Convert the given array of elements into their serialised form
	 * @param objects The array of elements to serialise
	 * @param count The number of elements to serialise
	 * @return The elements in their serialised form
	 */
	byte[] serialise(Object[] objects,int count);
	
	
	/** Convert a serialised form into an array of elements of the serialiser's type
	 * @param bytes The byte array to deserialise
	 * @return An array of elements deserialised from the given byte array
	 */
	T[] deserialise(byte[] bytes);	

}
