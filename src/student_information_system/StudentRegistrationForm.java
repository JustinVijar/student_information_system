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
import java.sql.SQLException;

public class StudentRegistrationForm extends JFrame {

    private JTextField studentIdField, firstNameField, middleNameField, lastNameField, contactNumberField, emailField;
    private JButton registerButton, backButton;
    private JLabel titleLabel;
    private JPasswordField passwordField;

    public StudentRegistrationForm() {
        setTitle("Student Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        createComponents();
        addComponentsToPanel();
        attachListeners();
        
         // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        studentIdField = new JTextField(10);
        firstNameField = new JTextField(10);
        middleNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        contactNumberField = new JTextField(10);
        emailField = new JTextField(10);
        passwordField = new JPasswordField(10);

        registerButton = new JButton("Register");
        backButton = new JButton("<");
        
        titleLabel = new JLabel("Student Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private void addComponentsToPanel() {
        JPanel panel = new JPanel(new GridLayout(11, 2));
        
        // Add the back button
        panel.add(backButton);
        
         // Add the title label
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(titleLabel);

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);

        panel.add(new JLabel("First Name:"));
        panel.add(firstNameField);

        panel.add(new JLabel("Middle Name:"));
        panel.add(middleNameField);

        panel.add(new JLabel("Last Name:"));
        panel.add(lastNameField);

        panel.add(new JLabel("Contact Number:"));
        panel.add(contactNumberField);

        panel.add(new JLabel("Email:"));
        panel.add(emailField);
                
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        
        panel.add(new JLabel(""));
        panel.add(registerButton);
        

        add(panel);
    }

    private void attachListeners() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStudent();
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

        // Close the current frame (StudentRegistrationForm)
        setVisible(false);
    }

    private void registerStudent() {
        try (Connection connection = DatabaseConnection.getConnection()) {
        String sql = "INSERT INTO student (student_id, first_name, middle_name, last_name, contact_number, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, Integer.parseInt(studentIdField.getText()));
                preparedStatement.setString(2, firstNameField.getText());
                preparedStatement.setString(3, middleNameField.getText());
                preparedStatement.setString(4, lastNameField.getText());
                preparedStatement.setString(5, contactNumberField.getText());
                preparedStatement.setString(6, emailField.getText());
                preparedStatement.setString(7, new String(passwordField.getPassword()));


                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student registered successfully!");
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Error registering student.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        studentIdField.setText("");
        firstNameField.setText("");
        middleNameField.setText("");
        lastNameField.setText("");
        contactNumberField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentRegistrationForm().setVisible(true);
        });
    }
}
