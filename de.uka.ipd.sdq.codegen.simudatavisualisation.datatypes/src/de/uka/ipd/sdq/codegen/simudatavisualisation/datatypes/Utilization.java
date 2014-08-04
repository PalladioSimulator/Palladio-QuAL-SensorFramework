package de.uka.ipd.sdq.codegen.simudatavisualisation.datatypes;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores the information necessary to display a Utilization. The Utilization can be
 * visualized using the information about the bucket width and the Utilization for the buckets.
 * 
 * @author khameershaik
 */
public class Utilization {
    /** The default width for Utilization classes */
    public static final double DEFAULT_BUCKET_WIDTH = 1.0;
    /** The default width for Utilization classes */
    public static final double DEFAULT_NUMBER_BUCKETS = 2;

    /**
     * The maximum number of buckets to show when initialising the diagram. The user can decide to
     * show more.
     */
    public static final double MAXIMUM_NUMBER_OF_BUCKETS = 100;

    /**
     * List of the entities, that describe the Utilization for each of the buckets
     * 
     * @see UtilizationBucketInformation
     */
    protected List<UtilizationBucketInformation> bucketInformation;

    /** Contains the width of each bucket of the Utilization. */
    protected double bucketWidth;

    /** title/name of the Utilization */
    private String title;

    /**
     * Creates a new Utilization without buckets and default bucket width.
     * 
     * @param title
     *            Title od name of the Utilization.
     */
    public Utilization(String title) {
        this(title, DEFAULT_BUCKET_WIDTH);
    }

    /**
     * Creates a new Utilization without buckets.
     * 
     * @param title
     *            Title or name of the Utilization.
     * @param bucketWidth
     *            Width of a bucket of the Utilization.
     */
    public Utilization(String title, double bucketWidth) {
        this.bucketInformation = new ArrayList<UtilizationBucketInformation>();
        this.title = title;
        this.bucketWidth = bucketWidth;
    }

    /**
     * Adds a new bucket to the Utilization.
     * 
     * @param entity
     *            information about the bucket.
     */
    public void addEntity(UtilizationBucketInformation entity) {
        bucketInformation.add(entity);
    }

    /**
     * Removes a bucket from the Utilization.
     * 
     * @param entity
     *            bucket to remove.
     */
    public void removeEntity(UtilizationBucketInformation entity) {
        bucketInformation.remove(entity);
    }

    /**
     * Returns the list of the buckets and their respective Utilization.
     * 
     * @return the list of the entities.
     */
    public List<UtilizationBucketInformation> getBucketInformation() {
        return bucketInformation;
    }

    /**
     * Receives the title or name of the Utilization.
     * 
     * @return Title or name of the Utilization.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title or name of the Utilization.
     * 
     * @param title
     *            The new title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Receives the bucket width of the Utilization.
     * 
     * @return the bucket width.
     */
    public double getBucketWidth() {
        return bucketWidth;
    }

    /**
     * Sets the bucket width of the Utilization.
     * 
     * @param bucketWidth
     *            the new bucket width.
     */
    public void setBucketWidth(double bucketWidth) {
        this.bucketWidth = bucketWidth;
    }

}
