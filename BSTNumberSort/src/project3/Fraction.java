package project3;

/* Filename: Fraction.java
    Created by: Laudro Pineda
    Purpose: The java file is used to a string of fractions and assign the numerator, denominator, and value.
 */

import java.text.DecimalFormat;

public class Fraction implements Comparable<Fraction> {

    private double numerator;
    private double denominator;
    private double value;


    Fraction(String fraction) throws InvalidFractionException {
        String[] convert = fraction.split("/");     // converts the fraction inside the string into an array
        if (convert.length != 2) throw new InvalidFractionException(fraction); // error is thrown if fraction isn't correct
        numerator = Integer.parseInt(convert[0]);        // set the numerator of the fraction
        denominator = Integer.parseInt(convert[1]);      // sets the denominator of the fraction
        this.value = numerator / denominator;       // sets the fraction value as = numerator/denominator (ex. 1/2)
    }


    private Double getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();
        return df.format(numerator) + "/" + df.format(denominator);
    }


    @Override
    public int compareTo(Fraction frac) {
        if (this.value == frac.getValue()) {
            return 0;
        }
        return this.value > frac.value ? 1 : -1;
    }
}

