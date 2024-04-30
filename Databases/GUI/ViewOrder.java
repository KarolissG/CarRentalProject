package GUI;

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

import GUI.Main.ConnectionManager;

public class ViewOrder extends JFrame {

    private JTable table;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton exitButton;
    private Connection connection = ConnectionManager.getConnection();

    public ViewOrder() {
        setTitle("Reservation Management");
        setSize(750, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false; // Make uneditable
            }
        };

        table = new JTable(model);

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Create buttons
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this::actionPreformed);
        updateButton = new JButton("Update");
        updateButton.addActionListener(this::actionPreformed);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this::actionPreformed);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Load data into the table
        loadData();

        setVisible(true);
    }

    
    /** 
     * @param event
     */
    public void actionPreformed(ActionEvent event) {
        if (event.getSource() == exitButton) {
            // Open the Main window
            Main main = new Main();
            main.setVisible(true);
            // Close the current window
            dispose();
        } else if (event.getSource() == deleteButton) {
            // Delete
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(ViewOrder.this, "Please select a row to delete.");
                return;
            }
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int reservationId = (int) model.getValueAt(row, 0);
            // Display a confirmation dialog
            int option = JOptionPane.showConfirmDialog(ViewOrder.this,
                    "Are you sure you want to delete reservation ID: " + reservationId + "?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Delete the customer
                if (CRUD2.OrderCRUD.DeleteOrder(this.connection, reservationId) != 1) {
                    JOptionPane.showMessageDialog(ViewOrder.this, "Error Deleting Order");
                } else {
                    // Remove the row from the table model
                    model.removeRow(row);
                    // Output to user that the customer has been deleted
                    JOptionPane.showMessageDialog(ViewOrder.this,
                            "Invoice ID: " + reservationId + " deleted successfully.");
                }
            }
        } else if (event.getSource() == updateButton) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(ViewOrder.this, "Please select a row to update.");
                return;
            }
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int reservationId = (int) model.getValueAt(row, 0);
            // Open the UpdateOrder window with OrderId
            UpdateOrder updateOrder = new UpdateOrder(reservationId);
            updateOrder.setVisible(true);
            // Close the current ViewOrder window
            dispose();
        }
    }

    private void loadData() {
        try {
            // Execute query to retrieve data
            ResultSet resultSet = CRUD2.OrderCRUD.RetrieveTable(this.connection);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewOrder();
            }
        });
    }
}
