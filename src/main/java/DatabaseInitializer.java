import java.sql.*;

public class DatabaseInitializer {

    private SQLConnection sqlConnection;

    DatabaseInitializer(SQLConnection sqlConnection) {
        this.sqlConnection = sqlConnection;
    }

    public void initializeDatabase() {
        createTables();
        createForeignKeys();
    }

    private void createTables() {
        createTableUser();
        createTableFaculty();
        createTableStudent();
        createTableCourse();
        createTableSection();
        createTableEnrollment();
        createTableSectionSchedule();
        createTablePrerequisites();
        createTableAdvisor();
        createTableTranscript();
    }

    private void createTableUser() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS user (" +
                "user_id INT PRIMARY KEY AUTO_INCREMENT," +
                "username VARCHAR(255)," +
                "password VARCHAR(255)," +
                "is_faculty BOOLEAN" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createTableFaculty() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS faculty (" +
                "faculty_id INT PRIMARY KEY AUTO_INCREMENT," +
                "first_name VARCHAR(255)," +
                "middle_name VARCHAR(255)," +
                "last_name VARCHAR(255)," +
                "contact_number VARCHAR(20)," +
                "email VARCHAR(255)," +
                "user_id INT" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createTableStudent() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS student (" +
                "student_id INT PRIMARY KEY AUTO_INCREMENT," +
                "first_name VARCHAR(255)," +
                "middle_name VARCHAR(255)," +
                "last_name VARCHAR(255)," +
                "contact_number VARCHAR(20)," +
                "email VARCHAR(255)," +
                "user_id INT" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createTableCourse() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS course (" +
                "course_id INT PRIMARY KEY AUTO_INCREMENT," +
                "section_id INT," +
                "title VARCHAR(255)," +
                "credits INT," +
                "description TEXT" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createTableSection() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS section (" +
                "section_id INT PRIMARY KEY AUTO_INCREMENT," +
                "course_id INT," +
                "section_name VARCHAR(255)" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createTableEnrollment() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS enrollment (" +
                "enrollment_id INT PRIMARY KEY AUTO_INCREMENT," +
                "student_id INT," +
                "course_id INT," +
                "enrollment_date TIMESTAMP" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createTableSectionSchedule() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS section_schedule (" +
                "section_schedule_id INT PRIMARY KEY AUTO_INCREMENT," +
                "section_id INT," +
                "faculty_id_instructor INT," +
                "meeting_time TIME," +
                "meeting_date_start DATE," +
                "meeting_date_end DATE" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createTablePrerequisites() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS prerequisites (" +
                "prereq_id INT PRIMARY KEY AUTO_INCREMENT," +
                "course_id INT," +
                "course_id_prereq INT" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createTableAdvisor() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS advisor (" +
                "advisor_id INT PRIMARY KEY AUTO_INCREMENT," +
                "faculty_id INT," +
                "section_id INT" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createTableTranscript() {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS transcript (" +
                "transcript_id INT PRIMARY KEY AUTO_INCREMENT," +
                "student_id INT," +
                "course_id INT," +
                "grades DOUBLE," +
                "credits_earned INT," +
                "is_finished BOOLEAN" +
                ")";
        executeUpdate(createTableQuery);
    }

    private void createForeignKeys() {
        createForeignKeyFaculty();
        createForeignKeyStudent();
        createForeignKeyCourseSection();
        createForeignKeyEnrollmentStudent();
        createForeignKeyEnrollmentCourse();
        createForeignKeySectionScheduleSection();
        createForeignKeySectionScheduleFaculty();
        createForeignKeyPrerequisitesCourse();
        createForeignKeyPrerequisitesPrereq();
        createForeignKeyAdvisorFaculty();
        createForeignKeyAdvisorSection();
        createForeignKeyTranscriptStudent();
        createForeignKeyTranscriptCourse();
    }

    private void createForeignKeyFaculty() {
        String createForeignKeyQuery = "ALTER TABLE faculty " +
                "ADD CONSTRAINT fk_faculty_user FOREIGN KEY (user_id) REFERENCES user(user_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyStudent() {
        String createForeignKeyQuery = "ALTER TABLE student " +
                "ADD CONSTRAINT fk_student_user FOREIGN KEY (user_id) REFERENCES user(user_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyCourseSection() {
        String createForeignKeyQuery = "ALTER TABLE course " +
                "ADD CONSTRAINT fk_course_section FOREIGN KEY (section_id) REFERENCES section(section_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyEnrollmentStudent() {
        String createForeignKeyQuery = "ALTER TABLE enrollment " +
                "ADD CONSTRAINT fk_enrollment_student FOREIGN KEY (student_id) REFERENCES student(student_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyEnrollmentCourse() {
        String createForeignKeyQuery = "ALTER TABLE enrollment " +
                "ADD CONSTRAINT fk_enrollment_course FOREIGN KEY (course_id) REFERENCES course(course_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeySectionScheduleSection() {
        String createForeignKeyQuery = "ALTER TABLE section_schedule " +
                "ADD CONSTRAINT fk_schedule_section FOREIGN KEY (section_id) REFERENCES section(section_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeySectionScheduleFaculty() {
        String createForeignKeyQuery = "ALTER TABLE section_schedule " +
                "ADD CONSTRAINT fk_schedule_faculty FOREIGN KEY (faculty_id_instructor) REFERENCES faculty(faculty_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyPrerequisitesCourse() {
        String createForeignKeyQuery = "ALTER TABLE prerequisites " +
                "ADD CONSTRAINT fk_prerequisites_course FOREIGN KEY (course_id) REFERENCES course(course_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyPrerequisitesPrereq() {
        String createForeignKeyQuery = "ALTER TABLE prerequisites " +
                "ADD CONSTRAINT fk_prerequisites_prereq FOREIGN KEY (course_id_prereq) REFERENCES course(course_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyAdvisorFaculty() {
        String createForeignKeyQuery = "ALTER TABLE advisor " +
                "ADD CONSTRAINT fk_advisor_faculty FOREIGN KEY (faculty_id) REFERENCES faculty(faculty_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyAdvisorSection() {
        String createForeignKeyQuery = "ALTER TABLE advisor " +
                "ADD CONSTRAINT fk_advisor_section FOREIGN KEY (section_id) REFERENCES section(section_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyTranscriptStudent() {
        String createForeignKeyQuery = "ALTER TABLE transcript " +
                "ADD CONSTRAINT fk_transcript_student FOREIGN KEY (student_id) REFERENCES student(student_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void createForeignKeyTranscriptCourse() {
        String createForeignKeyQuery = "ALTER TABLE transcript " +
                "ADD CONSTRAINT fk_transcript_course FOREIGN KEY (course_id) REFERENCES course(course_id)";
        executeUpdate(createForeignKeyQuery);
    }

    private void executeUpdate(String query) {
        try (Connection connection = sqlConnection.getDatabaseConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
