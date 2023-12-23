/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_information_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

public class Main {

     public static void main(String[] args) {
        // Establish the database connection
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("Connected to the database.");

            // Display the Student Registration Form
            SwingUtilities.invokeLater(() -> {
                new JavaChipsAcademyFrame().setVisible(true);
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

