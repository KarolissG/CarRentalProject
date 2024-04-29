package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import CRUD2.CustomerCRUD;
import GUI.Main.ConnectionManager;

public class Login extends JFrame implements ActionListener {
    public static Boolean loggedIn = false;
    public static String email; 
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private Connection connection = ConnectionManager.getConnection();

    public Login() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel(" Email:");
        JLabel passwordLabel = new JLabel(" Password:");

        emailField = new JTextField();
        passwordField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(registerButton);
        add(loginButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            // user passwrod check
            if (CustomerCRUD.LoginCheck(connection, email, password)) {
                loggedIn = true;
                this.email = email;
                Main Main = new Main();
                Main.setVisible(true);
                dispose();
            } else
                JOptionPane.showMessageDialog(this, "Username or Password incorrect ");

        } else if (e.getSource() == registerButton) {
            // Handle register customer button click
            RegisterCustomer registerCustomer = new RegisterCustomer();
            registerCustomer.setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
