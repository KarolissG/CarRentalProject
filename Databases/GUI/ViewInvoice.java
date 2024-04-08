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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import GUI.Main.ConnectionManager;

public class ViewInvoice extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private JButton exitButton;
    private Connection connection = ConnectionManager.getConnection();

    public ViewInvoice() {
        setTitle("Invoices");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        // Create exit button
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this::actionPerformed);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 1)); // GridLayout with 1 row and 2 columns
        buttonPanel.add(exitButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        loadData();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == exitButton) {
            // Open the Main window
            Main main = new Main();
            main.setVisible(true);
            // Close the current window
            dispose();
        }
    }

    private void loadData() {
        try {
            // Execute query to retrieve data
            ResultSet resultSet = CRUD2.InvoiceCRUD.RetrieveTable(this.connection);

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
                new ViewInvoice();
            }
        });
    }
}
