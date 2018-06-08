package model;

/**
 * Specifies how this Material is measured.
 * Assumes Material is being measured either by inches or lbs.
 * Could later be implemented to have different units of length or weight.
 * 
 * @author Michelle Brown
 * 
 * @version May 25, 2018
 */
public enum MeasurementType {
    w_h, w_h_d, weight;
}
