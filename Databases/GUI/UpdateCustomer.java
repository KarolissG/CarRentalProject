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

public class UpdateCustomer extends JFrame implements ActionListener {
    private JTextField nameField, passwordField, eircodeField,
            phoneNoField, dobField, emailField, driverNumField,
            reviewField;
    private JButton updateButton;
    private JButton cancelButton;
    private Connection connection = ConnectionManager.getConnection();
    private int customerId; // Assuming customerId is declared as an instance variable

    public UpdateCustomer() {
        setTitle("Update Customer");
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

        // Create update button
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);

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
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(cancelButton);
        buttonPanel.add(updateButton);

        // Add form panel and button panel to the frame's content pane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            // Perform update process
            if (updateCustomer()) {
                JOptionPane.showMessageDialog(this, "Customer Updated");
            }
        } else if (e.getSource() == cancelButton) {
            dispose(); // Close the window if cancel button is clicked
        }
    }

    private boolean updateCustomer() {
        String name = nameField.getText();
        String password = passwordField.getText();
        String eircode = eircodeField.getText();
        String phoneNo = phoneNoField.getText();
        String dob = dobField.getText();
        String email = emailField.getText();
        String driverNum = driverNumField.getText();
        String review = reviewField.getText();

        // Validate input fields here...

        // Call the UpdateCustomer function
        CustomerCRUD.UpdateCustomer(connection, customerId, name, password, eircode, phoneNo, dob, email, driverNum, review);
        clearFormFields();
        return true; // Return true if update successful, handle error cases otherwise
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
                new UpdateCustomer();
            }
        });
    }
}
