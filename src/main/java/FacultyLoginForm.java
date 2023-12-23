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

public class FacultyLoginForm extends JFrame {

    private JTextField facultyIdField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;
    private JLabel titleLabel;

    public FacultyLoginForm() {
        setTitle("Faculty Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        createComponents();
        addComponentsToPanel();
        attachListeners();

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        facultyIdField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");
        backButton = new JButton("<");

        titleLabel = new JLabel("Faculty Login");
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
        
        panel.add(new JLabel("Faculty ID:"));
        panel.add(facultyIdField);

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
                loginFaculty();
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

    private void loginFaculty() {
        int facultyId = Integer.parseInt(facultyIdField.getText());
        String password = new String(passwordField.getPassword());

        // Check credentials in the database
        if (validateFacultyCredentials(facultyId, password)) {
            // Open JavaChipsSIS frame on successful login
            openJavaChipsSISFacultyFrame();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        }
    }

    private boolean validateFacultyCredentials(int facultyId, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM faculty WHERE faculty_id = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, facultyId);
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

    private void openJavaChipsSISFacultyFrame() {
        SwingUtilities.invokeLater(() -> {
            new JavaChipsSISFaculty().setVisible(true);
        });

        // Optionally, you can hide the current frame
        setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FacultyLoginForm().setVisible(true);
        });
    }
}

