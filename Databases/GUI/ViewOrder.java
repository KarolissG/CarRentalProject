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
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import GUI.Main.ConnectionManager;

public class ViewOrder extends JFrame {

    private JTable table;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton exitButton;
    private Object[] rawData;
    private Connection connection = ConnectionManager.getConnection();

    public ViewOrder() {
        setTitle("Reservation Management");
        setSize(750, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Make only column 0 uneditable
            }
        };

        table = new JTable(model);

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    loadData();
                    if (selectedRow != -1) {
                        // When selected
                        RowSelection(selectedRow);

                    }
                }
            }

            private Object[] RowSelection(int selectedRow) {
                Object[] rowData = new Object[table.getColumnCount()];
                // Iterate over each column in the selected row
                for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
                    // Get the value of the cell at the selected row and current column
                    Object cellValue = table.getValueAt(selectedRow, columnIndex);
                    // Store the cell value in the rowData array
                    rowData[columnIndex] = cellValue;
                }
                return rowData;
            }
        });
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

    public void actionPreformed(ActionEvent event) {
        if (event.getSource() == exitButton) {
            // Open the Main window
            Main main = new Main();
            main.setVisible(true);
            // Close the current window
            dispose();
        } else if (event.getSource() == updateButton) {
            JOptionPane.showMessageDialog(ViewOrder.this, "(origional) - ");
        } else if (event.getSource() == deleteButton) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(ViewOrder.this, "Please select a row to delete.");
                return;
            }
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int reservationId = (int) model.getValueAt(row, 0);
            // Display a confirmation dialog with customer ID and name
            int option = JOptionPane.showConfirmDialog(ViewOrder.this,
                    "Are you sure you want to delete reservation ID: " + reservationId + "?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                // Delete the customer
                CRUD2.OrderCRUD.DeleteOrder(this.connection, reservationId);
                // Remove the row from the table model
                model.removeRow(row);
                // Output to user that the customer has been deleted
                JOptionPane.showMessageDialog(ViewOrder.this,
                        "Reservation ID: " + reservationId + " deleted successfully.");
            }
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

    private void updateAction() {
        // Implement update action here
        JOptionPane.showMessageDialog(this, "Update action is not yet implemented.");
    }

    private void exitAction() {
        // Open the Main window
        Main Main = new Main();
        Main.setVisible(true);
        // Close the current ViewOrder window
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ViewOrder();
            }
        });
    }
}
