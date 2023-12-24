/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package task3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 *
 * @author Makizz
 */
public class InputGrades extends javax.swing.JFrame {

    /**
     * Creates new form InputGrades
     */
    public InputGrades() {
        initComponents();
        setTitle("Input Grades");
        populateCourseComboBox();
        // Add a DocumentListener to jTextField1
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkStudentId();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkStudentId();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Plain text components do not fire these events
            }
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jComboBoxCourse = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBoxCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Student ID Number");

        jLabel2.setText("Course");

        jLabel3.setText("Grade");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxCourse, 0, 261, Short.MAX_VALUE)
                                .addComponent(jTextField1)))
                        .addGap(179, 179, 179))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(254, 254, 254))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jComboBoxCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String studentId = jTextField1.getText().trim();
        String courseTitle = (String) jComboBoxCourse.getSelectedItem(); // Assuming the combo box is filled with course titles
        String grades = jTextField2.getText().trim();

        // Validate if required fields are not empty
        if (studentId.isEmpty() || courseTitle == null || grades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }

        Connection connection = null;
        try {
            // Get a connection from your DatabaseConnection class
            connection = DatabaseConnection.getConnection();

            // Get the course_id and credits based on the selected course title
            CourseInfo courseInfo = getCourseInfo(connection, courseTitle);

            // Check if the course exists
            if (courseInfo != null) {
                // SQL query to insert a new record into the 'transcript' table
                String sql = "INSERT INTO transcript (student_id, course_id, grades, credits_earned) VALUES (?, ?, ?, ?)";

                // Prepare the SQL statement
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Set parameters for the insert query
                    statement.setString(1, studentId);
                    statement.setInt(2, courseInfo.getCourseId());
                    statement.setString(3, grades);
                    statement.setInt(4, courseInfo.getCreditsEarned());

                    // Execute the update query
                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        // If the insert was successful, display a success message
                        JOptionPane.showMessageDialog(this, "Transcript record inserted successfully!");
                        dispose();
                    } else {
                        // If no rows were affected, display a failure message
                        JOptionPane.showMessageDialog(this, "Failed to insert transcript record.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selected course not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection in the finally block
            DatabaseConnection.closeConnection(connection);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputGrades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputGrades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputGrades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputGrades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputGrades().setVisible(true);
            }
        });
    }
    
     private CourseInfo getCourseInfo(Connection connection, String courseTitle) throws SQLException {
        CourseInfo courseInfo = null;

        // SQL query to retrieve course_id and credits based on course title
        String sql = "SELECT course_id, credits FROM course WHERE title = ?";

        // Prepare the SQL statement
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the course title parameter
            statement.setString(1, courseTitle);

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                // Check if there are results
                if (resultSet.next()) {
                    int courseId = resultSet.getInt("course_id");
                    int creditsEarned = resultSet.getInt("credits");
                    courseInfo = new CourseInfo(courseId, creditsEarned);
                }
            }
        }

        return courseInfo;
    }

    // Inner class to store course information
    private static class CourseInfo {
        private final int courseId;
        private final int creditsEarned;

        public CourseInfo(int courseId, int creditsEarned) {
            this.courseId = courseId;
            this.creditsEarned = creditsEarned;
        }

        public int getCourseId() {
            return courseId;
        }

        public int getCreditsEarned() {
            return creditsEarned;
        }
    }
    private void checkStudentId() {
        String studentId = jTextField1.getText().trim();

        // Check if the student_id is not empty
        if (!studentId.isEmpty()) {
            Connection connection = null;
            try {
                // Get a connection from your DatabaseConnection class
                connection = DatabaseConnection.getConnection();

                // SQL query to retrieve student information based on student_id
                String sql = "SELECT first_name, middle_name, last_name FROM student WHERE student_id = ?";

                // Prepare the SQL statement
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Set the student_id parameter
                    statement.setString(1, studentId);

                    // Execute the query
                    try (ResultSet resultSet = statement.executeQuery()) {
                        // Check if the student is found
                        if (resultSet.next()) {
                            // Concatenate first_name, middle_name, and last_name
                            String fullName = resultSet.getString("first_name")
                                    + " " + resultSet.getString("middle_name")
                                    + " " + resultSet.getString("last_name");

                            // Set the text of jLabel1
                            jLabel1.setText(fullName);
                        } else {
                            // If student is not found, clear jLabel1
                            jLabel1.setText("Student not found");
                        }
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                // Close the database connection in the finally block
                DatabaseConnection.closeConnection(connection);
            }
        } else {
            // If student_id is empty, clear jLabel1
            jLabel1.setText("");
        }
    }
    
    private void populateCourseComboBox() {
        Connection connection = null;
        try {
            // Get a connection from your DatabaseConnection class
            connection = DatabaseConnection.getConnection();

            // SQL query to retrieve courses from the 'course' table
            String sql = "SELECT title FROM course";

            // Prepare the SQL statement
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Check if there are results
                    if (resultSet.next()) {
                        // Populate the combo box with course names
                        Vector<String> courseList = new Vector<>();
                        do {
                            courseList.add(resultSet.getString("title"));
                        } while (resultSet.next());

                        // Use DefaultComboBoxModel to set items in the combo box
                        jComboBoxCourse.setModel(new DefaultComboBoxModel<>(courseList));
                    } else {
                        // If no courses found, display a default message
                        jComboBoxCourse.setModel(new DefaultComboBoxModel<>(new String[]{"No Course Found"}));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection in the finally block
            DatabaseConnection.closeConnection(connection);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxCourse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}