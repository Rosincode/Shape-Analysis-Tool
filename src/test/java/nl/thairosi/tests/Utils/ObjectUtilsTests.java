package nl.thairosi.tests.Utils;

import nl.thairosi.sat.Models.Cone;
import nl.thairosi.sat.Models.Shape;
import nl.thairosi.sat.Models.ShapeType;
import nl.thairosi.sat.Utils.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class tests the methods of the Object Utils class
 */
public class ObjectUtilsTests {

    /**
     * This test asserts that the expected field names and its values are returned with the getFieldNamesAndValues method
     */
    @Test
    void testObjectFieldNamesAndValuesAreCorrect() {
        Shape coneSample = new Cone("coneSample", ShapeType.CONE, 550, 150);
        Map<String, Object> sampleMap = ObjectUtils.getFieldNamesAndValues(coneSample);
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("diameter", 150.0);
        testMap.put("height", 550.0);
        assertEquals(testMap, sampleMap);
    }
}