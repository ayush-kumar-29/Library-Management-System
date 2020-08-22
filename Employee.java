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
public class Employee 
{
    public void addBook()
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
           System.out.println("ADD NEW BOOK HERE:");
           System.out.println("Enter Book ID : ");
           int bid=Integer.parseInt(sc.nextLine());
           System.out.print("Enter TITLE : ");
           String title=sc.nextLine();
           System.out.print("Enter SUBJECT : ");
           String subject=sc.nextLine();
           System.out.print("Enter AUTHOR : ");
           String author=sc.nextLine();
           String status="AVAILABLE";
           String sql="insert into BOOKS values("+bid+",'"+title+"','"+subject+"','"+author+"','"+status+"')";
           Statement st=con.createStatement();
           int x=st.executeUpdate(sql);
           if(x>0)
           {    
               System.out.println("****** NEW BOOK ADDED TO RECORD ******");
               System.out.println("THANK YOU !!");
           }
           
        }
        catch (SQLException e) 
        {
            System.err.println("Error:"+e.getMessage());
        }
    }
    public void deleteBook()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("DELETE BOOK HERE :");
        System.out.println("Enter BOOK ID :");
        int bid=Integer.parseInt(sc.nextLine());
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
           String sql="delete from BOOKS where bid="+bid;
           Statement st=con.createStatement();
           int x=st.executeUpdate(sql);
           if(x>0)
           {
               System.out.println("**** BOOK DELETED FROM RECORD ****");
               System.out.println("THANK YOU !");
           }
           else
           {
               System.out.println("#### DELETE ERROR : BOOK NOT FOUND ####");
           }
        }
        catch (SQLException e) 
        {
            System.err.println("Error:"+e.getMessage());
        }
    }
    public void listAllBooks()
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
    public void issueBook(int empid)
    {
        int a=0,b=0;
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
            System.out.println("ISSUE BOOK HERE :");
            System.out.print("Enter BOOK ID : ");
            int BID=Integer.parseInt(sc.nextLine());
            System.out.print("Enter STUDENT ROLL NO : ");
            int ROLLNO=Integer.parseInt(sc.nextLine());
            String sql1="select * from BOOKS where status='AVAILABLE'";
            Statement st1 =con.createStatement();
            ResultSet rs1=st1.executeQuery(sql1);
            while(rs1.next())
            {
                int bid=rs1.getInt("bid");
                if(bid==BID)
                {
                    a=1;
                    String sql2="select * from STUDETAIL";
                    Statement st2 =con.createStatement();
                    ResultSet rs2=st2.executeQuery(sql2);
                    while(rs2.next())
                    {
                        int rollno=rs2.getInt("rollno");
                        if(rollno==ROLLNO)
                        {
                            b=1;
                            String sql3="update BOOKS set status='ISSUED' where bid="+BID;
                            Statement st3=con.createStatement();
                            int x=st3.executeUpdate(sql3);
                            String sql4="insert into ISSUEDETAIL values("+BID+","+ROLLNO+","+empid+")";
                            Statement st4=con.createStatement();
                            int y=st4.executeUpdate(sql4);
                            if(x>0 && y>0)
                            {
                                System.out.println("**** BOOK ISSUED TO "+ROLLNO+" *****");
                                System.out.println("THANK YOU !");
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            if(a==0 && b==0)
            {
                System.out.println("#### BOOK ISSUE ERROR : WRONG BOOK ID OR BOOK NOT AVAILABLE ####");
            }
            else if(a==1 && b==0)
            {
                System.out.println("#### BOOK ISSUE ERROR : WRONG ROLL NO ENTERED ####");
            }
        }
        catch (SQLException e) 
        {
            System.err.println("Error:"+e.getMessage());
        }
    }
    public void newEmployee()
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
            System.out.println("REGISTER EMPLOYEE HERE :");
            System.out.println("ENTER DETAILS:");
            System.out.print("EMP ID : ");
            int empid=Integer.parseInt(sc.nextLine());
            System.out.print("NAME : ");
            String name=sc.nextLine();
            System.out.print("DEPT : ");
            String dept=sc.nextLine();
            System.out.print("JOIN DATE : ");
            String doj=sc.nextLine();
            System.out.print("SALARY : ");
            int salary=Integer.parseInt(sc.nextLine());
            System.out.print("PASSWORD : ");
            String pass =sc.nextLine();
            String sql="insert into EMPDETAIL values("+empid+",'"+name+"','"+dept+"','"+doj+"',"+salary+")";
            Statement st=con.createStatement();
            int x=st.executeUpdate(sql);
            if(x>0)
            {
                System.out.println("****** Employee Added Successful !!!******");
            }
            else
            {
                System.out.println("********ERROR in Registraion !!!********");
            }
            String sql2="insert into LOGIN values("+empid+",'"+pass+"','"+name+"','emp')";
            Statement st2=con.createStatement();
            int y=st2.executeUpdate(sql2);
            if(y>0)
            {
                System.out.println("LOGIN DETAILS FOR PANEL ACCESS");
                System.out.println("YOUR LOGIN ID : "+empid);
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
    public void employeeMenu(int empid)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("SELECT THE GIVEN OPTIONS:");
        System.out.println("1.For Add Book.");
        System.out.println("2.For Delete Book.");
        System.out.println("3.For List All Books.");
        System.out.println("4.For Search Book.");
        System.out.println("5.For Issue Book.");
        System.out.println("6.For Add New Employee.");
        System.out.println("7.For Exit.");
        System.out.println("--------------------------------");
        System.out.print("Enter Your Choice:");
        int choice=sc.nextInt();
        Employee E=new Employee();
        switch(choice)
        {
            case 1:
                E.addBook();
                break;
            case 2:
                E.deleteBook();
                break;
            case 3:
                E.listAllBooks();
                break;
            case 4: 
                E.searchBook();
                break;
            case 5:
                E.issueBook(empid);
                break;
            case 6:
                E.newEmployee();
                break;
            case 7:
                break;
            default:
                System.out.println("Wrong Choice Entered !");
        }
    }
        
}
