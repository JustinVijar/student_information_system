import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        int confirmation = userDatabaseManager.loginUser(txtUsername.getText(), txtPassword.getPassword());
        System.out.println(confirmation);
        //TODO: Redirect SIS Form here
    }

    private void register() {
        System.out.println("Register");
        //TODO: Redirect Register Form here
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