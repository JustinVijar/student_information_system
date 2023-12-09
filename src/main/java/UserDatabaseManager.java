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
    public int loginUser(String user, char[] pass) throws SQLException {
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
        }
    }

    public User retrieveUser(String username) throws SQLException {
        String userQuery = "SELECT * FROM user WHERE username = ?";
        Connection connection = getDatabaseConnection();
        try (PreparedStatement userStatement = connection.prepareStatement(userQuery)) {
            userStatement.setString(1, username);
            ResultSet res = userStatement.executeQuery();
            res.next();
            return new User(
                    res.getInt("user_id"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getBoolean("is_faculty")
            );
        } catch (SQLException e) {
            return null;
        }
    }

    public User retrieveUser(int userId) throws SQLException {
        String userQuery = "SELECT * FROM user WHERE user_id = ?";
        Connection connection = getDatabaseConnection();
        try (PreparedStatement userStatement = connection.prepareStatement(userQuery)) {
            userStatement.setInt(1, userId);
            ResultSet res = userStatement.executeQuery();
            res.next();
            return new User(
                    res.getInt("user_id"),
                    res.getString("username"),
                    res.getString("password"),
                    res.getBoolean("is_faculty")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Person retrievePerson(String username)
            throws SQLException {
        String query = "SELECT * FROM " + (retrieveUser(username).isFaculty() ? "faculty" : "student") + " WHERE user_id = ?";

        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, retrieveUser(username).getId());
            ResultSet res = statement.executeQuery();
            res.next();
            return new Person(
                    res.getString("first_name"),
                    res.getString("middle_name"),
                    res.getString("last_name"),
                    res.getString("contact_number"),
                    res.getString("email")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int registerUser(User user) throws SQLException {
        String userQuery = "INSERT INTO user(username, password, is_faculty) VALUES (?,?,?)";
        Connection connection = getDatabaseConnection();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setBoolean(3, user.getFaculty());
            if (statement.executeUpdate() > 0) {
                connection.commit();
                ResultSet generatedUserId = statement.getGeneratedKeys();
                generatedUserId.next();
                return generatedUserId.getInt(1);
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return -1;
    }

    public boolean registerPerson(int userId, Person person) throws SQLException {
        String personQuery = "INSERT INTO "+(retrieveUser(userId).isFaculty() ? "faculty" : "student")+
                "(first_name,middle_name,last_name,contact_number,email,user_id) " +
                "VALUES (?,?,?,?,?,?)";
        Connection connection = getDatabaseConnection();
        try(PreparedStatement personStatement = connection.prepareStatement(personQuery)) {
            connection.setAutoCommit(false);
            personStatement.setString(1, person.getFirstName());
            personStatement.setString(2, person.getMiddleName());
            personStatement.setString(3, person.getLastName());
            personStatement.setString(4, person.getContactNumber());
            personStatement.setString(5, person.getEmail());
            personStatement.setInt(6,  userId);
            boolean res = personStatement.executeUpdate() > 0;
            connection.commit();
            return res;
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    public boolean registerUserPerson(User user, Person person) throws SQLException {
        int userId = registerUser(user);
        return (userId != -1) && registerPerson(userId, person);
    }

}