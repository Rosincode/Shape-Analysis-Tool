package nl.thairosi.tests.Utils;

import nl.thairosi.sat.Utils.DoubleUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class tests the methods of the DoubleUtils class
 */
public class DoubleUtilsTests {

    double nineDecimalsNumber = 125.123456789;
    double roundUpNumber = 125.125;
    double roundDownNumber = 125.124;

    /**
     * This test tests if a nine decimals double will be rounded to the expected value
     */
    @Test
    void testRoundDoubleValueByTwoDecimals() {
        assertEquals(125.12, DoubleUtils.round(nineDecimalsNumber));
    }

    /**
     * This test tests if the third decimal will be rounded up when it is 5 or more
     */
    @Test
    void testRoundDoubleValueUpTwoDecimals() {
        assertEquals(125.13, DoubleUtils.round(roundUpNumber));
    }

    /**
     * This test tests if the third decimal will be rounded down when it is 4 or less
     */
    @Test
    void testRoundDoubleValueDownTwoDecimals() {
        assertEquals(125.12, DoubleUtils.round(roundDownNumber));
    }
}
