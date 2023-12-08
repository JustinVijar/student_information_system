import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseManager extends SQLConnection{

    UserDatabaseManager(String databaseURL, String username, String password) {
        super(databaseURL, username, password);
    }

    /**
     * @param user String username of the user
     * @param pass Character array of the user's password
     * @return -1 is fail, 0 is student login, 1 is faculty login
     */
    public int loginUser(String user, char[] pass) {
        String query = "SELECT user_id, is_faculty FROM user WHERE username = ? AND password = ?";
        try (Connection connection = getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, new String(pass));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getBoolean("is_faculty")) {
                    return 1;
                }
                return 0;
            }
            return -1;
        }catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
