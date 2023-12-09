import java.sql.*;

public class SQLConnection {

    private String databaseURL;
    private String username;
    private String password;

    SQLConnection(String databaseURL, String username, String password) {
        this.databaseURL = databaseURL;
        this.username = username;
        this.password = password;
    }

    public Connection getDatabaseConnection() throws SQLException{
        return DriverManager.getConnection(databaseURL, username, password);
    }

}
