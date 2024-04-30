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

    
    /** 
     * @param connectionIn
     * @return ResultSet
     */
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

    public static int DeleteInvoice(Connection connectionIn, int ID) {
        try {
            String query = "DELETE FROM invoice WHERE invoiceID = ?";
            pstat = connectionIn.prepareStatement(query);
            pstat.setInt(1, ID);
            int i = pstat.executeUpdate();
            return i;
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
            return -1;
        }
    }
}
