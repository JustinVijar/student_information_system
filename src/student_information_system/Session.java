/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Session.java
package student_information_system;

public class Session {
    private static Session instance;
    private int currentStudentId;

    private Session() {
        // Private constructor to enforce singleton pattern
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public int getCurrentStudentId() {
        return currentStudentId;
    }

    public void setCurrentStudentId(int studentId) {
        currentStudentId = studentId;
    }
}
