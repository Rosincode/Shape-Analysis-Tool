package nl.thairosi.tests.Models;

import nl.thairosi.sat.Models.Cube;
import nl.thairosi.sat.Models.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class contains the JUnit test methods for this particular shape
 */
public class CubeTests {
    private final Cube cubeObjectNegative;
    private final Cube cubeObjectZero;
    private final Cube cubeObjectTwelve;

    /**
     * The constructor instantiates new shape samples for testing
     */
    public CubeTests() {
        this.cubeObjectNegative = new Cube("Negative", ShapeType.CUBE, -12);
        this.cubeObjectZero = new Cube("Zero", ShapeType.CUBE, 0);
        this.cubeObjectTwelve = new Cube("Twelve", ShapeType.CUBE, 12);
    }

    /**
     * This test asserts that when at least one dimension in the shape is 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsNegative() {
        assertEquals(0, cubeObjectNegative.calcVolume());
    }

    /**
     * This test asserts that when at least one dimension in the shape is below 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsZero() {
        assertEquals(0, cubeObjectZero.calcVolume());
    }

    /**
     * This test tests the calculate volume method of the shape by asserting that the rounded calculated volume is as expected
     */
    @Test
    void testCalculatedVolumeWhenEdgeIsTwelve() {
        assertEquals(1.73, cubeObjectTwelve.calcVolume());
    }
}
