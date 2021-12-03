package com.aspire.selva;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Employee {
	
	//logs ArrayList.
	static ArrayList<String> logsArrayList = new ArrayList<>();
	 
	//Employee LinkedList.
	public  static LinkedList<Employee> employeeLinkedList = new LinkedList<Employee>();
	
	//private members
	private String employeeID, employeeName, employeeDateOfBirth, employeeDateOfJoin, employeePhoneNumber,employeeEmailID;
	
	//default constructor.
	Employee()
	{
		//
	}

	//parameterized constructor
	Employee(String empID, String empName, String empDateOfBirth, String empDateOfJoin, String empPhoneNumber, String empEmail)
	{
		//linkedList Object Initialization
		employeeID = empID;
		employeeName = empName;
		employeeDateOfBirth = empDateOfBirth;
		employeeDateOfJoin = empDateOfJoin;
		employeePhoneNumber = empPhoneNumber;
		employeeEmailID = empEmail;
		
		//Insert data into database
		if ( (!(empID.equals(null))) && Main.databaseLoadFlag == true) {
			try {
				String query = "INSERT INTO EMPLOYEE VALUES(\"" + empID + "\",\"" + empName + "\",\"" + empDateOfBirth
						+ "\",\"" + empDateOfJoin + "\",\"" + empPhoneNumber + "\",\"" + empEmail + "\");";
				Main.statement.executeUpdate(query);
				
			} catch (SQLIntegrityConstraintViolationException exception) {
				System.out.println("Employee ID Already Used Try Unique :");
			} catch (SQLException exception) {
				exception.printStackTrace();
			} 
		}
	}
  
	//setter and getter methods
	
	public void setName(String name, String empID)
	{
		try
		{
			String query = "UPDATE EMPLOYEE SET NAME=\""+name+"\"WHERE ID=\""+empID+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		

		for(Employee employee : employeeLinkedList)
		{
			if(employee.getEmpID().equals(empID))
			{
				employee.employeeName = name;
			}
		}

	}
	
	public String  getName()
	{
		return employeeName;
	}
	
	public void setEmpId(String employeeId, String empID)
	{
		try
		{
			String query = "UPDATE EMPLOYEE SET ID=\""+employeeId+"\"WHERE ID=\""+empID+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		
		for(Employee employee : employeeLinkedList)
		{
			if(employee.getEmpID().equals(empID))
			{
				employee.employeeID = employeeId;
			}
		}

	}
	
	public String  getEmpID()
	{
		return employeeID;
	}
	
	public void setDob(String dateOfBirth, String empID)
	{
		try
		{
			
			String query = "UPDATE EMPLOYEE SET DOB=\""+dateOfBirth+"\"WHERE ID=\""+empID+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		
		for(Employee employee : employeeLinkedList)
		{
			if(employee.getEmpID().equals(empID))
			{
				employee.employeeDateOfBirth = dateOfBirth;
			}
		}

	}
	
	public String  getDOB()
	{
		return employeeDateOfBirth;
	}

	public void setDoj(String dateOfJoin, String empID)
	{
		try
		{
			
			String query = "UPDATE EMPLOYEE SET DOJ=\""+dateOfJoin+"\"WHERE ID=\""+empID+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		
		for(Employee employee : employeeLinkedList)
		{
			if(employee.getEmpID().equals(empID))
			{
				employee.employeeDateOfJoin = dateOfJoin;
			}
		}


	}
	
	public String  getDOJ()
	{
		return employeeDateOfJoin;
	}
	
	public void setPno(String phoneNumber, String empID)
	{
		try
		{
			
			String query = "UPDATE EMPLOYEE SET PHONE=\""+phoneNumber+"\"WHERE ID=\""+empID+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		
		for(Employee employee : employeeLinkedList)
		{
			if(employee.getEmpID().equals(empID))
			{
				employee.employeePhoneNumber = phoneNumber;
			}
		}
	}
	
	public String  getPno()
	{
		return employeePhoneNumber;
	}
	
	public void setEmail(String employeeEmail, String empID)
	{
		try
		{
			
			String query = "UPDATE EMPLOYEE SET EMAIL=\""+employeeEmail+"\"WHERE ID=\""+empID+"\";";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		
		for(Employee employee : employeeLinkedList)
		{
			if(employee.getEmpID().equals(empID))
			{
				employee.employeeEmailID = employeeEmail;
			}
		}
	}
	
	public String  getEmail()
	{
		return employeeEmailID;
	}
}
