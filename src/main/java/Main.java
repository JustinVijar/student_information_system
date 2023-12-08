import java.sql.*;

public class Main {
    public static void main(String[] args) {
            // Change this based on your MySQL server details
            String mysqlURL = "jdbc:mysql://localhost:3307/";
            String username = "root";
            String password = "javachips";


            // Connection to create the 'StudentInformationSystem' schema
            SQLConnection sqlConnection = new SQLConnection(mysqlURL, username, password);
            try (Connection mysqlConnection = sqlConnection.getDatabaseConnection()) {
                createDatabase(mysqlConnection, "StudentInformationSystem");
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }

            // Connection to the 'StudentInformationSystem' database
            String databaseURL = mysqlURL+"StudentInformationSystem";
            sqlConnection = new SQLConnection(databaseURL, username, password);
            DatabaseInitializer initializer = new DatabaseInitializer(sqlConnection);
            initializer.initializeDatabase();
        }

        private static void createDatabase(Connection connection, String schemaName) {
            try {
                String createSchemaQuery = "CREATE DATABASE IF NOT EXISTS " + schemaName;
                try (PreparedStatement preparedStatement = connection.prepareStatement(createSchemaQuery)) {
                    preparedStatement.executeUpdate();
                    System.out.print("created database");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
