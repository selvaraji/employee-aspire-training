package com.aspire.selva;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Print{

	public void  printEmployee(String employeeID)
	{
		try
		{
			String queryOne = "SELECT * FROM EMPLOYEE WHERE ID=\""+employeeID+"\"";
			String queryAll = "SELECT * FROM EMPLOYEE";
			ResultSet date ;
			if(employeeID.equals("flag"))
			{
				date = Main.statement.executeQuery(queryAll);
			}
			else
			{
				date = Main.statement.executeQuery(queryOne);
			}
			int count = 0;
			while(date.next())
			{
				System.out.println("************************************************");
				System.out.printf("Employee                   : %d\n", ++count);
				System.out.printf("Employee ID %-15s: %s\n", "", date.getString(1));
				System.out.printf("Employee Name %-13s: %s\n", "", date.getString(2));
				System.out.printf("Employee Date of Birth %-4s: %s\n", "", date.getString(3));
				System.out.printf("Employee Date of Join %-5s: %s\n", "", date.getString(4));
				System.out.printf("Employee Phone number %-5s: %s\n", "", date.getString(5));
				System.out.printf("Employee Email %-12s: %s\n", "", date.getString(6));
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
		}
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
