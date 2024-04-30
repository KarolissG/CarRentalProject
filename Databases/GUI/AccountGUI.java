package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AccountGUI extends JFrame implements ActionListener {
    private JButton manageCustomersButton;
    private JButton registerCustomerButton;
    private JButton exitButton; // New exit button

    public AccountGUI() {
        setTitle("Account Management System");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        manageCustomersButton = new JButton("Manage Customers");
        registerCustomerButton = new JButton("Register Customer");
        exitButton = new JButton("Exit"); // Initialize the exit button

        // Add action listeners
        manageCustomersButton.addActionListener(this);
        registerCustomerButton.addActionListener(this);
        exitButton.addActionListener(this); // Add action listener to the exit button

        // Set layout
        setLayout(new GridLayout(3, 1)); // Increased the number of rows to accommodate the new button

        // Add buttons to the frame
        add(manageCustomersButton);
        add(registerCustomerButton);
        add(exitButton); // Add the exit button to the frame

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                // Open the main window again
                Main.openMainWindow();
            }
        });
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == manageCustomersButton) {
            // Handle manage customers button click
            ViewCustomer viewCustomer = new ViewCustomer();
            viewCustomer.setVisible(true);
            dispose();
        } else if (event.getSource() == registerCustomerButton) {
            // Handle register customer button click
            RegisterCustomer registerCustomer = new RegisterCustomer();
            registerCustomer.setVisible(true);
            dispose();
        } else if (event.getSource() == exitButton) { // Check if the exit button is clicked
            // Open the main window again
            Main.openMainWindow();
            // Close the current window
            dispose();
        }
    }

    public static void main(String[] args) {
        // Run GUI on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AccountGUI();
            }
        });
    }
}
