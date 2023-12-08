import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



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
    private ButtonGroup studentFacultyGroup;
    private UserDatabaseManager databaseManager;

    public Register() {
        setContentPane(contentPane);
//        setModal(true);
        getRootPane().setDefaultButton(buttonRegister);

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

        String password = new String(passwordField.getPassword());
        String repeatPassword = new String(repeatPasswordField.getPassword());

        if (!password.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Repeat Password does not match!",
                    "Not Match",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean isFaculty = false; // Default value for non-selected

        // Check the selected radio button
        ButtonModel selectedButtonModel = studentFacultyGroup.getSelection();
        if (selectedButtonModel != null) {
            JRadioButton selectedButton = (JRadioButton) studentFacultyGroup.getElements().nextElement();
            if (selectedButton == facultyRadioButton) {
                isFaculty = true;
            }
        }

        User user = new User(
                txtUsername.getText(),
                new String(passwordField.getPassword()),
                isFaculty
        );

        Person person = new Person(
                txtFirstName.getText(),
                txtMiddleName.getText(),
                txtLastName.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText()
        );

        if (databaseManager.registerUser(user, person)) {
            JOptionPane.showMessageDialog(
                    this,
                    "REGISTRATION SUCCESS",
                    "SUCCESS",
                    JOptionPane.PLAIN_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "There was a problem on registration.\n" +
                            "Please make sure that:\n" +
                            "- Username is not taken\n" +
                            "- MySQL Connection is established",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
