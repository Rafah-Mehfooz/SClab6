//Name – Registration No. – Section
//M Rafah Mehfooz - 123918 - BESE-6A - Lab Task Part 1

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author Admin
 */
public class Lab6_part1 {
    
     public static void sortby_col(String arr[][], int col)
    {
        Arrays.sort(arr, new Comparator<String[]>()
        {
            @Override
            public int compare(String[] o1, String[] o2) {
                if( Double.parseDouble(o1[col]) < Double.parseDouble(o2[col]) )
                    return 1;
                else
                    return -1;
            }
        });

    }
     
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        
        // TODO code application logic here         
        int ROWS = 2;
        int COLS = 4;
        System.out.println("Welocme to the JAVA world!");

        //0:Index, 1:Registration Number, 2: Names and 3: CGPA
        String[][] multi = new String[ROWS][COLS];

        Set<String> reg_Set = new HashSet<String>();
        
        for (int i = 0; i < ROWS; i++)
        {
            System.out.println("** New Entry Number: "+i+" **");
                        
            for (int j = 0; j < COLS; j++)
            {
                switch(j)
                {
                    //Unique Registration Number
                    case(1):
                        System.out.print("Enter Your Registration Number: ");
                        Scanner rNIn = new Scanner(System.in);
                        String regNum = rNIn.nextLine();
                        
                        //check unique value for Registration Number
                        if (reg_Set.add(regNum) == true )
                        {
                            System.out.println("Registration Num: ["+regNum+"] Added!");
                            multi[i][j] = regNum;
                            break;
                        }
                        else
                        {
                            System.out.println("Registration Number Already Exists, Enter Valid Registration Number Again");
                            System.out.println("");
                            j--;
                            continue;
                        }
                    //Student Name    
                    case(2):
                        System.out.print("Enter Your Name: ");
                        Scanner nIn = new Scanner(System.in);
                        String Name = nIn.nextLine();
                        System.out.println("Student Name: ["+Name+"] Added!");
                        multi[i][j] = Name;
                        break;
                    //Valid CGPA
                    case(3):
                        System.out.print("Enter Your CGPA: ");
                        Scanner cIn = new Scanner(System.in);
                        Double CGPA = cIn.nextDouble();
                        
                        //check whether data is valid and in double
                        if (( CGPA <= 4.0 && CGPA >= 1.0) && ((Object)CGPA instanceof Double))
                        {
                            String string_cgpa = Double.toString(CGPA);
                            System.out.println("Student CGPA: ["+string_cgpa+"] Added!");
                            multi[i][j] = string_cgpa;
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid CGPA, Enter Valid CGPA Again");
                            System.out.println("");
                            j--;
                            continue;
                        }
                        
                    default:
                        break;
                }
            }
        }
        System.out.println("");
        
        //Printing Student Data Min, Max, Average
        double min, max, avg, total = 0.0, min_temp = 5.0, max_temp = -1.0;
        System.out.println("Printing Student Data: ");
        System.out.println("RegNum  Name    CGPA");
        //Printing Data
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 1; j < COLS; j++)
            {
                System.out.print(multi[i][j]+"  ");
                if (j == 3)
                {
                    double temp = Double.parseDouble(multi[i][j]);
                    total += temp;
                    min_temp = Double.min(temp, min_temp);
                    max_temp = Double.max(temp, max_temp);
                }
            }
            System.out.println(" ");
        }
        
        //Min, Max and Average Data
        min = min_temp; max = max_temp;avg = total/ROWS;
        
        System.out.println("Minimum CGPA: ["+min+"]");
        System.out.println("Maximum CGPA: ["+max+"]");
        System.out.println("Average CGPA: ["+avg+"]");
        
        System.out.println("Students name, who Obtained Less than Avergae CGPA ");
        
        for (int i = 0; i < ROWS; i++)
        {
            if ( Double.parseDouble(multi[i][3]) < avg)
            {
                System.out.println(i+"  "+multi[i][2]);
        
            }
            
        }
        
        //Rank of Students
        sortby_col(multi,3);
        
        String data;
        int flag = 0;
        String Sname = "", SReg = "", SCGPA = "";
        int rank = 0;
        do {
            System.out.print("Enter RegNum or Name to find Data: ");
            Scanner input = new Scanner(System.in);
            data = input.nextLine();
            for (int i = 0; i < ROWS; i++)
            {
                if ( (data.equals(multi[i][1])) ||  (data.equals(multi[i][2])) )
                {
                    flag = 1;
                    SReg = multi[i][1];
                    Sname = multi[i][2];
                    SCGPA = multi[i][3];
                    rank = i+1;
                    break;
                }
                else
                    flag = 0;
                
            }
            if (flag == 1)
                break;
        } while ( !(data.equals("-1")));
        
        if (flag == 1)
        {
            System.out.println("Student Found ");
            System.out.println("Student Registration Number: "+ SReg);
            System.out.println("Student Name: "+ Sname);
            System.out.println("Student CGPA: "+ SCGPA);
            System.out.println("Student Rank: "+ rank+" out of "+ ROWS);
            
        }
        System.out.println("Program Successfully Completed! ");


    }
}
