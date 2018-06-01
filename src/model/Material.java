package model;

import java.io.Serializable;

/**
 * Class that holds information about a material a user may choose to add
 * to their project from the shop.
 * 
 * @author David
 * @author Michelle
 */
public class Material implements Serializable{

    /**
     * generated serial id.
     */
    private static final long serialVersionUID = -8314551894492343040L;

    public String myName;

    public Double myPrice;

    public model.Measurement myMeasurement;

    public int myAmount;

    public Material(String theName, double thePrice, model.Measurement measurement) {
        this(theName, thePrice, measurement, 1);
    }
    
    public Material(String theName, double thePrice, model.Measurement theMeasurement, int theAmount) {
        myName = theName;
        myPrice = thePrice;
        myMeasurement = theMeasurement;
        myAmount = theAmount;
    }

    public String toString() {
        return  myName + "\nCost = $" + myPrice + "\nMeasurment = " + myMeasurement.toString() + "\n\n";
    }

    public double totalCost() {
        return myPrice * myAmount;
    }
    
    public String getName() {
        return myName;
    }
    
    
    
    /**
     * Inner class that defines the way a Material can be measured.
     * Assumes that if we are using the imperial standard,
     * measurements will be in inches (length) or ounces (weight),
     * and if we are using the metric standard,
     * measurements will be in cm (length) or grams (weight). 
     * Default standard is imperial.
     * 
     * @author Michelle
     */
    class Measurement {

        boolean imperial; //defaults to true

        MeasurementType measurementType;

        double width;

        double height;

        double depth;

        double weight;

        /**
         * Constructor.
         * 
         * @param measurementType
         * @param width
         * @param height
         * @param depth
         * @param weight
         */
        private Measurement(MeasurementType measurementType, double width,
                            double height, double depth, double weight) {
            setMeasurements(measurementType, width, height, depth, weight);
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
         */
        public void setMeasurements(MeasurementType measurementType, double width,
                                     double height, double depth, double weight) {
            if (measurementType == MeasurementType.weight) {
                width = 0;
                height = 0;
                depth = 0;
                this.weight = weight;
            } else {
                this.width = width;
                this.height = height;
                if (measurementType == MeasurementType.w_h_d) {
                    this.depth = depth;
                } else
                    this.depth = 0;
            }
        }

        /**
         * Will convert the measurement values to a different standard.
         * If the standard being used is imperial, it will be converted to metric
         * and visa versa.
         */
        private void convertStandards() {
            if (measurementType == MeasurementType.weight) {
                weight = convert(weight, false);
            } else {
                width = convert(width, true);
                height = convert(height, true);;
                if (measurementType == MeasurementType.w_h_d) {
                    depth = convert(depth, true);
                }
            }
        }
        
        /**
         * Will help change the standard being used and return
         * the new value of the unit of measurement.
         * 
         * @param num
         * @param typeLength
         * @return
         */
        private double convert(double num, boolean typeLength) { //1 = length, 0 = weight
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
        
    }

}
