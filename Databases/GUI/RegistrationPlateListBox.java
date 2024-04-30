package src.GUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import src.CRUD2.OrderCRUD;

public class RegistrationPlateListBox extends JList<String> {
    public RegistrationPlateListBox(Connection connection) {
        DefaultListModel<String> model = new DefaultListModel<>();
        try {
            ResultSet resultSet = OrderCRUD.getRegistrationPlates(connection);
            if (resultSet != null) {
                while (resultSet.next()) {
                    String regPlate = resultSet.getString("regPlate");
                    model.addElement(regPlate);
                }
            }
            setModel(model);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}