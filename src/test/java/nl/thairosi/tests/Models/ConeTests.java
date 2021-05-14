package nl.thairosi.tests.Models;

import nl.thairosi.sat.Models.Cone;
import nl.thairosi.sat.Models.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class contains the JUnit test methods for this shapes sub class
 */
public class ConeTests {
    private final Cone coneObjectNegative;
    private final Cone coneObjectZero;
    private final Cone coneObjectSample;

    /**
     * The constructor instantiates new shape samples for testing
     */
    public ConeTests() {
        this.coneObjectNegative = new Cone("Negative", ShapeType.CONE, -1.5, -1.1);
        this.coneObjectZero = new Cone("Zero", ShapeType.CONE, 0, 0);
        this.coneObjectSample = new Cone("Sample", ShapeType.CONE, 550, 150);
    }

    /**
     * This test asserts that when at least one dimension in the shape is 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsNegative() {
        assertEquals(0, coneObjectNegative.calcVolume());
    }

    /**
     * This test asserts that when at least one dimension in the shape is below 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsZero() {
        assertEquals(0, coneObjectZero.calcVolume());
    }

    /**
     * This test tests the calculate volume method of the shape by asserting that the rounded calculated volume is as expected
     */
    @Test
    void testCalculatedVolumeSample() {
        assertEquals(3239.77, coneObjectSample.calcVolume());
    }
}
