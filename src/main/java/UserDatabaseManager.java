import java.sql.*;

public class UserDatabaseManager extends SQLConnection {

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
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean registerUser(User user, Person person) {
        String insertUserQuery = "INSERT INTO user(username, password, is_faculty) VALUES (?,?,?)";
        String insertStudentQuery = "INSERT INTO student(first_name,middle_name,last_name,contact_number,email,user_id) " +
                                    "VALUES (?,?,?,?,?,?)";
        String insertFacultyQuery = "INSERT INTO faculty(first_name,middle_name,last_name,contact_number,email,user_id) " +
                                    "VALUES (?,?,?,?,?,?)";
        String findExistingUserQuery = "SELECT * FROM user WHERE username = ?";
        Connection connection = getDatabaseConnection();
        try(PreparedStatement insertUserStatement = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement findExistingUserStatement = connection.prepareStatement(findExistingUserQuery)) {
            connection.setAutoCommit(false);
            findExistingUserStatement.setString(1, user.getUsername());
            ResultSet existingUser = findExistingUserStatement.executeQuery();
            if (existingUser.next()) {
                return false;
            }

            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.setBoolean(3, user.isFaculty());
            insertUserStatement.executeUpdate();

            ResultSet generatedUserId = insertUserStatement.getGeneratedKeys();
            generatedUserId.next();
            int generatedId = generatedUserId.getInt(1);

            if (user.isFaculty()) {
                try(PreparedStatement insertFacultyStatement = connection.prepareStatement(insertFacultyQuery)) {
                    insertFacultyStatement.setString(1, person.getFirstName());
                    insertFacultyStatement.setString(2, person.getMiddleName());
                    insertFacultyStatement.setString(3, person.getLastName());
                    insertFacultyStatement.setString(4, person.getContactNumber());
                    insertFacultyStatement.setString(5, person.getEmail());
                    insertFacultyStatement.setInt(6, generatedId);
                    boolean res = insertFacultyStatement.executeUpdate() > 0;
                    connection.commit();
                    return res;
                }
            } else {
                try(PreparedStatement insertStudentStatement = connection.prepareStatement(insertStudentQuery)) {
                    insertStudentStatement.setString(1, person.getFirstName());
                    insertStudentStatement.setString(2, person.getMiddleName());
                    insertStudentStatement.setString(3, person.getLastName());
                    insertStudentStatement.setString(4, person.getContactNumber());
                    insertStudentStatement.setString(5, person.getEmail());
                    insertStudentStatement.setInt(6, generatedId);
                    boolean res = insertStudentStatement.executeUpdate() > 0;
                    connection.commit();
                    return res;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

}