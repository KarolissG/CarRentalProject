package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import CRUD2.OrderCRUD;
import GUI.Main.ConnectionManager;

public class UpdateOrder extends JFrame implements ActionListener {
    private JTextField rentDateField, daysField, pickedUpField,
            returnedField, datepickedUpField;
    private JList<String> regPlateList;
    private JButton updateButton;
    private JButton cancelButton;
    private Connection connection = ConnectionManager.getConnection();
    private int orderId;

    public UpdateOrder(int orderId) {
        this.orderId = orderId;
        setTitle("Update Order");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize list
        regPlateList = new JList<>();
        JScrollPane regScrollPane = new JScrollPane(regPlateList);

        // Create labels and text fields
        JLabel rentDateLabel = new JLabel("Rent Date: (yyyy-mm-dd)");
        JLabel daysLabel = new JLabel("Days Rented:");
        JLabel pickedUpLabel = new JLabel("Picked Up: (0/1)");
        JLabel returnedLabel = new JLabel("Returned: (0/1)");
        JLabel datepickedUpLabel = new JLabel("Date Picked Up: (yyyy-mm-dd)");
        JLabel regLabel = new JLabel("Registration:");

        rentDateField = new JTextField();
        daysField = new JTextField();
        pickedUpField = new JTextField();
        returnedField = new JTextField();
        datepickedUpField = new JTextField();

        // Create update button
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);

        // Create cancel button
        cancelButton = new JButton("Back");
        cancelButton.addActionListener(this);

        // Create panel for form components
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(rentDateLabel);
        formPanel.add(rentDateField);
        formPanel.add(daysLabel);
        formPanel.add(daysField);
        formPanel.add(pickedUpLabel);
        formPanel.add(pickedUpField);
        formPanel.add(returnedLabel);
        formPanel.add(returnedField);
        formPanel.add(datepickedUpLabel);
        formPanel.add(datepickedUpField);
        formPanel.add(regLabel);
        formPanel.add(regScrollPane);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(cancelButton);
        buttonPanel.add(updateButton);

        // Add form panel and button panel to the frame's content pane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(formPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Fill form with existing order data
        try {
            ResultSet resultSet = OrderCRUD.RetrieveOrder(connection, orderId);
            if (resultSet != null && resultSet.next()) {
                String rentDate = resultSet.getString("rentalDate");
                String days = resultSet.getString("daysRented");
                String pickedUp = resultSet.getString("ifPickedUp");
                String returned = resultSet.getString("ifReturned");
                String datepickedUp = resultSet.getString("datePickedUp");

                rentDateField.setText(rentDate);
                daysField.setText(days);
                pickedUpField.setText(pickedUp);
                returnedField.setText(returned);
                datepickedUpField.setText(datepickedUp);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        // Populate registration plates list
        populateRegistrationPlates();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            if (updateOrder()) {
                JOptionPane.showMessageDialog(this, "Order Updated");
            }
        } else if (e.getSource() == cancelButton) {
            ViewOrder viewOrder = new ViewOrder();
            viewOrder.setVisible(true);
            dispose();
        }
    }

    private void populateRegistrationPlates() {
        try {
            ResultSet resultSet = OrderCRUD.getRegistrationPlates(connection);
            DefaultListModel<String> model = new DefaultListModel<>();
            while (resultSet.next()) {
                String registration = resultSet.getString("regPlate");
                model.addElement(registration);
            }
            regPlateList.setModel(model);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    private boolean updateOrder() {
        String rentDate = rentDateField.getText();
        String days = daysField.getText();
        String pickedUp = pickedUpField.getText();
        String returned = returnedField.getText();
        String datepickedUp = datepickedUpField.getText();
        String registration = regPlateList.getSelectedValue(); // Get selected registration plate

        OrderCRUD.UpdateOrder(connection, orderId, rentDate, days, pickedUp, returned, datepickedUp, registration);
        return true;
    }

    public static void main(String[] args) {
        int orderId = 1;
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UpdateOrder(orderId);
            }
        });
    }
}
