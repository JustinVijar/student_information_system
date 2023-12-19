import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Objects;


public class Register extends JDialog{
    private JPanel contentPane;
    private JButton buttonRegister;
    private JButton buttonCancel;
    private JPanel mainPanel;
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JTextField txtPhoneNumber;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JRadioButton studentRadioButton;
    private JRadioButton facultyRadioButton;
    private JTextField txtFirstName;
    private JTextField txtMiddleName;
    private JTextField txtLastName;
    private JButton checkUsernameButton;
    private JLabel lbldoesUsernameExist;
    private ButtonGroup studentFacultyGroup;
    private UserDatabaseManager databaseManager;

    public Register() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonRegister);

        checkUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (Objects.equals(txtUsername.getText(), "")) {
                        return;
                    }

                    if (databaseManager.retrieveUser(txtUsername.getText()) == null) {
                        lbldoesUsernameExist.setText("Username already exist");
                    } else {
                        lbldoesUsernameExist.setText("Username available");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRegsiter();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        Utilities utilities = new Utilities() {
            @Override
            void performActionEnter() {
                onRegsiter();
            }
        };
        utilities.actionEnter(mainPanel);
        Utilities.centerFrameOnScreen(this);

        databaseManager = new UserDatabaseManager(Utilities.databaseUrl, Utilities.user, Utilities.pass);

    }

    private void onRegsiter() {
        executeRegistration();

    }

    private User getUser(){
        boolean isFaculty = facultyRadioButton.isSelected();
        return new User(
                txtUsername.getText(),
                new String(passwordField.getPassword()),
                isFaculty
        );

    }

    private Person getPerson() {

        return new Person(
                txtFirstName.getText(),
                txtMiddleName.getText(),
                txtLastName.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText()
        );
    }

    private void executeRegistration(){
        String password = new String(passwordField.getPassword());
        String repeatPassword = new String(repeatPasswordField.getPassword());

        User user = getUser();
        Person person = getPerson();

        if (!password.equals(repeatPassword)) {
            JOptionPane.showConfirmDialog(
                    this,
                    "Repeat Password does not match!",
                    "Not Match",
                    JOptionPane.DEFAULT_OPTION
            );
            return;
        }

        if (user.getPassword().length() < 8) {
            JOptionPane.showConfirmDialog(
                    this,
                    "Password Length must be greater than 8",
                    "Password Length Invalid",
                    JOptionPane.DEFAULT_OPTION
            );
            return;
        }

        if (person.getContactNumber().length() != 10 && person.getContactNumber().length() != 11) {
            JOptionPane.showConfirmDialog(
                    this,
                    "Contact number length is invalid",
                    "Contact Number Invalid",
                    JOptionPane.DEFAULT_OPTION
            );
            return;
        }

        for (char c : person.getContactNumber().toCharArray()) {
            if (!Character.isDigit(c)) {
                JOptionPane.showConfirmDialog(
                        this,
                        "Contact number must only have letters",
                        "Contact Number Invalid",
                        JOptionPane.DEFAULT_OPTION
                );
                return;
            }
        }

        if (!person.getEmail().contains("@")) {
            JOptionPane.showConfirmDialog(
                    this,
                    "Email format is invalid",
                    "Email Invalid",
                    JOptionPane.DEFAULT_OPTION
            );
            return;
        }

        if (person.getFirstName().equals("") || person.getLastName().equals("") || user.getUsername().equals("")) {
            JOptionPane.showConfirmDialog(
                    this,
                    "Please fill up necessary fields",
                    "Empty Fields",
                    JOptionPane.DEFAULT_OPTION
            );
            return;
        }

        try {
            if (databaseManager.registerUserPerson(user, person)) {
                JOptionPane.showMessageDialog(
                        this,
                        "REGISTRATION SUCCESS",
                        "SUCCESS",
                        JOptionPane.PLAIN_MESSAGE
                );
            } else {
                JOptionPane.showConfirmDialog(
                        this,
                        "There was a problem on registration.\n" +
                                "Please make sure that the username is not taken",
                        "ERROR",
                        JOptionPane.DEFAULT_OPTION
                );
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "There was a problem on registration.\n" +
                            "ERROR: " + e,
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
            dispose();
        }

        dispose();
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {

    }

}
