/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Reuel
 */
package student_information_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class StudentProfile extends JFrame {

    public StudentProfile() {
        setTitle("JavaChips Academy - Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);

        // Create a panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the navigation panel and add it to the north position
        JPanel navigationPanel = createNavigationPanel();
        mainPanel.add(navigationPanel, BorderLayout.NORTH);

        // Create a panel for information below the navigation
        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new BorderLayout());
        
        // Show student information
        JPanel credentialPanel = createCredentialPanel();
        credentialPanel.setLayout(new GridLayout(4,2));
        informationPanel.add(credentialPanel, BorderLayout.NORTH);

        mainPanel.add(informationPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private JPanel createNavigationPanel() {
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new FlowLayout());

        // Add buttons to the navigation panel
        JButton homeButton = new JButton("Home");
        JButton profileButton = new JButton("Profile");
        JButton viewCourseButton = new JButton("View Course");
        JButton transcriptButton = new JButton("Transcript and Academic Records");
        JLabel text = new JLabel("                                      ");
        JButton logoutButton = new JButton("Log out");
        
        //set button color:
        homeButton.setBackground(Color.orange);

        navigationPanel.add(homeButton);
        navigationPanel.add(profileButton);
        navigationPanel.add(viewCourseButton);
        navigationPanel.add(transcriptButton);
        navigationPanel.add(text);
        navigationPanel.add(logoutButton);

        // Add action listeners for the buttons
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new JavaChipsSISStudent().setVisible(true);
                });

                // Optionally, you can hide the current frame
                setVisible(false);
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If this is your assigned task, and
                //you want to make your part, please modify the following lines
                // to open your class: Change "JavaChipsSISFaculty" to your java class name
                // don't forget to copy the nav and listeners for smoother navigation on all parts
                 SwingUtilities.invokeLater(() -> {
                     new StudentProfile().setVisible(true);
                 });
                //
                // // Optionally, you can hide the current frame
                // setVisible(false);
            }
        });

        viewCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If this is your assigned task, and
                //you want to make your part, please modify the following lines
                // to open your class: Change "JavaChipsSISFaculty" to your java class name
                // don't forget to copy the nav and listeners for smoother navigation on all parts
                //SwingUtilities.invokeLater(() -> {
                //     new StudentProfile().setVisible(true);
                // });
                //
                // // Optionally, you can hide the current frame
                // setVisible(false);
            }
        });

        transcriptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If this is your assigned task, and - GARCES'S TASK NUMBER 4
                //you want to make your part, please modify the following lines
                // to open your class: Change "JavaChipsSISFaculty" to your java class name
                // don't forget to copy the nav and listeners for smoother navigation on all parts
                SwingUtilities.invokeLater(() -> {
                    new StudentTranscript().setVisible(true);
                });

                // Optionally, you can hide the current frame
                setVisible(false);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new JavaChipsAcademyFrame().setVisible(true);
                });

                // Optionally, you can hide the current frame
                setVisible(false);
            }
        });

        return navigationPanel;
    }
    
    
    
    private JPanel createCredentialPanel() {
        // Retrieve the current student ID from the session
        int currentStudentId = Session.getInstance().getCurrentStudentId();

        // Create a panel with GridLayout (4 rows, 2 columns) for displaying credentials
        JPanel credentialPanel = new JPanel(new GridLayout(2, 5));
        JLabel studentID = new JLabel(String.valueOf(currentStudentId));

        // Add labels and values for Student ID, Name, Year Level, and Degree Program
        credentialPanel.add(new JLabel("Student ID:"));
        credentialPanel.add(studentID); // Display the current student ID

        String[] studentInfo = getStudentInfoFromDatabase(currentStudentId);
        
        JButton edit = new JButton("Edit Profile");
        JTextField name = new JTextField("" + studentInfo[0]);
        JTextField year = new JTextField("" + studentInfo[3]);
        JTextField degree = new JTextField(("" + studentInfo[4]));
        JTextField contact = new JTextField("" + studentInfo[1]);
        JTextField email = new JTextField("" + studentInfo[2]);
        
        // Check if student information was retrieved successfully
        if (studentInfo != null) {
            credentialPanel.add(new JLabel("Name:"));
            credentialPanel.add(name);
            
            credentialPanel.add(new JLabel("Year Level:"));
            credentialPanel.add(year);
            
            credentialPanel.add(new JLabel("Degree Program:"));
            credentialPanel.add(degree);
            
            credentialPanel.add(new JLabel("Contact Number:"));
            credentialPanel.add(contact);
            
            credentialPanel.add(new JLabel("Email:"));
            credentialPanel.add(email);
            
            
            credentialPanel.add(edit);
           
            
        } else {
            // Handle the case where student information retrieval failed
            credentialPanel.add(new JLabel("Error retrieving student information"));
            credentialPanel.add(new JLabel(""));
            
        }
        
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentID.getText();
                String yearLevel = year.getText();
                String degreeProgram = degree.getText();
                String contactnum = contact.getText();
                String emailadd = email.getText();

                // Call a method to update the student in the database
                // based on the entered information
                updateProfile(studentId, yearLevel, degreeProgram, emailadd, contactnum);
            }
        });

        return credentialPanel;
    }
    
    private void updateProfile(String studentId, String yearLevel, String degreeProgram, String email, String contact) {
        // Implement the database update functionality here
        // Use JDBC to connect to your database and execute an update query

        try (Connection connection = DatabaseConnection.getConnection()){
            String query = "UPDATE student SET year_level = ?, degree_program = ?, email = ?, contact_number = ? WHERE student_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, yearLevel);
                statement.setString(2, degreeProgram);
                statement.setString(3, email);
                statement.setString(4, contact);
                statement.setString(5, studentId);

                // Execute the update query
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // Update successful
                    JOptionPane.showMessageDialog(StudentProfile.this, "Student Updated Successfully");
                } else {
                    // Update failed
                    JOptionPane.showMessageDialog(StudentProfile.this, "Failed to Update Student");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    // Method to retrieve student information from the database
    private String[] getStudentInfoFromDatabase(int studentId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT last_name, first_name, middle_name, contact_number, email, year_level, degree_program FROM student WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, studentId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String lastName = resultSet.getString("last_name");
                        String firstName = resultSet.getString("first_name");
                        String middleName = resultSet.getString("middle_name");
                        String contactnum = resultSet.getString("contact_number");
                        String emailadd = resultSet.getString("email");
                        String yearLevel = resultSet.getString("year_level");
                        String degreeProgram = resultSet.getString("degree_program");
                        

                        // Return an array containing the retrieved information
                        return new String[]{lastName + ", " + firstName + (middleName != null ? " " + middleName : ""),contactnum, emailadd, 
                                yearLevel, degreeProgram};
                    } else {
                        // Return null if the student is not found
                        return null;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Return null in case of an exception
            return null;
        }
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JavaChipsSISStudent().setVisible(true);
        });
    }
}


