import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends Utilities{
    private JPanel mainPanel;
    private JPasswordField txtPassword;
    private JTextField txtUsername;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblLogin;

    public Login(JFrame frame) {
        frame.setTitle("Login");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        actionLoginButton();
        actionRegisterButton();

        actionEnter(mainPanel);
        centerFrameOnScreen(frame);
    }

    @Override
    public void performActionEnter() {
        login();
    }

    private void login() {
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager(Utilities.databaseUrl, Utilities.user, Utilities.pass);
        int confirmation = 0;
        try {
            confirmation = userDatabaseManager.loginUser(txtUsername.getText(), txtPassword.getPassword());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this.mainPanel, "An error occured:\n" + e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        if (confirmation == -1) {
            JOptionPane.showMessageDialog(
                    this.mainPanel,
                    "Username or Password is invalid.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        System.out.println(confirmation);
        //TODO: Redirect SIS Form here
    }

    private void register() {
        Register dialog = new Register();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void actionLoginButton() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void actionRegisterButton() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
    }

}