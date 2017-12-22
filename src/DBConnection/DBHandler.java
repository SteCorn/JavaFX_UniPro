package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {

    Connection dbconnection;
    private static boolean hasData = false;

    public Connection getConnetion() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        dbconnection = DriverManager.getConnection("jdbc:sqlite:SQLiteTest.db");
        return dbconnection;
    }

}
