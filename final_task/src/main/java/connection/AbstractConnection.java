package connection;

import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import java.sql.*;

public class AbstractConnection {

    static ISettingsFile environment = new JsonSettingsFile("config.json");

    private static final String URL = environment.getValue("/dbUrl").toString();
    private static final String USER = environment.getValue("/dbUser").toString();
    private static final String PASSWORD = environment.getValue("/dbPassword").toString();
    public static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getInstance().error(e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getInstance().error(e.getMessage());
            throw new RuntimeException(e);
        }
        connection = null;
    }
}
