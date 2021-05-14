package nl.thairosi.sat.Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.thairosi.sat.Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * The ShapeDAO Util class contains all the necessary CRUD methods for querying the Database
 */
public class ShapeDAO {

    Database database;

    /**
     * This constructor instantiates a database connection object using the Database class
     */
    public ShapeDAO() {
        this.database = new Database();
    }

    /**
     * Gets the necessary query information, creates the query and adds a new shape record in the database
     *
     * @param shape is the Shape Object to be created in the database
     * @throws SQLException throws an SQL exception when the SQL query syntax is incorrect
     */
    public void createShape(Shape shape) throws SQLException {
        Connection connection = database.connect();
        String table = shape.getShapeType().toString().toLowerCase();
        String columns = getColumnsQuery(shape);
        String questionMarks = getQuestionMarks(shape);
        ArrayList<String> values = getValues(shape);
        String query = "INSERT INTO " + table + " (" + columns + ") VALUES (" + questionMarks + ")";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, shape.getName());
            for (int i = 0; i < values.size(); i++) {
                statement.setString(i + 2, values.get(i));
            }
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the necessary query information, creates the query and adds a new shape record in the database
     * This overloaded method creates a shape record in the database including a given ID
     *
     * @param shape is the Shape Object to be created in the database
     * @param id    is the id to be used in stead of using the auto increment function in the database
     * @throws SQLException throws an SQL exception when the SQL query syntax is incorrect
     */
    public void createShape(Shape shape, int id) throws SQLException {
        Connection connection = database.connect();
        String table = shape.getShapeType().toString().toLowerCase();
        String columns = "ID, " + getColumnsQuery(shape);
        String questionMarks = "?," + getQuestionMarks(shape);
        ArrayList<String> values = getValues(shape);
        String query = "INSERT INTO " + table + " (" + columns + ") VALUES (" + questionMarks + ")";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, shape.getName());
            for (int i = 0; i < values.size(); i++) {
                statement.setString(i + 3, values.get(i));
            }
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates and returns the columns part of the query
     *
     * @param shape is the Shape Object where the column names are to be returned from
     * @return the column part of the SQL query conform the given Shape
     */
    private String getColumnsQuery(Shape shape) {
        StringBuilder columns = new StringBuilder("Name");
        for (Map.Entry<String, Object> entry : ObjectUtils.getFieldNamesAndValues(shape).entrySet()) {
            columns.append(", ").append(entry.getKey());
        }
        return columns.toString();
    }

    /**
     * Creates and returns the right amount of question marks necessary for the values of the SQL prepared statement
     *
     * @param shape is the Shape Object where the amount of attributes is to be counted for
     * @return the values part of the SQL query that is needed to create a shape record in the database
     */
    //
    private String getQuestionMarks(Shape shape) {
        return "?" + ", ?".repeat(getValues(shape).size());
    }

    /**
     * Creates an ArrayList of the used values in a shape object
     *
     * @param shape is the shape object where the values are to be returned from
     * @return an ArrayList of the Shape Object values
     */
    private ArrayList<String> getValues(Shape shape) {
        ArrayList<String> values = new ArrayList<>();
        for (Map.Entry<String, Object> entry : ObjectUtils.getFieldNamesAndValues(shape).entrySet()) {
            values.add(String.valueOf(entry.getValue()));
        }
        return values;
    }

    /**
     * Creates an ObservableList of Shape objects out of the Shape records present in the database
     *
     * @return the created ObservableList that is created out of the database shape tables
     * @throws SQLException if the SQL query is incorrect when this method is executed
     */
    //Reads all shapes that are present in the database
    public ObservableList<Shape> readShapes() throws SQLException {
        ObservableList<Shape> shapes = FXCollections.observableArrayList();
        Connection connection = database.connect();

        try {
            Statement st = connection.createStatement();
            for (ShapeType shapeType : ShapeType.values()) {
                String type = shapeType.toString().toLowerCase();
                String query = "SELECT * FROM " + type;
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String name = rs.getString("Name");
                    switch (type) {
                        case "cube": {
                            double edge = rs.getDouble("Edge");
                            Shape cube = new Cube(id, name, ShapeType.CUBE, edge);
                            shapes.add(cube);
                            break;
                        }
                        case "cone": {
                            double height = rs.getDouble("Height");
                            double diameter = rs.getDouble("Diameter");
                            Shape cone = new Cone(id, name, ShapeType.CONE, height, diameter);
                            shapes.add(cone);
                            break;
                        }
                        case "cylinder": {
                            double height = rs.getDouble("Height");
                            double diameter = rs.getDouble("Diameter");
                            Shape cylinder = new Cylinder(id, name, ShapeType.CYLINDER, height, diameter);
                            shapes.add(cylinder);
                            break;
                        }
                        case "pyramid": {
                            double height = rs.getDouble("Height");
                            double edge = rs.getDouble("Edge");
                            Shape pyramid = new Pyramid(id, name, ShapeType.PYRAMID, height, edge);
                            shapes.add(pyramid);
                            break;
                        }
                        case "sphere": {
                            double diameter = rs.getDouble("Diameter");
                            Shape sphere = new Sphere(id, name, ShapeType.SPHERE, diameter);
                            shapes.add(sphere);
                            break;
                        }
                    }

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shapes;
    }

    /**
     * Converts a ObservableList containing Shape objects into a ArrayList
     *
     * @return an ArrayList of all shapes present in the database
     * @throws SQLException if the SQL code in the readShapes() method is incorrect
     */
    public ArrayList<Shape> readShapesAsArrayList() throws SQLException {
        ObservableList<Shape> shapes = readShapes();
        if (shapes == null) {
            return null;
        }
        return new ArrayList<>(shapes);
    }

    /**
     * Deletes the record of the selected shape from the database
     *
     * @param shape is the selected shape Object that will be deleted
     * @throws SQLException if the query syntax is not right
     */
    public void deleteShape(Shape shape) throws SQLException {
        String table = shape.getShapeType().toString().toLowerCase();
        String shapeId = String.valueOf(shape.getId());
        Connection connection = database.connect();
        String query = "DELETE FROM " + table + " WHERE ID = " + shapeId;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}