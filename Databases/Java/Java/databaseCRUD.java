import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class databaseCRUD {
    static Connection connection = null;
    static ResultSet resultSet = null;
    static PreparedStatement pstat = null;
    static PreparedStatement tempPstat = null;

    public static void ShowTables(String DB_URL, String user, String query) {
        try {
            connection = DriverManager.getConnection(DB_URL, user, ""); // connect
            pstat = connection.prepareStatement(query); // sql query
            resultSet = pstat.executeQuery(); // executes the query
            System.out.println("--Tables in Database--");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)); // output tables
            }
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }// method

    public static void RetrieveTable(Connection connectionIn, String query) {
        try {
            connection = connectionIn; // connect
            pstat = connection.prepareStatement(query); // sql query
            resultSet = pstat.executeQuery(); // executes the query

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            System.out.println("--Table Details--");
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.print(metaData.getColumnName(i) + "\t");// output tables

            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.print(resultSet.getObject(i) + "\t");
                System.out.println();
            }
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }// method

    public static void AmmendItem(Connection connectionIn, String tableIN) {

        try {
            connection = connectionIn; // connect
            pstat = connection.prepareStatement(" " + tableIN); // sql query
            resultSet = pstat.executeQuery(); // executes the query

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            System.out.println("--Table Details--");
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.print(metaData.getColumnName(i) + "\t");// output tables

            System.out.println();
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.print(resultSet.getObject(i) + "\t");
                System.out.println();
            }

        }

        catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void AmmendCustomer(Connection connectionIn) {
        Scanner dataIn = new Scanner(System.in);
        try {
            connection = connectionIn; // connect
            String query = "INSERT INTO customer (name, password, eircode, phoneNo, DOB, email, driverNum, review) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstat = connection.prepareStatement(query); // sql query
            System.out.print("Enter Name \n: ");
            pstat.setString(1, dataIn.nextLine());

            System.out.print("Enter password \n: ");
            pstat.setString(2, dataIn.nextLine());

            System.out.print("Enter Eircode varchar(7)\n: ");
            pstat.setString(3, dataIn.nextLine());

            System.out.print("Enter Phone Number varchar(15)\n: ");
            pstat.setString(4, dataIn.nextLine());

            System.out.print("Enter DOB (YYYY-MM-DD): ");
            String dobInput = dataIn.nextLine();

            Date dob = Date.valueOf(dobInput);
            pstat.setDate(5, dob); // Set the DOB parameter

            System.out.print("Enter email\n: ");
            pstat.setString(6, dataIn.nextLine());

            System.out.print("Enter driverNum varchar(10)\n: ");
            pstat.setString(7, dataIn.nextLine());

            System.out.print("Enter review 1-5\n: ");
            pstat.setString(8, dataIn.nextLine());

            int i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the table.");
        }

        catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void AmmendVehicle(Connection connectionIn) {
        Scanner dataIn = new Scanner(System.in);
        try {
            connection = connectionIn; // connect
            String query = "INSERT INTO vehicle (regPlate, brand, locationID, available, engineSize, insuranceRate, model) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstat = connection.prepareStatement(query); // sql query
            System.out.print("Enter regPlate varchar(15) \n: ");
            pstat.setString(1, dataIn.nextLine());

            System.out.print("Enter brand \n: ");
            pstat.setString(2, dataIn.nextLine());

            System.out.print("LocationID 1-7\n: ");
            pstat.setString(3, dataIn.nextLine());

            pstat.setString(4, "1");

            System.out.print("Enter engineSize\n: ");
            pstat.setString(5, dataIn.nextLine());

            System.out.print("Enter insuranceRate\n: ");
            pstat.setString(6, dataIn.nextLine());

            System.out.print("Enter Model\n: ");
            pstat.setString(7, dataIn.nextLine());

            int i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the table.");
        }

        catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void AmmendReservation(Connection connectionIn) {
        Scanner dataIn = new Scanner(System.in);
        try {
            connection = connectionIn; // connect
            String query = "INSERT INTO reservation (rentalDate, daysRented, ifPickedUp, ifReturned, datePickedUp, regPlate) VALUES (?, ?, ?, ?, ?, ?)";
            tempPstat = connection.prepareStatement(
                    "SELECT v.regPlate FROM vehicle v LEFT JOIN reservation r ON v.regPlate = r.regPlate WHERE r.regPlate IS NULL;\r\n");
            pstat = connection.prepareStatement(query); // sql query
            System.out.print("Enter Rental Date (YYYY-MM-DD): ");
            String dobInput = dataIn.nextLine();

            Date date = Date.valueOf(dobInput);
            pstat.setDate(1, date); // Set the DOB parameter

            System.out.print("Days Rented For \n: ");
            pstat.setString(2, dataIn.nextLine());

            pstat.setBoolean(3, false);

            pstat.setBoolean(4, false);

            pstat.setNull(5, java.sql.Types.DATE);

            resultSet = tempPstat.executeQuery(); // executes the query
            System.out.println("--Available Vehicles--");
            while (resultSet.next()) {
                // Retrieve the regPlate value from the current row
                String regPlate = resultSet.getString("regPlate");

                System.out.println(regPlate);
            }

            System.out.print("Enter a RegPlate\n: ");
            pstat.setString(6, dataIn.nextLine());

            int i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the table.");
        }

        catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                resultSet.close();
                pstat.close();
                tempPstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    public static void UpdateCustomer(Connection connectionIn, String ID) {
        Scanner dataIn = new Scanner(System.in);
        try {
            connection = connectionIn;
            String query = "UPDATE customer SET ";
            System.out.println("Would you like to update the name y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter Name \n: ");
                query = query + "name =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the password y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter Password \n: ");
                query = query + "password =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the Eircode y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter Eircode \n: ");
                query = query + "eircode =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the phone number y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter Number varchar(15)\n: ");
                query = query + "password =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the DOB y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter DOB (YYYY-MM-DD)\n: ");
                query = query + "DOB =" + Date.valueOf(dataIn.next());
            }

            System.out.println("Would you like to update the email y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter email \n: ");
                query = query + "email =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the driverNum y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter driverNum varchar(10)\n: ");
                query = query + "driverNum =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the review y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter review 1-5\n: ");
                query = query + "review =" + dataIn.nextInt();
            }
            query = query + " WHERE customerID = " + ID;
            int i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the table.");
        }

        catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    public static void UpdateVehicle(Connection connectionIn, String ID) {
        Scanner dataIn = new Scanner(System.in);
        try {
            connection = connectionIn;
            String query = "UPDATE vehicle SET ";
            System.out.println("Would you like to update the brand y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter brand \n: ");
                query = query + "brand =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the model y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter Model \n: ");
                query = query + "model =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the locationID y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter locationID 1-7 \n: ");
                query = query + "locationID =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the availability y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter true/false\n: ");
                query = query + "available =" + dataIn.nextBoolean();
            }

            System.out.println("Would you like to update the insuranceRate y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter Rate \n: ");
                query = query + "insuranceRate =" + dataIn.nextLine();
            }

            System.out.println("Would you like to update the engineSize y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter engineSize\n: ");
                query = query + "engineSize =" + dataIn.nextLine();
            }

            query = query + " WHERE regPlate = " + ID;
            int i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the table.");
        }

        catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    public static void UpdateReservation(Connection connectionIn, String ID) {
        Scanner dataIn = new Scanner(System.in);
        try {
            connection = connectionIn;
            String query = "UPDATE reservation SET ";
            System.out.println("Would you like to update the rentalDate y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter Date (YYYY-MM-DD)\n: ");
                query = query + "rentalDate =" + Date.valueOf(dataIn.next());
            }

            System.out.println("Would you like to update the daysRented y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter daysRented \n: ");
                query = query + "daysRented =" + dataIn.nextInt();
            }

            System.out.println("Would you like to update the ifPickedUp y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter true/false\n: ");
                query = query + "ifPickedUp =" + dataIn.nextBoolean();
            }
            System.out.println("Would you like to update the ifReturned y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter true/false\n: ");
                query = query + "ifReturned =" + dataIn.nextBoolean();
            }

            System.out.println("Would you like to update the DatePickedUp y/n");
            if (dataIn.nextLine() == "y") {
                System.out.print("Enter Date (YYYY-MM-DD)\n: ");
                query = query + "datePickedUp =" + Date.valueOf(dataIn.next());
            }

            System.out.println("Would you like to update the regPlate y/n");
            if (dataIn.nextLine() == "y") {
                System.out.println("--Available Vehicles--");
                while (resultSet.next()) {
                    // Retrieve the regPlate value from the current row
                    String regPlate = resultSet.getString("regPlate");

                    System.out.println(regPlate);
                }
                System.out.print("Enter regPlate\n: ");
                query = query + "regPlate =" + dataIn.nextLine();
            }

            query = query + " WHERE reservationID = " + ID;
            int i = pstat.executeUpdate();
            System.out.println(i + " record successfully updated in the table.");
        }

        catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void DeleteItem(Connection connectionIn, String table, String ID) {
        try {
            connection = connectionIn;
            String query = "DELETE FROM " + table + " WHERE ";
            pstat = connection.prepareStatement("SELECT * FROM " + table);
            resultSet = pstat.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            query = query + metaData.getColumnName(1) + " = " + ID;

            int i = pstat.executeUpdate();
            System.out.println(i + " record successfully deleted in the table.");
        } catch (SQLException sqlException) { // catch errors
            sqlException.printStackTrace();
        } finally {
            try {// close all connections to database and get errors
                pstat.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }
}// class