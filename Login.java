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
import rcpl.project.Employee;
import rcpl.project.Student;
/**
 *
 * @author KIIT
 */
public class Login 
{
    public void loginNow()
    {
        Scanner sc=new Scanner(System.in);
        Employee E=new Employee();
        Student S=new Student();
        int x=0,y=0;
        System.out.print("Enter Login ID:");
        int ID=Integer.parseInt(sc.nextLine());
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException e) 
        {
            System.err.println("Error:"+e.getMessage());
        }
        String url="jdbc:mysql://localhost:3306/CollegeLibrary";
        try(Connection con=DriverManager.getConnection(url,"root","password"))
        {
            String sql="select * from LOGIN";
            Statement st =con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next())
            {
                int id=rs.getInt("id");
                if(id==ID)
                {
                    x=1;
                    System.out.print("Enter Password:");
                    String PASS=sc.nextLine();
                    String pass=rs.getString("pass");
                    if(pass.equals(PASS))
                    {
                        y=1;
                        String ROLE=rs.getString("role");
                        if(ROLE.equals("stu"))
                        S.studentMenu();
                        else if(ROLE.equals("emp"))
                        E.employeeMenu(ID);
                    }
                
                }
            }    
            if(x==0 && y==0)
               System.out.println("Invalid ID !!!");
            if(x==1 && y==0)
                System.out.println("Wrong Password !!!\nTry Again...(");
        }
        catch (SQLException e) 
        {
            System.err.println("Error:"+e.getMessage());
        }
        
    }
}
