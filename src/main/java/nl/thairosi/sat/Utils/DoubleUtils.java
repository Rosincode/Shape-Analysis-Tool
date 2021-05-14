package nl.thairosi.sat.Utils;

/**
 * The DoubleUtils class will be instantiated to change double values in a desired double value
 */
public class DoubleUtils {

    /**
     * This round method rounds up a double value by two decimals
     *
     * @param value is the to be converted double value parameter
     * @return a rounded by two decimals double value
     */
    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
