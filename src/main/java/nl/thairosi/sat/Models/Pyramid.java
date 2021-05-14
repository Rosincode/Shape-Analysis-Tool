package nl.thairosi.sat.Models;

import nl.thairosi.sat.Utils.DoubleUtils;

/**
 * This is a sub class of the Shape super class witch adds the necessary dimensions to a instantiated shape object
 * This class also contains the right implementation of the super classes abstract classes calcVolume and getDimensions
 */
public class Pyramid extends nl.thairosi.sat.Models.Shape {
    private final double height;
    private final double edge;

    /**
     * This constructor uses the name and shapeType from the Shape super class
     * Also the constructor adds the dimensions when instantiating the object
     *
     * @param name      is the name that is given by the user
     * @param shapeType is the shapeType that corresponds to this particular shape
     * @param height    is a dimension that is necessary for calculating the volume of this shape
     * @param edge      is a dimension that is necessary for calculating the volume of this shape
     */
    public Pyramid(String name, ShapeType shapeType, double height, double edge) {
        super(name, shapeType);
        this.height = DoubleUtils.round(height);
        this.edge = DoubleUtils.round(edge);
    }

    /**
     * This overloaded constructor uses the id, the name and shapeType from the Shape super class
     * Also the constructor adds the dimensions when instantiating the object
     *
     * @param id        is the (auto incremented) ID that has been created by the database
     * @param name      is the name that is given by the user
     * @param shapeType is the shapeType that corresponds to this particular shape
     * @param height    is a dimension that is necessary for calculating the volume of this shape
     * @param edge      is a dimension that is necessary for calculating the volume of this shape
     */
    public Pyramid(int id, String name, ShapeType shapeType, double height, double edge) {
        super(id, name, shapeType);
        this.height = DoubleUtils.round(height);
        this.edge = DoubleUtils.round(edge);
    }

    /**
     * Dimension getter
     *
     * @return the height of this Shape object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Dimension getter
     *
     * @return the edge of this Shape object
     */
    public double getEdge() {
        return edge;
    }

    /**
     * Calculates the shapes volume conform the given dimensions
     *
     * @return the calculated volume
     */
    public double calcVolume() {
        if (this.edge > 0 && this.height > 0) {
            return DoubleUtils.round(((1.0 / 3.0) * Math.pow(this.edge, 2) * this.height) / 1000);
        } else {
            return 0;
        }
    }

    /**
     * Creates a representation of the dimensions of this Shape object
     *
     * @return the dimensions in order to show them the right way in the GUI
     */
    public String getDimensions() {
        return "Height: " + this.getHeight() + " cm / " +
                "Edge: " + this.getEdge() + " cm";
    }

    /**
     * Overrides the java toString method in order to export text files
     *
     * @return all values split by comma's
     */
    @Override
    public String toString() {
        return getId() + "," +
                getName() + "," +
                getShapeType() + "," +
                getHeight() + "," +
                getEdge();
    }
}
