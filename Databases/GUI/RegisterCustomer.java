package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CRUD2.CustomerCRUD;
import GUI.Main.ConnectionManager;

public class RegisterCustomer extends JFrame implements ActionListener {
    private JTextField nameField, passwordField, eircodeField,
            phoneNoField, dobField, emailField, driverNumField,
            reviewField;
    private JButton registerButton;
    private JButton cancelButton; // New cancel button
    private Connection connection = ConnectionManager.getConnection();

    public RegisterCustomer() {
        setTitle("Customer Registration");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create labels and text fields
        JLabel nameLabel = new JLabel("Name:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel eircodeLabel = new JLabel("Eircode: (7)");
        JLabel phoneNoLabel = new JLabel("Phone Number: (15)");
        JLabel dobLabel = new JLabel("Date of Birth: (yyyy-mm-dd)");
        JLabel emailLabel = new JLabel("Email:");
        JLabel driverNumLabel = new JLabel("Driver Number: (10)");
        JLabel reviewLabel = new JLabel("Review: (1-5)");

        nameField = new JTextField();
        passwordField = new JTextField();
        eircodeField = new JTextField();
        phoneNoField = new JTextField();
        dobField = new JTextField();
        emailField = new JTextField();
        driverNumField = new JTextField();
        reviewField = new JTextField();

        // Create register button
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        // Create cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        // Create panel for form components
        JPanel formPanel = new JPanel(new GridLayout(10, 2));
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(eircodeLabel);
        formPanel.add(eircodeField);
        formPanel.add(phoneNoLabel);
        formPanel.add(phoneNoField);
        formPanel.add(dobLabel);
        formPanel.add(dobField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(driverNumLabel);
        formPanel.add(driverNumField);
        formPanel.add(reviewLabel);
        formPanel.add(reviewField);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2)); // GridLayout with 1 row and 2 columns
        buttonPanel.add(cancelButton);
        buttonPanel.add(registerButton);

        // Add form panel and button panel to the frame's content pane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    
    /** 
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            // Perform registration process
            if (registerCustomer()) {
                JOptionPane.showMessageDialog(this, "Customer Created ");
            }

        } else if (e.getSource() == cancelButton) {
            if (Login.loggedIn) {
                // Open the AccountGUI window
                AccountGUI accountGUI = new AccountGUI();
                accountGUI.setVisible(true);
                // Close the current ViewCustomer window
                dispose();
            } else {
                // Open the Login window
                Login Login = new Login();
                Login.setVisible(true);
                // Close the current window
                dispose();
            }
        }
    }

    private boolean registerCustomer() {
        String name = nameField.getText();
        String password = passwordField.getText();
        String eircode = eircodeField.getText();
        String phoneNo = phoneNoField.getText();
        String dob = dobField.getText();
        String email = emailField.getText();
        String driverNum = driverNumField.getText();
        String review = reviewField.getText();

        if (name.isEmpty() || password.isEmpty() || eircode.isEmpty() || phoneNo.isEmpty()
                || dob.isEmpty() || email.isEmpty() || driverNum.isEmpty() || review.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required. Please fill in all fields.");
            return false;
        }

        // Validation for email format
        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
            return false;
        }

        // Validation for lengths of certain fields
        if (phoneNo.length() > 15) {
            JOptionPane.showMessageDialog(this, "Please enter a valid phone number (15 digits).");
            return false;
        }
        if (driverNum.length() > 10) {
            JOptionPane.showMessageDialog(this, "Please enter a valid driver number (10 digits).");
            return false;
        }

        // Validation for date format (yyyy-mm-dd)
        if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Please enter the date of birth in the format yyyy-mm-dd.");
            return false;
        }

        // Call the RegisterCustomer function
        CustomerCRUD.RegisterCustomer(connection, name, password, eircode, phoneNo, dob, email, driverNum, review);
        clearFormFields();
        return true;
    }

    private void clearFormFields() {
        nameField.setText("");
        passwordField.setText("");
        eircodeField.setText("");
        phoneNoField.setText("");
        dobField.setText("");
        emailField.setText("");
        driverNumField.setText("");
        reviewField.setText("");
    }

    public static void main(String[] args) {
        // Run GUI on the event dispatch thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegisterCustomer();
            }
        });
    }
}
