package nl.thairosi.sat.Models;

import java.io.Serializable;

/**
 * The Shape model is the super class of all available Shapes in the application
 * This model is abstract in order to handle different implementations of methods within the sub classes
 * The model implements the Serializable class witch makes it possible to export and import shapes as object files
 */
public abstract class Shape implements Serializable {
    private int id;
    private final String name;
    private final ShapeType shapeType;

    /**
     * This constructor sets the name and the shapeType of a Shape object
     *
     * @param name      is the name that is given by the user in the GUI
     * @param shapeType is the automatically assigned ShapeType enum of a new created shape object
     */
    public Shape(String name, ShapeType shapeType) {
        this.name = name;
        this.shapeType = shapeType;
    }

    /**
     * This overloaded constructor sets the id, the name and the shapeType of a Shape object
     *
     * @param id        is the (automatically incremented) ID from the database
     * @param name      is the name that is received from the database
     * @param shapeType is the ShapeType enum that is received from the database
     */
    public Shape(int id, String name, ShapeType shapeType) {
        this.id = id;
        this.name = name;
        this.shapeType = shapeType;
    }

    /**
     * ID getter
     *
     * @return the id of the object
     */
    public int getId() {
        return this.id;
    }

    /**
     * Name getter
     *
     * @return the name of the object
     */
    public String getName() {
        return this.name;
    }

    /**
     * ShapeType getter
     *
     * @return the ShapeType enum of the object
     */
    public ShapeType getShapeType() {
        return this.shapeType;
    }

    /**
     * This abstract method executes the calcVolume method of the current initialized sub class
     *
     * @return the calculated value
     */
    public abstract double calcVolume();

    /**
     * This abstract method executes the getDimensions method of the current initialized sub class
     *
     * @return a representation of the dimension values that were used for the shape
     */
    public abstract String getDimensions();

    /**
     * Overrides the java equals method in order to add the ID and name comparing functionality when using the equals method
     *
     * @param shape is the shape object to be compared with
     * @return true if the comparing shape id and name params are equal to the current shape or true otherwise
     */
    @Override
    public boolean equals(Object shape) {
        if (this == shape) {
            return true;
        }
        if (shape == null || getClass() != shape.getClass()) {
            return false;
        }
        Shape that = (Shape) shape;
        if (this.id != that.id && (!this.name.equals(that.name))) {
            return false;
        }
        return this.shapeType == that.shapeType;
    }

    /**
     * Overrides the java hashCode method in order to build hashes with only the id, name and shapeType
     *
     * @return a hash created by hashing the hashCode values of the given parameter values
     */
    @Override
    public int hashCode() {
        return 31 * (31 * this.id + this.name.hashCode()) + this.shapeType.hashCode();
    }
}
