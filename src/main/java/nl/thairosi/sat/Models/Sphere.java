package nl.thairosi.sat.Models;

import nl.thairosi.sat.Utils.DoubleUtils;

/**
 * This is a sub class of the Shape super class witch adds the necessary dimension to a instantiated shape object
 * This class also contains the right implementation of the super classes abstract classes calcVolume and getDimensions
 */
public class Sphere extends nl.thairosi.sat.Models.Shape {
    private final double diameter;

    /**
     * This constructor uses the name and shapeType from the Shape super class
     * Also the constructor adds the dimension when instantiating the object
     * @param name is the name that is given by the user
     * @param shapeType is the shapeType that corresponds to this particular shape
     * @param diameter is a dimension that is necessary for calculating the volume of this shape
     */
    public Sphere(String name, ShapeType shapeType, double diameter) {
        super(name, shapeType);
        this.diameter = DoubleUtils.round(diameter);
    }

    /**
     * This overloaded constructor uses the id, the name and shapeType from the Shape super class
     * Also the constructor adds the dimension when instantiating the object
     * @param id is the (auto incremented) ID that has been created by the database
     * @param name is the name that is given by the user
     * @param shapeType is the shapeType that corresponds to this particular shape
     * @param diameter is a dimension that is necessary for calculating the volume of this shape
     */
    public Sphere(int id, String name, ShapeType shapeType, double diameter) {
        super(id, name, shapeType);
        this.diameter = DoubleUtils.round(diameter);
    }

    /**
     * Dimension getter
     * @return the diameter of this Shape object
     */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Calculates the shapes volume conform the given dimension
     * @return the calculated volume
     */
    public double calcVolume() {
        if (this.diameter > 0) {
            double radius = this.diameter / 2;
            return DoubleUtils.round(((4.0 / 3.0) * Math.PI * Math.pow(radius, 3)) / 1000);
        } else {
            return 0;
        }
    }

    /**
     * Creates a representation of the dimension of this Shape object
     * @return the dimension in order to show them the right way in the GUI
     */
    public String getDimensions() {
        return "Diameter: " + this.getDiameter() + " cm";
    }

    /**
     * Overrides the java toString method in order to export text files
     * @return all values split by comma's
     */
    @Override
    public String toString() {
        return  getId() + "," +
                getName() + "," +
                getShapeType() + "," +
                getDiameter();
    }
}
