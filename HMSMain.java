package HMS;

import java.sql.*;
import java.util.Scanner;

public class HMSMain {
    private static final String url="jdbc:mysql://localhost:3306/hospital"; //Database_Name is hospital that I used.
    private static final String uname="YOUR_USERNAME_HERE";
    private static final String pass="PASSWORD_HERE";
    public static boolean checkDoctorAvailability(int d_id,String date,Connection connection)
    {
        String query = "SELECT COUNT(*) FROM appointments WHERE doc_id = ? AND date_of_appointment = ?";
        try{
        PreparedStatement ppst = connection.prepareStatement(query);
        ppst.setInt(1,d_id);
        ppst.setString(2,date);
        ResultSet rset = ppst.executeQuery();
        if(rset.next())
        {
            int count = rset.getInt(1);
            // If doctor is appointed for the given date, if we pass an integer instead of columnName,
            // it will return columnIndex.
            // So, this way we make sure Doctor is appointed!
            if(count==0)
            {
                return true;
            }
            else{return false;}
        }
        }
        catch (SQLException sql){sql.printStackTrace();}
        return false;
    }
    public static void bookAppointment(Connection connection, Scanner scn,Patient patient, Doctor doctor)
    {
        // Check if Patient Already Exists!
        System.out.println("Enter Patient ID");
        int p_id = scn.nextInt();
        System.out.println("Enter Doctor ID");
        int d_id = scn.nextInt();
        System.out.println("Enter Your Date: (YYYY-MM-DD)");
        String appointment_date = scn.next();
        if(patient.checkPatientByID(p_id) && doctor.checkDoctorByID(d_id))
        {
            if(checkDoctorAvailability(d_id,appointment_date, connection))
            {
                String appointmentQuery = "INSERT INTO appointments(patient_id,doc_id,date_of_appointment) VALUES (?,?,?)";
                try
                {
                    PreparedStatement ppst = connection.prepareStatement(appointmentQuery);
                    ppst.setInt(1,p_id);
                    ppst.setInt(2,d_id);
                    ppst.setString(3, appointment_date);
                    int rows_affected = ppst.executeUpdate();
                    if(rows_affected>0)
                    {
                        System.out.println("Appointment Scheduled!");
                    }
                    else
                    {
                        System.out.println("Failed to Book Appointment.");
                    }
                }
                catch(SQLException sql){sql.printStackTrace();}
            }
            else{System.out.println("Doctor Not Available on this Date");}
        }
        else{System.out.println("Either Doctor or Patient Doesn't Exist!");}

    }
    // Main HMS Method of this class.
    public static void HMSDriverClass()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException cnfe){cnfe.printStackTrace();}

        try(Connection connection = DriverManager.getConnection(url,uname,pass))
        {
            Scanner scn = new Scanner(System.in);
            Patient patient = new Patient(connection,scn);
            Doctor doctor = new Doctor(connection);
            while(true)
            {
                System.out.println("Welcome To HMS");
                System.out.println("1. Add Patient Details");
                System.out.println("2. View All Patients");
                System.out.println("3. View All Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.print("Enter Your Option: - ");
                //USING SWITCH CASE
                System.out.println();
                int choice = scn.nextInt();
                switch(choice)
                {
                    case 1:
                        //Add Patient
                        patient.addPatient();
                        break;
                    case 2:
                        //Viewing Data
                        patient.viewPatients();
                        break;
                    case 3:
                        // Viewing Doctor
                        doctor.viewDoctors();
                        break;
                    case 4:
                        //Appointment Booking
                        bookAppointment(connection,scn,patient,doctor);
                        break;
                    case 5:
                        System.out.println("Existing Hospital Management System...\n"+"Exited!");
                        return;
                    default:
                        System.out.println("Enter Valid Option!!");
                        break;
                }
            }
        }
        catch (SQLException e)
        {e.printStackTrace();}
    }
}
