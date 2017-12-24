package Model.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {

    private Connection dbconnection;

    public Connection getConnetion() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        dbconnection = DriverManager.getConnection("jdbc:sqlite:db.db");
        return dbconnection;
    }

}
