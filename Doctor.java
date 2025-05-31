package HMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    private Connection connection;


    public Doctor(Connection connection){
        this.connection = connection;
    }
    //Doctor Entry is Done by Database Admin! Directly.
    public void viewDoctors()
    {
        String query = "SELECT * FROM doctors";
        try
        {
            PreparedStatement ppst = connection.prepareStatement(query);
            ResultSet rset = ppst.executeQuery();

            //Just A Tabular Format for Output!
            System.out.println("Doctor Details:");
            System.out.println("+-----------+---------------------------+---------------------------+");
            System.out.println("| Doctor_ID | Doctor_NAME               | Doctor_Specialization     |");
            System.out.println("+-----------+---------------------------+---------------------------+");
            while(rset.next())
            {
                int i = rset.getInt("id");
                String n = rset.getString("name");
                String s = rset.getString("speciality");
                System.out.printf("| %-9s | %-25s | %-25s |",i,n,s);
                System.out.println("\n+-----------+---------------------------+---------------------------+");
            }
        }
        catch(SQLException sql)
        {sql.printStackTrace();}
    }
    public boolean checkDoctorByID(int id)
    {
        String query =  "SELECT * FROM doctors WHERE id = ?";
        try
        {
            PreparedStatement ppst = connection.prepareStatement(query);
            ppst.setInt(1, id);
            ResultSet rset = ppst.executeQuery();
            //Just A Tabular Format for Output!
            System.out.println("Doctor Details:");
            System.out.println("+-----------+---------------------------+---------------------------+");
            System.out.println("| Doctor_ID | Doctor_NAME               | Doctor_Specialization     |");
            System.out.println("+-----------+---------------------------+---------------------------+");
            if(rset.next()) //Since only single entry is fetched!
            {
                int i = rset.getInt("id");
                String n = rset.getString("name");
                String s = rset.getString("speciality");
                System.out.printf("| %-9s | %-25s | %-25s |",i,n,s);
                System.out.println("\n+-----------+---------------------------+---------------------------+");
                return true;
            }
            else{return false;}
        }
        catch(SQLException sql)
        {sql.printStackTrace();}
        return false;
    }


}



