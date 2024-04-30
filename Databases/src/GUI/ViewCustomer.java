package src.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import src.GUI.Main.ConnectionManager;

public class ViewCustomer extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton exitButton; // New exit button
    private Connection connection = ConnectionManager.getConnection();

    public ViewCustomer() {
        setTitle("Customer Management");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);

        // Create table model
        DefaultTableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells uneditable
            }
        };

        // Create table and set model
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create delete button
        updateButton = new JButton("Update");
        updateButton.addActionListener(this::actionPerformed);

        // Create delete button
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this::actionPerformed);

        // Create exit button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this::actionPerformed);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3)); // GridLayout with 1 row and 2 columns
        buttonPanel.add(exitButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Add form panel and button panel to the frame's content pane
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Load data into the table
        loadData();

        setVisible(true);
    }

    
    /** 
     * @param event
     */
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == exitButton) {
            // Open the AccountGUI window
            AccountGUI accountGUI = new AccountGUI();
            accountGUI.setVisible(true);
            // Close the current ViewCustomer window
            dispose();
        } else if (event.getSource() == deleteButton) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(ViewCustomer.this, "Please select a row to delete.");
                return;
            }
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int customerId = (int) model.getValueAt(row, 0);
            String customerName = (String) model.getValueAt(row, 1); // Assuming name is in the second column

            // Display a confirmation dialog with customer ID and name
            int option = JOptionPane.showConfirmDialog(ViewCustomer.this,
                    "Are you sure you want to delete customer ID: " + customerId + ", Name: " + customerName + "?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Delete the customer
                deleteCustomer(customerId);
                // Remove the row from the table model
                model.removeRow(row);
                // Output to user that the customer has been deleted
                JOptionPane.showMessageDialog(ViewCustomer.this,
                        "Customer ID: " + customerId + ", Name: " + customerName + " deleted successfully.");
            }
        } else if (event.getSource() == updateButton) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(ViewCustomer.this, "Please select a row to update.");
                return;
            }
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int customerId = (int) model.getValueAt(row, 0);
            // Open the UpdateCustomer window with customerId
            UpdateCustomer updateCustomer = new UpdateCustomer(customerId);
            updateCustomer.setVisible(true);
            // Close the current ViewCustomer window
            dispose();
        }
    }

    private void loadData() {
        try {
            // Execute query to retrieve data
            ResultSet resultSet = src.CRUD2.CustomerCRUD.RetrieveTable(this.connection);

            // Get metadata
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // Create table model with column names
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(metaData.getColumnName(i));
            }

            // Add rows to the table
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
            // Close resources (resultSet will be closed when statement is closed)
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    
    /** 
     * @param customerId
     */
    private void deleteCustomer(int customerId) {
        // Call the DeleteCustomer method from CRUD2.CustomerCRUD
        src.CRUD2.CustomerCRUD.DeleteCustomer(this.connection, customerId);
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewCustomer();
            }
        });
    }
}
