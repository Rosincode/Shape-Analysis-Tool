package nl.thairosi.tests.Utils;

import nl.thairosi.sat.Models.*;
import nl.thairosi.sat.Utils.ShapeIO;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class tests the methods of the ShapeIO Utils class
 */
public class ShapeIOTests {
    private final ArrayList<Shape> demoShapes;
    private final ShapeIO shapeIO;

    /**
     * The constructor instantiates a new ShapeIO class and a new Shape ArrayList
     */
    public ShapeIOTests() {
        this.demoShapes = new ArrayList<>();
        this.shapeIO = new ShapeIO();
    }

    /**
     * This test asserts that the given Shape ArrayList will be equal to the ArrayList read from the shapeImportSample.txt file
     *
     * @throws IOException            if importing the text file fails
     * @throws ClassNotFoundException if the class read from the text file was not found
     */
    @Test
    void testIfTheImportedTextFileEqualsTheDemoShapesArrayList() throws IOException, ClassNotFoundException {
        this.demoShapes.add(new Cube(42, "cubeSample", ShapeType.CUBE, 150));
        this.demoShapes.add(new Cylinder(10, "cylinderSample", ShapeType.CYLINDER, 550, 150));
        this.demoShapes.add(new Sphere(11, "sphereSample", ShapeType.SPHERE, 150));
        this.demoShapes.add(new Pyramid(11, "pyramidSample", ShapeType.PYRAMID, 550, 150));
        this.demoShapes.add(new Cone(9, "coneSample", ShapeType.CONE, 550, 150));
        ArrayList<Shape> importedShapes = this.shapeIO.readShapesFromFile(new File("/Users/kevin/Downloads/shapeImportSample.txt"));
        assertEquals(this.demoShapes, importedShapes);
    }

    /**
     * This test asserts that the given Shape ArrayList will be equal to the ArrayList read from the shapeImportSample.obj file
     *
     * @throws IOException            if importing the object file fails
     * @throws ClassNotFoundException if the class read from the object file was not found
     */
    @Test
    void testIfTheImportedObjectFileEqualsTheDemoShapesArrayList() throws IOException, ClassNotFoundException {
        this.demoShapes.add(new Cube(42, "cubeSample", ShapeType.CUBE, 150));
        this.demoShapes.add(new Cylinder(10, "cylinderSample", ShapeType.CYLINDER, 550, 150));
        this.demoShapes.add(new Sphere(11, "sphereSample", ShapeType.SPHERE, 150));
        this.demoShapes.add(new Pyramid(11, "pyramidSample", ShapeType.PYRAMID, 550, 150));
        this.demoShapes.add(new Cone(9, "coneSample", ShapeType.CONE, 550, 150));
        ArrayList<Shape> importedShapes = this.shapeIO.readShapesFromFile(new File("/Users/kevin/Downloads/shapeImportSample.obj"));
        assertEquals(this.demoShapes, importedShapes);
    }
}
