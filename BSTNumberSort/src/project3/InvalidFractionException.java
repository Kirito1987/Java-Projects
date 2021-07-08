package project3;
/*Filename: InvalidFractionException.java
    Created by: Laudro Pineda
    Purpose: This class will handle the exception InvalidFraction, which is thrown inside the
        Fraction.java class when an incorrect fraction is used.
 */

public class InvalidFractionException extends Exception {
    InvalidFractionException(String token) {
        super(token);
    }
}
