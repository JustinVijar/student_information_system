/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javachips.studentinformationsystem;

/**
 *
 * @author psg420
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public abstract class Utilities {

    // Put the databaseUrl, user and pass in here
    public static final String databaseUrl = "jdbc:mysql://localhost:3306/student_info_sys",
                                user = "root",
                                pass = "secret";


    static void centerFrameOnScreen(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2 - frame.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2 - frame.getHeight() / 2;
        frame.setLocation(centerX, centerY);
    }

}