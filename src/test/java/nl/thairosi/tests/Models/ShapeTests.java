package nl.thairosi.tests.Models;

import nl.thairosi.sat.Models.Cone;
import nl.thairosi.sat.Models.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class contains the JUnit test methods for the shape super class
 */
public class ShapeTests {
    private final Cone coneObjectSample;

    /**
     * The constructor instantiates a new sample shape for testing
     */
    public ShapeTests() {
        this.coneObjectSample = new Cone("Sample", ShapeType.CONE, 550, 150);
    }

    /**
     * This test asserts that a new created Cone Shape object has the correct CONE ShapeType value
     */
    @Test
    void testConeGetShapeTypeTest() {
        assertEquals(ShapeType.CONE, coneObjectSample.getShapeType());
    }
}
