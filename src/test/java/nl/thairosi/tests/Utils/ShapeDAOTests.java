package nl.thairosi.tests.Utils;

import nl.thairosi.sat.Utils.Database;
import nl.thairosi.sat.Utils.ShapeDAO;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test class tests the methods of the ShapeDAO Utils class
 */
public class ShapeDAOTests {
    private final ShapeDAO shapeDAO;
    private final Database database;

    /**
     * The constructor instantiates a new ShapeDAO and a Database class
     */
    public ShapeDAOTests() {
        this.shapeDAO = new ShapeDAO();
        this.database = new Database();
    }

    /**
     * This test tests if the amount of read shapes is the same as the records count in the database
     *
     * @throws SQLException if the SQL query syntax is incorrect
     */
    @Test
    void testCorrectAmountOfShapesAreRead() throws SQLException {
        Connection connection = database.connect();
        int databaseRecordsCount = 0;
        String[] shapes = {"cone", "cylinder", "cube", "pyramid", "sphere"};
        for (String shape : shapes) {
            ResultSet rs = connection.createStatement().executeQuery("SELECT COUNT(*) FROM " + shape);
            rs.next();
            databaseRecordsCount += rs.getInt(1);
        }
        int readShapesCount = shapeDAO.readShapes().size();
        assertEquals(databaseRecordsCount, readShapesCount);
    }
}
