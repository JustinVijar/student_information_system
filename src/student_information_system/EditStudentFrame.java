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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class EditStudentFrame extends JFrame {
    
    private JTextField studentIdField;
    private JTextField yearLevelField;
    private JTextField degreeProgramField;

    public EditStudentFrame() {
        setTitle("Edit Student");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 2));

        JLabel titleLabel = new JLabel("Edit/Update Student Information:          ");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdField = new JTextField();
        JButton searchButton = new JButton("Search");

        JLabel yearLevelLabel = new JLabel("Year Level:");
        yearLevelField = new JTextField();
        JLabel degreeProgramLabel = new JLabel("Degree Program:");
        degreeProgramField = new JTextField();

        JButton updateButton = new JButton("Update");
        
        mainPanel.add(titleLabel);
        mainPanel.add(new JLabel());
        mainPanel.add(studentIdLabel);
        mainPanel.add(studentIdField);
        mainPanel.add(new JLabel()); // Placeholder
        mainPanel.add(searchButton);
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(yearLevelLabel);
        mainPanel.add(yearLevelField);
        mainPanel.add(degreeProgramLabel);
        mainPanel.add(degreeProgramField);
        mainPanel.add(new JLabel()); // Placeholder
        mainPanel.add(updateButton);

        add(mainPanel);
        
        // Center the frame on the screen
        setLocationRelativeTo(null);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();

                // Call a method to search for the student in the database
                // and update the fields accordingly
                searchStudent(studentId);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText();
                String yearLevel = yearLevelField.getText();
                String degreeProgram = degreeProgramField.getText();

                // Call a method to update the student in the database
                // based on the entered information
                updateStudent(studentId, yearLevel, degreeProgram);
            }
        });
    }
    
    private void searchStudent(String studentId) {
        // Implement the database search functionality here
        // Use JDBC to connect to your database and execute a query

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM student WHERE student_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, studentId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Student found
                        String yearLevel = resultSet.getString("year_level");
                        String degreeProgram = resultSet.getString("degree_program");
                        JOptionPane.showMessageDialog(EditStudentFrame.this, "Student Found");
                        // Update the fields
                        yearLevelField.setText(yearLevel);
                        degreeProgramField.setText(degreeProgram);
                    } else {
                        // Student not found
                        JOptionPane.showMessageDialog(EditStudentFrame.this, "Student Not Found");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateStudent(String studentId, String yearLevel, String degreeProgram) {
        // Implement the database update functionality here
        // Use JDBC to connect to your database and execute an update query

        try (Connection connection = DatabaseConnection.getConnection()){
            String query = "UPDATE student SET year_level = ?, degree_program = ? WHERE student_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, yearLevel);
                statement.setString(2, degreeProgram);
                statement.setString(3, studentId);

                // Execute the update query
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // Update successful
                    JOptionPane.showMessageDialog(EditStudentFrame.this, "Student Updated Successfully");
                } else {
                    // Update failed
                    JOptionPane.showMessageDialog(EditStudentFrame.this, "Failed to Update Student");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
