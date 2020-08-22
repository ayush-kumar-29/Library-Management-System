/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rcpl.project;
import rcpl.project.Login;
import rcpl.project.Student;
import java.util.Scanner;
/**
 *
 * @author KIIT
 */

public class Main 
{
   public static void main(String [] argv) 
   {
       Scanner sc=new Scanner(System.in);
       Login L=new Login();
       Student S=new Student();
       System.out.println("--------------------------------");
       System.out.println("WELCOME TO COLLEGE LIBRARY PANEL");
       System.out.println("--------------------------------");
       System.out.println("SELECT THE GIVEN OPTIONS: ");
       System.out.println("1.For Login.");
       System.out.println("2.For register New Student.");
       System.out.println("3.For Exit.");
       System.out.println("--------------------------------");
       System.out.print("Enter you choice:");
       int choice=sc.nextInt();
       switch(choice)
       {
           case 1:
               L.loginNow();
               break;
           case 2:
               S.newStudent();
               break;
           case 3:
               break;
           default:
               System.out.println("Wrong Choice Entered !");
       }
   }
}
