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


public class ManageEnrollment extends JFrame {
    
    JTextField studentId;
    JTextField yearLevel;
    JTextField degree;

    public ManageEnrollment() {
        setTitle("ENROLL YOUR STUDENT HERE");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(550, 320);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 2));
        
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentId = new JTextField();

        JLabel yearLevelLabel = new JLabel("Year Level:");
        yearLevel = new JTextField();
        JLabel degreeProgramLabel = new JLabel("Degree Program:");
        degree = new JTextField();

        JButton enroll = new JButton("Enroll");
        JButton drop = new JButton("drop");
        
       
        mainPanel.add(new JLabel());
        mainPanel.add(studentIdLabel);
        mainPanel.add(studentId);
        mainPanel.add(new JLabel()); // Placeholder
        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(yearLevelLabel);
        mainPanel.add(yearLevel);
        mainPanel.add(degreeProgramLabel);
        mainPanel.add(degree);
        mainPanel.add(new JLabel()); // Placeholder
        mainPanel.add(enroll);
        mainPanel.add(drop);

        add(mainPanel);
        
        // Center the frame on the screen
        setLocationRelativeTo(null);

        enroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You just enrolled your student!");
            }
        });

        drop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              JOptionPane.showMessageDialog(null, "Successfully dropped!");
            }
        });
    }
    
   
}


