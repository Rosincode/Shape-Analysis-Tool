package nl.thairosi.tests.Models;

import nl.thairosi.sat.Models.ShapeType;
import nl.thairosi.sat.Models.Sphere;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class contains the JUnit test methods for this shapes sub class
 */
public class SphereTests {
    private final Sphere sphereObjectNegative;
    private final Sphere sphereObjectZero;
    private final Sphere sphereObjectFootball;

    /**
     * The constructor instantiates new shape samples for testing
     */
    public SphereTests() {
        this.sphereObjectNegative = new Sphere("Negative", ShapeType.SPHERE, -12);
        this.sphereObjectZero = new Sphere("Zero", ShapeType.SPHERE, 0);
        this.sphereObjectFootball = new Sphere("Football", ShapeType.SPHERE, 22);
    }

    /**
     * This test asserts that when at least one dimension in the shape is 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsNegative() {
        assertEquals(0, sphereObjectNegative.calcVolume());
    }

    /**
     * This test asserts that when at least one dimension in the shape is below 0 the volume will be 0
     */
    @Test
    void testIsCalculatedVolumeZeroWhenEdgeIsZero() {
        assertEquals(0, sphereObjectZero.calcVolume());
    }

    /**
     * This test tests the calculate volume method of the shape by asserting that the rounded calculated volume is as expected
     */
    @Test
    void testCalculatedVolumeOfAFootball() {
        assertEquals(5.58, sphereObjectFootball.calcVolume());
    }
}
