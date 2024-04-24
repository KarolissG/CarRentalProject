package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public Login() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for spacing
        add(loginButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Here you can add your authentication logic
            // For simplicity, let's just print the username and password
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
        } else if (e.getSource() == registerButton) {
            // Functuin if Register is pressed
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
