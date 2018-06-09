package model;

import java.io.Serializable;

/**
 * Class that defines the way a Material can be measured.
 * Assumes that if we are using the imperial standard,
 * measurements will be in inches (length) or ounces (weight),
 * and if we are using the metric standard,
 * measurements will be in cm (length) or grams (weight). 
 * Default standard is imperial.
 * 
 * @author Michelle
 */
public class Measurement implements Serializable {
    
    /**
     * generated serial id.
     */
    private static final long serialVersionUID = 5002827379414578857L;
    
    private boolean imperial; //defaults to true

    private MeasurementType measurementType;

    private double myWidth;

    private double myHeight;

    private double myDepth;

    private double myWeight;

    /**
     * Constructor that allows you to specify measurement type
     * 
     * @param measurementType
     * @param width
     * @param height
     * @param depth
     * @param weight
     * 
     * @author Michelle
     */
    public Measurement(MeasurementType measurementType, double width,
                        double height, double depth, double weight) {
        setMeasurements(measurementType, width, height, depth, weight);
        imperial = true;
    }

    /**
     * Default constructor.
     * 
     * @param theWidth
     * @param theHeight
     * @param theDepth
     * @param theWeight
     * 
     * @author Michelle
     */
    public Measurement(double theWidth, double theHeight, double theDepth,
                       double theWeight) {
        myWidth = theWidth;
        myHeight = theHeight;
        myDepth = theDepth;
        myWeight = theWeight;
        imperial = true;
    }
    
    /**
     * Will set the measurement values based on the type of measurement being used.
     * This is to make sure that no values get set that shouldn't be set.
     * 
     * @param measurementType
     * @param width
     * @param height
     * @param depth
     * @param weight
     * 
     * @author Michelle
     */
    public void setMeasurements(MeasurementType measurementType, double width,
                                 double height, double depth, double weight) {
        if (measurementType == MeasurementType.weight) {
            width = 0;
            height = 0;
            depth = 0;
            myWeight = weight;
        } else {
            myWidth = width;
            myHeight = height;
            if (measurementType == MeasurementType.w_h_d) {
                myDepth = depth;
            } else
                myDepth = 0;
        }
    }

    /**
     * Will convert the measurement values to a different standard.
     * If the standard being used is imperial, it will be converted to metric
     * and visa versa.
     * 
     * @author Michelle
     */
    public void convertStandards() {
        if (measurementType == MeasurementType.weight) {
            myWeight = convert(myWeight, false);
        } else {
            myWidth = convert(myWidth, true);
            myHeight = convert(myHeight, true);;
            if (measurementType == MeasurementType.w_h_d) {
                myDepth = convert(myDepth, true);
            }
        }
    }
    
    /**
     * Will help change the standard being used and return
     * the new value of the unit of measurement.
     * 
     * @param num
     * @param typeLength
     * @return the converted number
     * 
     * @author Michelle
     */
    public double convert(double num, boolean typeLength) { //1 = length, 0 = weight
        double toMetric;
        double toImperial;
        if (typeLength) {
            toMetric = 2.54; //converts from inches to cm
        } else {
            toMetric = 28.3495; //converts from ounces to grams
        }
        toImperial = 1/toMetric;
        if (imperial) {
            imperial = false;
            return num*toMetric;
        } else {
            imperial = true;
            return num*toImperial;
        }
    }
    
    /**
     * @author Michelle
     */
    public String toString() {
        return  myWidth + ", " + myHeight + ", " + myDepth + ", " + myWeight;
    }
    
}
