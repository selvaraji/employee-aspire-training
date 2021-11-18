package com.aspire.selva;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Employee {
	//logs ArrayList.
	static ArrayList<String> arrayList = new ArrayList<>();
	static int noOfLogs = 0;
	 
	//Employee LinkedList.
	public  static LinkedList<Employee> employeeLinkedList = new LinkedList<Employee>();
	private String employeeID, employeeName, employeeDateOfBirth, employeeDateOfJoin, employeePhoneNumber,employeeEmailID;
	
	
	
	//default constructor.
	Employee()
	{
		//
	}
	
	Employee(String empID, String empName, String empDateOfBirth, String empDateOfJoin, String empPhoneNumber, String employeeEmail)
	{
		//ArrayList Object Initialization
		employeeID = empID;
		employeeName = empName;
		employeeDateOfBirth = empDateOfBirth;
		employeeDateOfJoin = empDateOfJoin;
		employeePhoneNumber = empPhoneNumber;
		employeeEmailID = employeeEmail;
		
		if (!(empID.equals(null)) && Main.databaseLoadFlag == true) {
			try {
				String query = "INSERT INTO EMPLOYEE VALUES(\"" + empID + "\",\"" + empName + "\",\"" + empDateOfBirth
						+ "\",\"" + empDateOfJoin + "\",\"" + empPhoneNumber + "\",\"" + employeeEmail + "\");";
				Main.statement.executeUpdate(query);
				//System.out.println(rs);
				
			} catch (SQLIntegrityConstraintViolationException exception) {
				System.out.println("Employee ID Already Used Try Unique :");
			} catch (SQLException exception) {
				exception.printStackTrace();
			} 
		}
	}
  
	
	public void setName(String name, String id)
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
	
	public String  getName()
	{
		return employeeName;
	}
	
	public void setEmpId(String employee_ID, String id)
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
	
	public String  getEmpID()
	{
		return employeeID;
	}
	
	public void setDob(String date_Of_Birth, String id)
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
	
	public String  getDOB()
	{
		return employeeDateOfBirth;
	}

	public void setDoj(String date_of_join, String id)
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
	
	public String  getDOJ()
	{
		return employeeDateOfJoin;
	}
	
	public void setPno(String phone_number, String id)
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
	
	public String  getPno()
	{
		return employeePhoneNumber;
	}
	
	public void setEmail(String employeeEmail, String id)
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
	
	public String  getEmail()
	{
		return employeeEmailID;
	}
	
}
