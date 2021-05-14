package nl.thairosi.tests.Models;

import nl.thairosi.sat.Models.Cylinder;
import nl.thairosi.sat.Models.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class contains the JUnit test methods for this shapes sub class
 */
public class CylinderTests {
    private final Cylinder cylinderObjectNegative;
    private final Cylinder cylinderObjectZero;
    private final Cylinder cylinderObjectSample;

    /**
     * The constructor instantiates new shape samples for testing
     */
    public CylinderTests() {
        this.cylinderObjectNegative = new Cylinder("Negative", ShapeType.CYLINDER, -12, -30);
        this.cylinderObjectZero = new Cylinder("Zero", ShapeType.CYLINDER, 0, 0);
        this.cylinderObjectSample = new Cylinder("Sample", ShapeType.CYLINDER, 12, 30);
    }

    /**
     * This test asserts that when at least one dimension in the shape is 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsNegative() {
        assertEquals(0, cylinderObjectNegative.calcVolume());
    }

    /**
     * This test asserts that when at least one dimension in the shape is below 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsZero() {
        assertEquals(0, cylinderObjectZero.calcVolume());
    }

    /**
     * This test tests the calculate volume method of the shape by asserting that the rounded calculated volume is as expected
     */
    @Test
    void testCalculatedVolumeSample() {
        assertEquals(8.48, cylinderObjectSample.calcVolume());
    }
}
