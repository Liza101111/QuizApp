package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static Connection getDBConnection() {
        Connection connection = null;
        // JDBC driver name and database URL
        String dbUrl = "jdbc:mysql://localhost:3306/quiz_app?serverTimezone=UTC";
        String driverJDBC = "com.mysql.cj.jdbc.Driver";
        //  Database credentials
        String userDB = "Lucia";
        String passwordDB = "101111";

        try {
            // Register JDBC driver
            Class.forName(driverJDBC);
            // Connect to database
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, userDB, passwordDB);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
