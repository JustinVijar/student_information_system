public class User {

    private String username, password;
    private boolean isFaculty;

    public User(String username, String password, boolean isFaculty) {
        this.username = username;
        this.password = password;
        this.isFaculty = isFaculty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFaculty() {
        return isFaculty;
    }

    public void setFaculty(boolean faculty) {
        isFaculty = faculty;
    }
}
