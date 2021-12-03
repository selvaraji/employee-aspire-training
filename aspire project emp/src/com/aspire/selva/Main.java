/**
 *Title       : Employee Management System
 *Author      : Selvaraji A
 *Created At  : 27-10-2021
 *Updated At  : 29-11-2021
 *Review Date : 08-11-2021
 *Reviewed by : Akshaya 
 */

package com.aspire.selva;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main extends Thread {
	public static Statement statement;
	public static ResultSet databaseData, databaseLogs;
	public static boolean databaseLoadFlag = false;
	final static String className = "com.mysql.cj.jdbc.Driver";
	
	public static void main(String[] args) {
		startMenu();
	}
	
	public static void startMenu(){
		int choice = 0;
		Scanner scanner = new Scanner (System.in);
		
		//new data thread
		Main dataRetrivalThread = new Main();
		dataRetrivalThread.start();
		
		//Main Menu
		while(choice != 6)
		{
			try
			{
				System.out.println("*******************************************************************");
				System.out.println("Enter Choice :");
				System.out.println("1.Create New Employee Database :");
				System.out.println("2.Update Existing Employee Database :");
				System.out.println("3.Delete Existing Employee Database :");
				System.out.println("4.Print Employee Details :");
				System.out.println("5.Print All Modification :");
				System.out.println("6.Exit :");
				System.out.println("*******************************************************************");
				
				choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
				case 1:
					Creation creation = new Creation();
					Updation.trackChange("New Employee "+creation.createUser()+" Created");
					System.out.println("*******************************************************************");
					System.out.println("New Employee Database Created");
					break;
					
				case 2:
					Updation updation = new Updation();
					while(!(updation.updateEmployee()))
						continue;
					break;
					
				case 3:
					Deletion deletion = new Deletion();
					System.out.println("Enter Employee ID For Deletion :");
					String deletionEmpID = new Scanner(System.in).nextLine();
					deletion.deleteEmployee(deletionEmpID);
					System.out.println("*******************************************************************");
					System.out.println("Successfully Deleted Employee : "+ deletionEmpID);
					break;
					
				case 4:
					System.out.println("1.Print All Employee Details :");
					System.out.println("2.Print Particular Employee Details :");
					int printChoice = Integer.parseInt(scanner.nextLine());
					if(databaseLoadFlag == false)
					{
						System.out.println("Please wait database will be connect :");
					}
					else
					{
						Print print = new Print();
						if(printChoice == 2)
						{
							System.out.println("Enter Employee ID :");
							String printEmpID = scanner.nextLine();
							print.printEmployee(printEmpID);
						}
						else if(printChoice == 1)
						{
							print.printEmployee();
						}
					}
					break;
					
				case 5:
					Print printLog = new Print();
					System.out.println("*******************************************************************");
					printLog.printLog();
					break;
					
				case 6:
					System.out.println("Program exited");
					System.out.println("*******************************************************************");
					break;
				
				default:
					System.out.println("Enter Valid Choice 1 TO 6");
					
				}
			}
			catch(NumberFormatException exception)
			{
				System.out.println("*******************************************************************");
				System.out.println("Enter Choice In Number Format \n");
			}
			catch(NoSuchElementException exception)
			{
				System.out.println("Invalid Input And Program Exited");
				break;
			}
			
		}
		scanner.close();
	}
	
	//new thread method
	public void run()
	{
		try {
			Class.forName(className);
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3377/db","root","tamilanda");  
			statement = connection.createStatement(); 
			Statement state = connection.createStatement();
			Statement stateLogs = connection.createStatement();
			String queryAll = "SELECT * FROM EMPLOYEE";
			databaseData  = state.executeQuery(queryAll);
			databaseLogs = stateLogs.executeQuery("SELECT * FROM LOG");
			while(databaseData.next())
			{
				Employee.employeeLinkedList.add(new Employee(databaseData.getString(1), databaseData.getString(2), databaseData.getString(3), databaseData.getString(4), databaseData.getString(5), databaseData.getString(6)));
			}
			
			while(databaseLogs.next())
			{
				Employee.logsArrayList.add(databaseLogs.getString(1));
			}
		
			
		} catch (ClassNotFoundException exception) {
		exception.printStackTrace();
		}
		 catch (SQLException exception) {
			exception.printStackTrace();
		}
		finally {
			databaseLoadFlag = true;
			System.out.println("Database Connected :");
			System.out.println("*******************************************************************");
		}
	}
	
	//static method
	static
	{
		System.out.println("*******************************************************************");
		System.out.println("Welcome To Employee Management System :");
	}
	
}

