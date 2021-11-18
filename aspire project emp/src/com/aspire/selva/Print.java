package com.aspire.selva;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Print {
	public void  printEmployee(String employeeID)
	{
		int count = 0;
		//ArrayList Print
		for(Employee employee : Employee.EmployeeArrayList)
		{
			System.out.println("************************************************");
			System.out.printf("Employee                   : %d\n", ++count);
			System.out.printf("Employee ID %-15s: %s\n", "", employee.getEmpID());
			System.out.printf("Employee Name %-13s: %s\n", "", employee.getName());
			System.out.printf("Employee Date of Birth %-4s: %s\n", "", employee.getDOB());
			System.out.printf("Employee Date of Join %-5s: %s\n", "",employee.getDOJ());
			System.out.printf("Employee Phone number %-5s: %s\n", "",employee.getPno());
			System.out.printf("Employee Email %-12s: %s\n", "",employee.getEmail());
			System.out.println("************************************************");
		}
		
		
	/*  try
		{
			String queryOne = "SELECT * FROM EMPLOYEE WHERE ID=\""+employeeID+"\"";
			String queryAll = "SELECT * FROM EMPLOYEE";
			
			ResultSet databaseData ;
			if(employeeID.equals("flag"))
			{
				databaseData = Main.statement.executeQuery(queryAll);
			}
			else
			{
				databaseData = Main.statement.executeQuery(queryOne);
			}
			int count = 0;
			while(databaseData.next())
			{
				System.out.println("************************************************");
				System.out.printf("Employee                   : %d\n", ++count);
				System.out.printf("Employee ID %-15s: %s\n", "", databaseData.getString(1));
				System.out.printf("Employee Name %-13s: %s\n", "", databaseData.getString(2));
				System.out.printf("Employee Date of Birth %-4s: %s\n", "", databaseData.getString(3));
				System.out.printf("Employee Date of Join %-5s: %s\n", "", databaseData.getString(4));
				System.out.printf("Employee Phone number %-5s: %s\n", "", databaseData.getString(5));
				System.out.printf("Employee Email %-12s: %s\n", "", databaseData.getString(6));
				System.out.println("************************************************");
			}
			if(count == 0)
			{
				System.out.println("Employee Database Not found :");
			}
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}*/
	}
	public void printLog()
	{ 
		if(Updation.arrayList.size() == 0)
		{
			System.out.println("No Modification Recorded :");
		}
		for(String logs: Updation.arrayList)
		{
			System.out.println(logs);
		}
	}
}
