import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {

        Connection connection = null;

        final String DATABASE_URL = "jdbc:mysql://localhost:3306/carrental"; // database URL
        final String user = "root"; // database username
        Scanner UserIn = new Scanner(System.in);

        try {

            connection = DriverManager.getConnection(DATABASE_URL, user, ""); // connect
            System.out.println("");

            boolean exit = false;
            while (!exit) {
                System.out.print("\nWould you like to \n1.view\n2.ammend\n3.update\n4.delete\n: ");
                int input = UserIn.nextInt();
                Scanner TableIn = new Scanner(System.in);
                exit = true;
                switch (input) {
                    case 1:
                        databaseCRUD.ShowTables(DATABASE_URL, user, "SHOW TABLES"); // output database tables
                        System.out.print("\nWhich Table would you like to look at \n: ");
                        String query = "SELECT * FROM ";
                        String table = TableIn.nextLine();
                        databaseCRUD.RetrieveTable(connection, query + table); // select table to retrieve
                        break;
                    case 2:
                    while(exit){
                        databaseCRUD.ShowTables(DATABASE_URL, user,
                                "SELECT table_name FROM information_schema.tables WHERE table_schema = 'carrental' AND table_name IN ('customer', 'vehicle', 'reservation');");
                        System.out.print("\nWhich Table would you like to Ammend \n: ");
                        switch (UserIn.nextLine()) {
                            case "customer":
                                databaseCRUD.AmmendCustomer(connection);
                                break;

                            case "vehicle":
                                break;
                            
                            case "reservation":
                                break;

                            default:
                                System.out.println("Invalid Input");
                        }

                        break;
                    }
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Invalid Input");
                        exit = false;
                }
            }
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                connection.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}