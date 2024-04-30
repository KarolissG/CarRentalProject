package src.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener {

    // Declaration of navigation buttons and panels
    private JButton accountButton, reserveButton, returnButton, invoiceButton;
    private JPanel contentPanel, accountPanel, reservePanel, returnPanel, invoicePanel;

    public class ConnectionManager {
        private static Connection connection;

        // Method to establish the database connection
        public static Connection getConnection() {
            if (connection == null) {
                try {
                    // Connect to your database
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrental",
                            "root", "");
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            return connection;
        }

        // Method to close the database connection
        public static void closeConnection() {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static void openMainWindow() {
        Main system = new Main();
        system.setVisible(true);
    }

    // Constructor
    public Main() {
        // Set up the JFrame
        setTitle("Car Rental");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Navigation buttons
        accountButton = new JButton("Accounts");
        reserveButton = new JButton("Reserve");
        returnButton = new JButton("Orders");
        invoiceButton = new JButton("Invoice");

        // Add action listeners to the buttons
        accountButton.addActionListener(this);
        reserveButton.addActionListener(this);
        returnButton.addActionListener(this);
        invoiceButton.addActionListener(this);

        // Create a panel for navigation buttons
        JPanel navPanel = new JPanel(new GridLayout(4, 1));
        navPanel.add(accountButton);
        navPanel.add(reserveButton);
        navPanel.add(returnButton);
        navPanel.add(invoiceButton);

        // Create a content panel with CardLayout for switching between pages
        contentPanel = new JPanel(new CardLayout());
        accountPanel = new JPanel(); // Placeholder for account page
        reservePanel = new JPanel(); // Placeholder for reserve page
        returnPanel = new JPanel(); // Placeholder for return page
        invoicePanel = new JPanel(); // Placeholder for invoice page

        // Add placeholders to content panel with CardLayout
        contentPanel.add(accountPanel, "account");
        contentPanel.add(reservePanel, "reserve");
        contentPanel.add(returnPanel, "order");
        contentPanel.add(invoicePanel, "invoice");

        // Set layout of JFrame and add navigation panel and content panel
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(navPanel, BorderLayout.WEST);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                ConnectionManager.closeConnection();
            }
        });
    }

    
    /** 
     * @param event
     */
    // ActionListener implementation for button clicks
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == accountButton) {
            // Create and show the AccountGUI panel
            AccountGUI accountGUI = new AccountGUI();
            accountGUI.setVisible(true);
            dispose();

        } else if (event.getSource() == reserveButton) {
            VehicleTypeSelection vehicleSelect = new VehicleTypeSelection();
            vehicleSelect.setVisible(true);

        } else if (event.getSource() == returnButton) {
            ViewOrder viewOrder = new ViewOrder();
            viewOrder.setVisible(true);
            dispose();
        } else if (event.getSource() == invoiceButton) {
            ViewInvoice viewInvoice = new ViewInvoice();
            viewInvoice.setVisible(true);
            dispose();
        }

    }

    
    /** 
     * @param args
     */
    // Main method to start the application
    public static void main(String[] args) {
        if (Login.loggedIn) {
            SwingUtilities.invokeLater(() -> {
                Main system = new Main();
                system.setVisible(true);
            });
        } else {
            // Open the Login window
            Login Login = new Login();
            Login.setVisible(true);
            // Close the current window
        }

    }

}
