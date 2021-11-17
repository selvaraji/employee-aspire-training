package com.aspire.selva;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class Employee {
	static ArrayList<String> arrayList = new ArrayList<>();  //logs
	static int noOfLogs = 0;
	 
	//public  ArrayList<Employee> EmployeeArrayList = new ArrayList<Employee>(); 
	
	//default constructor
	Employee()
	{
		//
	}
	
	Employee(String empID, String empName, String empDateOfBirth, String empDateOfJoin, String empPhoneNumber, String employeeEmail)
	{
		if (!(empID.equals(null))) {
			try {
				String query = "INSERT INTO EMPLOYEE VALUES(\"" + empID + "\",\"" + empName + "\",\"" + empDateOfBirth
						+ "\",\"" + empDateOfJoin + "\",\"" + empPhoneNumber + "\",\"" + employeeEmail + "\");";
				int rs = Main.statement.executeUpdate(query);
				System.out.println(rs);
				
			} catch (SQLIntegrityConstraintViolationException exception) {
				System.out.println("Employee ID Already Used Try Unique :");
			} catch (SQLException exception) {
				exception.printStackTrace();
			} 
		}
	}
  
	
	void setName(String name, String id)
	{
		try
		{
			String query = "UPDATE EMPLOYEE SET NAME=\""+name+"\"WHERE ID=\""+id+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}

	}
	
	
	void setEmpId(String employee_ID, String id)
	{
		try
		{
			String query = "UPDATE EMPLOYEE SET ID=\""+employee_ID+"\"WHERE ID=\""+id+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}

	}
	
	
	void setDob(String date_Of_Birth, String id)
	{
		try
		{
			
			String query = "UPDATE EMPLOYEE SET DOB=\""+date_Of_Birth+"\"WHERE ID=\""+id+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}

	}
	

	void setDoj(String date_of_join, String id)
	{
		try
		{
			
			String query = "UPDATE EMPLOYEE SET DOJ=\""+date_of_join+"\"WHERE ID=\""+id+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}

	}
	
	
	void setPno(String phone_number, String id)
	{
		try
		{
			
			String query = "UPDATE EMPLOYEE SET PHONE=\""+phone_number+"\"WHERE ID=\""+id+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}

	}
	
	
	void setEmail(String employeeEmail, String id)
	{
		try
		{
			
			String query = "UPDATE EMPLOYEE SET EMAIL=\""+employeeEmail+"\"WHERE ID=\""+id+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
	}
	
}
