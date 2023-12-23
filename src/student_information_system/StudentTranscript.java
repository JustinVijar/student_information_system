/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

public class StudentTranscript extends JFrame {

    public StudentTranscript() {
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

        // Create and add the credentialPanel to the informationPanel
        JPanel credentialPanel = createCredentialPanel();
        credentialPanel.setLayout(new GridLayout(2,2));
        informationPanel.add(credentialPanel, BorderLayout.NORTH);
        
        // Create and add the gradePanel to the informationPanel
        JPanel gradePanel = createGradePanel();
        gradePanel.setLayout(new FlowLayout());
        informationPanel.add(gradePanel, BorderLayout.CENTER);

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
        
        //set button color:
        transcriptButton.setBackground(Color.orange);

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /// If this is your assigned task, and
                //you want to make your part, please modify the following lines
                // to open your class: Change "JavaChipsSISFaculty" to your java class name
                // don't forget to copy the nav and listeners for smoother navigation on all parts
//                SwingUtilities.invokeLater(() -> {
//                    new JavaChipsSISFaculty().setVisible(true);
//                });
//
//                // Optionally, you can hide the current frame
//                setVisible(false);
            }
        });

        viewCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If this is your assigned task, and
                //you want to make your part, please modify the following lines
                // to open your class: Change "JavaChipsSISFaculty" to your java class name
                // don't forget to copy the nav and listeners for smoother navigation on all parts
//                SwingUtilities.invokeLater(() -> {
//                    new JavaChipsSISFaculty().setVisible(true);
//                });
//
//                // Optionally, you can hide the current frame
//                setVisible(false);
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
        JPanel credentialPanel = new JPanel(new GridLayout(2, 4));

        // Add labels and values for Student ID, Name, Year Level, and Degree Program
        credentialPanel.add(new JLabel("Student ID:"));
        credentialPanel.add(new JLabel(String.valueOf(currentStudentId))); // Display the current student ID

        String[] studentInfo = getStudentInfoFromDatabase(currentStudentId);

        // Check if student information was retrieved successfully
        if (studentInfo != null) {
            credentialPanel.add(new JLabel("Year Level:"));
            credentialPanel.add(new JLabel(studentInfo[1]));
            
            credentialPanel.add(new JLabel("Name:"));
            credentialPanel.add(new JLabel(studentInfo[0]));
 
            credentialPanel.add(new JLabel("Degree Program:"));
            credentialPanel.add(new JLabel(studentInfo[2]));
        } else {
            // Handle the case where student information retrieval failed
            credentialPanel.add(new JLabel("Error retrieving student information"));
            credentialPanel.add(new JLabel(""));
        }

        return credentialPanel;
    }

    // Method to retrieve student information from the database
    private String[] getStudentInfoFromDatabase(int studentId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT last_name, first_name, middle_name, year_level, degree_program FROM student WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, studentId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String lastName = resultSet.getString("last_name");
                        String firstName = resultSet.getString("first_name");
                        String middleName = resultSet.getString("middle_name");
                        String yearLevel = resultSet.getString("year_level");
                        String degreeProgram = resultSet.getString("degree_program");

                        // Return an array containing the retrieved information
                        return new String[]{lastName + ", " + firstName + (middleName != null ? " " + middleName : ""),
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

    private JPanel createGradePanel() {
        int studentId = Session.getInstance().getCurrentStudentId();
        
        JLabel gap = new JLabel();
        
        // Create a panel with BorderLayout
        JPanel gradePanel = new JPanel(new BorderLayout());

        // Retrieve grade information from the database
        Object[][] gradeData = getGradeDataFromDatabase(studentId);
        String[] columnNames = {"Course ID", "Course Title", "Grades"};

        // Create a table model with the retrieved data
        DefaultTableModel tableModel = new DefaultTableModel(gradeData, columnNames);
        JTable gradeTable = new JTable(tableModel);

        // Add the table to a scroll pane for scrolling if needed
        JScrollPane scrollPane = new JScrollPane(gradeTable);
        gradePanel.add(scrollPane, BorderLayout.CENTER);

        return gradePanel;
    }

    private Object[][] getGradeDataFromDatabase(int studentId) {
    try (Connection connection = DatabaseConnection.getConnection()) {
        // Use TYPE_SCROLL_INSENSITIVE to enable moving the cursor backward
        String sql = "SELECT t.course_id, c.title, t.grades FROM transcript t "
                   + "JOIN course c ON t.course_id = c.course_id "
                   + "WHERE t.student_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            preparedStatement.setInt(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Calculate the number of rows in the result set
                int rowCount = 0;
                while (resultSet.next()) {
                    rowCount++;
                }

                // Initialize the array to hold data from the result set
                Object[][] gradeData = new Object[rowCount][3];

                // Reset the result set cursor to the beginning
                resultSet.beforeFirst();

                // Populate the array with data from the result set
                int index = 0;
                while (resultSet.next()) {
                    gradeData[index][0] = resultSet.getString("course_id");
                    gradeData[index][1] = resultSet.getString("title");
                    gradeData[index][2] = resultSet.getString("grades");
                    index++;
                }

                return gradeData;
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        // Return an empty array in case of an exception
        return new Object[0][0];
    }
}

    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentTranscript().setVisible(true);
        });
    }
}

