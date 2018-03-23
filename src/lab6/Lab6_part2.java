//Name – Registration No. – Section
//M Rafah Mehfooz - 123918 - BESE-6A - Lab Task Part 2
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;
//STEP 1. Import required packages
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class Lab6_part2 {
        /**
     * @param args the command line arguments
     */
    //JDBC drivername and Database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost";
    
    //Database credentials
    static final String USER = "root";
    static final String PASS = "";
    
    public static void main(String[] args) {
    
    Connection conn = null;
    Statement stmt = null;
    
        try {
            //Step 2 : Register JDBC driver
            Class.forName(JDBC_DRIVER);
            
            //Step 3 : Open a connection
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql;
            
            //NEED TO CREATE A DATABASE MANUALLY in MYSQL
            System.out.println("CREATE DATABASE UNIVERSITY MANUALLY IN MYSQL...");
            
            //Step 4: Execute a query
            //Delete Existing Database
            System.out.println("Deleting database...");
            stmt = conn.createStatement();
            sql = "DROP DATABASE UNIVERSITY";
            stmt.executeUpdate(sql);
            System.out.println("Database deleted successfully...");

            //Create a New Database
            System.out.println("Creating database");
            stmt = conn.createStatement();
            sql = "CREATE DATABASE UNIVERSITY";
            stmt.execute(sql);
            System.out.println("Database Created Successfully..");

            stmt = conn.createStatement();
            sql = "USE UNIVERSITY";
            stmt.execute(sql);
            
            System.out.println("Creating STUDENTS Table");
            stmt = conn.createStatement();
            sql =  "CREATE TABLE UNIVERSITY.STUDENTS" + 
                   "(ID INTEGER not NULL," + 
                   " REGNO INTEGER," + 
                   " NAME VARCHAR(255)," + 
                   " SECTION VARCHAR(255)," + 
                   " CONTACT INTEGER, " + 
                   " ADDRESS VARCHAR(255), " + 
                   " PRIMARY KEY ( ID ))"; 
            stmt.executeUpdate(sql);
            System.out.println("Students Table Created Successfully..");
            
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            sql = "INSERT INTO STUDENTS VALUES (1, 100, 'Zara', 'A', 051123, 'St#1,Sec#1,Rawalpindi' )";   stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS VALUES (2, 101, 'Umer', 'B', 042123, 'St#2,Sec#2,Rawalpindi' )"; stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS VALUES (3, 102, 'Saad', 'A', 021123, 'St#3,Sec#3,Rawalpindi' )";   stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS VALUES (4, 103, 'Zaid', 'B', 051123, 'St#4,Sec#4,Rawalpindi' )";   stmt.executeUpdate(sql);
            sql = "INSERT INTO STUDENTS VALUES (5, 104, 'Sana', 'C', 051123, 'St#5,Sec#5,Rawalpindi' )";   stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");
            
            Scanner input;
            Double data;
            int option = 0;

            while ( option != -1)
            {
                System.out.println("1: Print Data");
                System.out.println("2: Delete Record");
                System.out.println("3: Search A Record");
                System.out.println("-1: Exit");
                System.out.print("Enter Your Choice: ");
                input = new Scanner(System.in);
                option = input.nextInt();
                ResultSet rs;
                switch(option)            
                {
                    case(1):
                        System.out.println(" Reading all results...");
                        stmt = conn.createStatement();
                        sql = "SELECT * FROM UNIVERSITY.STUDENTS";
                        rs = stmt.executeQuery(sql);
                        //STEP 5: Extract data from result set
                        while(rs.next()){
                           //Retrieve by column name
                           int id  = rs.getInt("ID");           int RegNo = rs.getInt("REGNO");
                           String Name = rs.getString("NAME");  String Section = rs.getString("SECTION");
                           int Contact = rs.getInt("CONTACT");  String Address = rs.getString("ADDRESS");
                           //Display values
                           System.out.print("ID: " +id+",  REGNO: "+RegNo+",  NAME: " +Name+",  SECTION: " + Section);
                           System.out.print(",  CONTACT: " + Contact+",  ADDRESS: " + Address);
                           System.out.println("");
                        }
                        rs.close();
                        break;
                    case(2):
                        System.out.println("Delete a Record...");
                        System.out.print("Enter Registration Number: ");
                        input = new Scanner(System.in);
                        data = input.nextDouble();
                        stmt = conn.createStatement();
                        sql = "DELETE FROM STUDENTS WHERE REGNO="+data+" ";
                        stmt.executeUpdate(sql);
                        System.out.println("Record Deleted...");
                        break;
                    case(3):
                        System.out.println("Select a Record...");
                        System.out.print("Enter Registration Number: ");
                        input = new Scanner(System.in);
                        data = input.nextDouble();
                        stmt = conn.createStatement();
                        sql = "SELECT * FROM UNIVERSITY.STUDENTS WHERE REGNO="+data+" ";
                        rs = stmt.executeQuery(sql);
                        
                        while(rs.next()){
                           //Retrieve by column name
                           int id  = rs.getInt("ID");           int RegNo = rs.getInt("REGNO");
                           String Name = rs.getString("NAME");  String Section = rs.getString("SECTION");
                           int Contact = rs.getInt("CONTACT");  String Address = rs.getString("ADDRESS");
                           //Display values
                           System.out.print("ID: " +id+",  REGNO: "+RegNo+",  NAME: " +Name+",  SECTION: " + Section);
                           System.out.print(",  CONTACT: " + Contact+",  ADDRESS: " + Address);
                           System.out.println("");
                        }
                        rs.close();
                        
                        System.out.println("Record Displayed...");
                        break;
                    default:
                        break;
                }
            }   
        } 
        catch (SQLException se) 
        {
            //Handle errors for JDBC
            se.printStackTrace();
            
        }
        catch (Exception e)
        {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        finally
        {// Free resources
            try
            {
                if (stmt != null)
                    stmt.close();
            }
            catch(SQLException se2){}//noting to do
            try
            {
                if (conn != null)
                    conn.close();
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }// end finally
       System.out.println("Goodbye!");

    }
}
