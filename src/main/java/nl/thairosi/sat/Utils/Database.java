package nl.thairosi.sat.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The Database Util class will be instantiated to use Database related methods
 */
public class Database {
    Connection connection;

    /**
     * This connect() method sets the MySQL database connection user login values and the jdbc URL for the database
     *
     * @return the connection itself using the java sql DriverManager class
     * @throws SQLException will be thrown when the DriverManagers getConnection method is not right
     */
    public Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/thairosi?serverTimezone=UTC";
        String usr = "root";
        String pwd = "";
        return connection = DriverManager.getConnection(url, usr, pwd);
    }

}
