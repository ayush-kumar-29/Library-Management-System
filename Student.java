/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcpl.project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 *
 * @author KIIT
 */
public class Student 
{
    public void newStudent()
    {
        Scanner sc=new Scanner(System.in);
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException ex) 
        {
            System.err.println("Error:"+ex.getMessage());
        }
        String url="jdbc:mysql://localhost:3306/CollegeLibrary";
        try(Connection con=DriverManager.getConnection(url,"root","password"))
        {
            System.out.println("REGISTER STUDENT HERE:");
            System.out.println("ENTER DETAILS:");
            System.out.print("ROLL NO :");
            int rollno=Integer.parseInt(sc.nextLine());
            System.out.print("NAME :");
            String name=sc.nextLine();
            System.out.print("DEPT :");
            String dept=sc.nextLine();
            System.out.print("SEM :");
            String sem=sc.nextLine();
            System.out.print("BATCH :");
            String batch=sc.nextLine();
            System.out.print("PASSWORD :");
            String pass=sc.nextLine();
            String sql="insert into STUDETAIL values("+rollno+",'"+name+"','"+dept+"','"+sem+"','"+batch+"')";
            Statement st=con.createStatement();
            int x=st.executeUpdate(sql);
            if(x>0)
            {
                System.out.println("********Registration Successful !!!********");
            }
            else
            {
                System.out.println("********ERROR in Registraion !!!********");
            }
            String sql2="insert into LOGIN values("+rollno+",'"+pass+"','"+name+"','stu')";
            try(Connection con2=DriverManager.getConnection(url,"root","password"))
            {
                Statement st2=con2.createStatement();
                int y=st2.executeUpdate(sql2);
                if(y>0)
                {
                    System.out.println("LOGIN DETAILS FOR PANEL ACCESS");
                    System.out.println("YOUR LOGIN ID : "+rollno);
                    System.out.println("YOUR PASSWORD : "+pass);
                    System.out.println("THANK YOU !");
                }
                else
                {
                    System.out.println("********ERROR in Registration********");
                }
            }
            catch (SQLException e) 
            {
                System.err.println("Error:"+e.getMessage());
            }
        }
        catch (SQLException e) 
        {
            System.err.println("Error:"+e.getMessage());
        }
    }
    public void viewAllBooks()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException ex) 
        {
            System.err.println("Error:"+ex.getMessage());
        }
        String url="jdbc:mysql://localhost:3306/CollegeLibrary";
        try(Connection con=DriverManager.getConnection(url,"root","password"))
        {
            String sql="select * from BOOKS";
            Statement st =con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%-11s %-15s %-10s %-15s %-10s\n","BID","TITLE","SUBJECT","AUTHOR","STATUS");
            System.out.println("------------------------------------------------------------------");
            while(rs.next())
            {
                int bid=rs.getInt("bid");
                String title =rs.getString("title");
                String subject=rs.getString("subject");
                String author=rs.getString("author");
                String status=rs.getString("status");
                System.out.printf("%-11d %-15s %-10s %-15s %-10s\n",bid,title,subject,author,status);
            }
            System.out.println("------------------------------------------------------------------");
        }
        catch (SQLException e) 
        {
            System.err.println("Error:"+e.getMessage());
        }
    }
    public void searchBook()
    {
        Scanner sc=new Scanner(System.in);
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException ex) 
        {
            System.err.println("Error:"+ex.getMessage());
        }
        String url="jdbc:mysql://localhost:3306/CollegeLibrary";
        try(Connection con=DriverManager.getConnection(url,"root","password"))
        {
            
            System.out.println("Search Book Here :");
            System.out.print("Enter Text to Search : ");
            String TITLE=sc.nextLine();
            String sql="select * from BOOKS where title='"+TITLE+"'";
            Statement st =con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            System.out.println("------------------------------------------------------------------");
            System.out.printf("%-11s %-15s %-10s %-15s %-10s\n","BID","TITLE","SUBJECT","AUTHOR","STATUS");
            System.out.println("------------------------------------------------------------------");
            while(rs.next())
            {
                int bid=rs.getInt("bid");
                String title =rs.getString("title");
                String subject=rs.getString("subject");
                String author=rs.getString("author");
                String status=rs.getString("status");
                System.out.printf("%-11d %-15s %-10s %-15s %-10s\n",bid,title,subject,author,status);
            }
            System.out.println("------------------------------------------------------------------");
        }
        catch (SQLException e) 
        {
            System.err.println("Error:"+e.getMessage());
        }
    }
    public void studentMenu()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Select the given option:");
        System.out.println("1.For View All Books.");
        System.out.println("2.For Search Book.");
        System.out.println("3.For Exit.");
        System.out.println("--------------------------------");
        System.out.print("Enter Your Choice:");
        int choice=sc.nextInt();
        Student S=new Student();
        switch(choice)
        {
            case 1:
                S.viewAllBooks();
                break;
            case 2:
                S.searchBook();
                break;
            case 3:
                break;
            default:
                System.out.println("Wrong Choice Entered !");
       }
    }
}
