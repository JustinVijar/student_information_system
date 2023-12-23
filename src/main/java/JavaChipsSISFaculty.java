/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_information_system;

import task3.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaChipsSISFaculty extends JFrame {

    public JavaChipsSISFaculty() {
        setTitle("JavaChips Academy - Faculty");
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

        // Add a welcome message
        JLabel welcomeLabel = new JLabel("Welcome back, faculty member!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        informationPanel.add(welcomeLabel, BorderLayout.NORTH);

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
        JButton manageEnrollmentButton = new JButton("Manage Student Enrollment");
        JButton createSectionsButton = new JButton("Create Course Sections");
        JButton inputGradesButton = new JButton("Input Grades");
        JButton editStudentButton = new JButton("Edit Student");
        JLabel text = new JLabel("                                      ");
        JButton logoutButton = new JButton("Log out");

        navigationPanel.add(homeButton);
        navigationPanel.add(manageEnrollmentButton);
        navigationPanel.add(createSectionsButton);
        navigationPanel.add(inputGradesButton);
        navigationPanel.add(editStudentButton);
        navigationPanel.add(text);
        navigationPanel.add(logoutButton);

        // Add action listeners for the buttons
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new JavaChipsSISFaculty().setVisible(true);
                });

                // Optionally, you can hide the current frame
                setVisible(false);
            }
        });

        manageEnrollmentButton.addActionListener(new ActionListener() {
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

        createSectionsButton.addActionListener(new ActionListener() {
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
                     CreateCourse createCourseFrame = new CreateCourse();
                     createCourseFrame.setVisible(true);
            }
        });

        inputGradesButton.addActionListener(new ActionListener() {
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
                InputGrades inputgrades = new InputGrades();
                inputgrades.setVisible(true);
            }
        });
        
        editStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditStudentFrame().setVisible(true);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JavaChipsSISFaculty().setVisible(true);
        });
    }
}


