package HMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scn;

    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scn = scanner;
    }
    public void addPatient()
    {
        System.out.println("Enter Patient Name:");
        String name = scn.next();
        System.out.println("Enter Patient Age:");
        int age = scn.nextInt();
        System.out.println("Enter Patient Gender:");
        String gender = scn.next();

        try
        {
            String query = "INSERT INTO patients(name,age,gender) VALUES(?,?,?)";
            PreparedStatement ppst = connection.prepareStatement(query);
            ppst.setString(1,name);
            ppst.setInt(2,age);
            ppst.setString(3,gender);
            int affected_rows = ppst.executeUpdate();
            if(affected_rows>0)
            {
                System.out.println("Patient Entry Complete!");
            }
            else
            {
                System.out.println("Error! Failed Entry!");
            }
        }
        catch (SQLException sql)
        {sql.printStackTrace();}
    }
    public void viewPatients()
    {
        String query = "SELECT * FROM patients";
        try
        {
            PreparedStatement ppst = connection.prepareStatement(query);
            ResultSet rset = ppst.executeQuery();

            //Just A Tabular Format for Output!
            System.out.println("Patient Details:");
            System.out.println("+------------+--------------------------+-------------+----------------+");
            System.out.println("| Patient_ID | Patient_NAME             | Patient_AGE | Patient_GENDER |");
            System.out.println("+------------+--------------------------+-------------+----------------+");
            while(rset.next())
            {
                int i = rset.getInt("id");
                String n = rset.getString("name");
                int a = rset.getInt("age");
                String g = rset.getString("gender");
                System.out.printf("| %-10s | %-24s | %-11s | %-14s |",i,n,a,g);
                System.out.println("\n+------------+--------------------------+-------------+----------------+");
            }
        }
        catch(SQLException sql)
        {sql.printStackTrace();}
    }
    public boolean checkPatientByID(int id)
    {
        String query =  "SELECT * FROM patients WHERE id = ?";
        try
        {
            PreparedStatement ppst = connection.prepareStatement(query);
            ppst.setInt(1, id);
            ResultSet rset = ppst.executeQuery();
            //Just A Tabular Format for Output!
            System.out.println("Patient Details:");
            System.out.println("+------------+--------------------------+-------------+----------------+");
            System.out.println("| Patient_ID | Patient_NAME             | Patient_AGE | Patient_GENDER |");
            System.out.println("+------------+--------------------------+-------------+----------------+");
            if(rset.next()) //Since only single entry is fetched!
            {
                int i = rset.getInt("id");
                String n = rset.getString("name");
                int a = rset.getInt("age");
                String g = rset.getString("gender");
                System.out.printf("| %-10s | %-24s | %-11s | %-14s |",i,n,a,g);
                System.out.println("\n+------------+--------------------------+-------------+----------------+");
                return true;
            }
            else{return false;}
        }
        catch(SQLException sql)
        {sql.printStackTrace();}
        return false;
    }
}
