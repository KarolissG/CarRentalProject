package CRUD2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class InvoiceCRUD {
    static Connection connection = null;
    static ResultSet resultSet = null;
    static PreparedStatement pstat = null;
    static PreparedStatement tempPstat = null;

    public static ResultSet RetrieveTable(Connection connectionIn) {
        try {
            pstat = connectionIn.prepareStatement("SELECT * FROM invoice"); // sql query
            resultSet = pstat.executeQuery(); // executes the query

            return resultSet;
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        }
        return null;
    }// method
}
