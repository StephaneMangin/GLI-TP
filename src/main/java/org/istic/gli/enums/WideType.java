package org.istic.gli.enums;

/**
 * Created by smangin on 08/10/15.
 */
public enum WideType {
    Degree(360),
    Gradiant(6.2832);

    private final double value;

    WideType(double value) {
        this.value = value;
    }
    public double getValue() {
           return this.value;
     }
}
