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

public class StudentLoginForm extends JFrame {

    private JTextField studentIdField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;
    private JLabel titleLabel;

    public StudentLoginForm() {
        setTitle("Student Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        createComponents();
        addComponentsToPanel();
        attachListeners();

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        studentIdField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");
        backButton = new JButton("<");

        titleLabel = new JLabel("Student Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void addComponentsToPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        
        // Add the back button
        panel.add(backButton);

        // Add the title label
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(titleLabel);

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);

        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        panel.add(new JLabel(""));
        panel.add(loginButton);

        add(panel);
    }
    
    private void attachListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginStudent();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateBack();
            }
        });
    }
    
    private void navigateBack() {
        // Open the JavaChipsAcademyFrame
        SwingUtilities.invokeLater(() -> {
            new JavaChipsAcademyFrame().setVisible(true);
        });

        // Close the current frame (FacultyRegistrationForm)
        setVisible(false);
    }

    private void loginStudent() {
        int studentId = Integer.parseInt(studentIdField.getText());
        String password = new String(passwordField.getPassword());

        // Check credentials in the database
        if (validateStudentCredentials(studentId, password)) {
            // Set the current student ID in the session
            Session.getInstance().setCurrentStudentId(studentId);
            // Open JavaChipsSISStudent frame on successful login
            openJavaChipsSISStudentFrame();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        }
    }

    private boolean validateStudentCredentials(int studentId, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM student WHERE student_id = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, studentId);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Returns true if a matching record is found
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void openJavaChipsSISStudentFrame() {
        SwingUtilities.invokeLater(() -> {
            new JavaChipsSISStudent().setVisible(true);
        });

        // Optionally, you can hide the current frame
        setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentLoginForm().setVisible(true);
        });
    }
}
