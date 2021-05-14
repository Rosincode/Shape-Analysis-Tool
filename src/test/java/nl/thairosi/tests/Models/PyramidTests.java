package nl.thairosi.tests.Models;

import nl.thairosi.sat.Models.Pyramid;
import nl.thairosi.sat.Models.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class contains the JUnit test methods for this shapes sub class
 */
public class PyramidTests {
    private final Pyramid pyramidObjectNegative;
    private final Pyramid pyramidObjectZero;
    private final Pyramid pyramidObjectGiza;

    /**
     * The constructor instantiates new shape samples for testing
     */
    public PyramidTests() {
        this.pyramidObjectNegative = new Pyramid("Negative", ShapeType.PYRAMID, -11, -11);
        this.pyramidObjectZero = new Pyramid("Zero", ShapeType.PYRAMID, 0, 0);
        this.pyramidObjectGiza = new Pyramid("Giza", ShapeType.PYRAMID, 146.70, 230.34);
    }

    /**
     * This test asserts that when at least one dimension in the shape is 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsNegative() {
        assertEquals(0, pyramidObjectNegative.calcVolume());
    }

    /**
     * This test asserts that when at least one dimension in the shape is below 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsZero() {
        assertEquals(0, pyramidObjectZero.calcVolume());
    }

    /**
     * This test tests the calculate volume method of the shape by asserting that the rounded calculated volume is as expected
     */
    @Test
    void testCalculatedVolumeOfTheGizaPyramid() {
        assertEquals(2594.46, pyramidObjectGiza.calcVolume());
    }

}
