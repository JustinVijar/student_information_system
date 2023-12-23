/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_information_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaChipsAcademyFrame extends JFrame {

    public JavaChipsAcademyFrame() {
        setTitle("JavaChips Academy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        createComponents();
        addComponentsToPanel();
        
         // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        JLabel titleLabel = new JLabel("JavaChips Academy", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
       
        
        JButton registerStudentButton = new JButton("Register as Student");
        JButton registerFacultyButton = new JButton("Register as Faculty");
        JButton loginStudentButton = new JButton("Login as Student");
        JButton loginFacultyButton = new JButton("Login as Faculty");

        // Add action listeners to the buttons
        registerStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStudentRegistrationForm();
            }
        });
        
        registerFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFacultyRegistrationForm();
            }
        });
        //login
        loginStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStudentLoginForm();
            }
        });
        
        loginFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFacultyLoginForm();
            }
        });
        
        

        // Add action listeners for other buttons...

        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.add(titleLabel);
        panel.add(registerStudentButton);
        panel.add(registerFacultyButton);
        panel.add(loginStudentButton);
        panel.add(loginFacultyButton);

        add(panel);
    }

    private void addComponentsToPanel() {
        // Add components to the panel if needed
    }

    private void openStudentRegistrationForm() {
        // Open the StudentRegistrationForm
        SwingUtilities.invokeLater(() -> {
            new StudentRegistrationForm().setVisible(true);
        });

        // Optionally, you can hide the current frame
        setVisible(false);
    }
    
    private void openFacultyRegistrationForm() {
        // Open the StudentRegistrationForm
        SwingUtilities.invokeLater(() -> {
            new FacultyRegistrationForm().setVisible(true);
        });

        // Optionally, you can hide the current frame
        setVisible(false);
    }

    private void openStudentLoginForm() {
        // Open the StudentRegistrationForm
        SwingUtilities.invokeLater(() -> {
            new StudentLoginForm().setVisible(true);
        });

        // Optionally, you can hide the current frame
        setVisible(false);
    }
    
    private void openFacultyLoginForm() {
        // Open the StudentRegistrationForm
        SwingUtilities.invokeLater(() -> {
            new FacultyLoginForm().setVisible(true);
        });

        // Optionally, you can hide the current frame
        setVisible(false);
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JavaChipsAcademyFrame().setVisible(true);
        });
    }
}

