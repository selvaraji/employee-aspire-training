package com.aspire.selva;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.DateTimeException;
import java.util.regex.Pattern;

public class Creation{
	
	String createUser(){ 
		String employeeID = null, employeeName = null, dateOfBirth = null, dateOfJoin = null, phoneNumber = null, email = null;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Valitation valitation = new Checking();
		System.out.println("*******************************************************************");
		System.out.println("Enter Employee Details :");
		System.out.println("*******************************************************************");		
		
		System.out.println("Enter Employee ID Like ACE Followed By Four Digit \"ACE1234\" :");
			for(;;)
			{
				try
				{
					//System.out.println("Enter Employee ID Like ACE Followed By Four Digit (ACE1234) :");
					employeeID = scanner.nextLine();
					if(valitation.checkEmpId(employeeID))
					{
						break;
					}
				}
				catch(StringIndexOutOfBoundsException exception )
				{
					System.out.println("Enter Valid Length Of Employee ID :");
					break;
				}
				catch(NoSuchElementException exception)
				{
					System.out.println("No Input Entered :");
					return null;
				}
			}
		
			System.out.println("Enter Employee Name :");
			for(;;)
			{
				try
				{
					//System.out.println("Enter Employee Name :");
					employeeName = scanner.nextLine();
					if(valitation.checkName(employeeName))
					{
						break;
					}
				}
				catch(NoSuchElementException exception)
				{
					System.out.println("No Input Entered :");
					return null;
				}
				catch(StringIndexOutOfBoundsException exception )
				{
					System.out.println("No Input Entered :");
				}
			}
		
			System.out.println("Enter Employee Date Of Birth In \"yyyy-mm-dd\" Format :");
			for(;;)
			{
				try
				{
					//System.out.println("Enter Employee Date Of Birth In (yyyy-mm-dd) Format :");
					dateOfBirth = scanner.nextLine();
					if(valitation.checkDOB(dateOfBirth))
					{
						break;
					}
				}
				catch(DateTimeException exception)
				{
					System.out.println("You Have Entered Invalid Date , Check Again And Enter \"yyyy-mm-dd\" This Format :");
				}
				catch(NumberFormatException exception)
				{
					System.out.println("You Have Entered  Invalid Date Format. Try \"yyyy-mm-dd\" Format :");
				}
				catch(NoSuchElementException exception)
				{
					System.out.println("Invalid Input :");
					return null;
				}
				catch(IndexOutOfBoundsException exception)
				{
					System.out.println("Enter all the details like year,month,day in \"yyyy-mm-dd\" format :");
				}
			}
		
			System.out.println("Enter Employee Date Of Join In \"yyyy-mm-dd\" Format :");
			for(;;)
			{
				try
				{
					//System.out.println("Enter Employee Date Of Join In (yyyy-mm-dd) Format :");
					dateOfJoin = scanner.nextLine();
					if(valitation.checkDOJ(dateOfJoin, dateOfBirth))
					{
						break;
					}
				}
				catch(DateTimeException exception)
				{
					System.out.println("You Have Entered Impossible Date :");
				}
				catch(NumberFormatException exception)
				{
					System.out.println("You Have Entered Invalid Date Format. Enter \"yyyy-mm-dd\" Format :");
				}
				catch(NoSuchElementException exception)
				{
					System.out.println("Invalid Input :");
					return null;
				}
				catch(IndexOutOfBoundsException exception)
				{
					System.out.println("Enter all the details like year,month,day in \"yyyy-mm-dd\" format :");
				}
				
			}
		
			System.out.println("Enter Employee Phone Number :");
			for(;;)
			{
				try
				{
					//System.out.println("Enter Employee Phone Number :");
					phoneNumber = scanner.nextLine();
					if(valitation.checkPno(phoneNumber))
					{
						break;
					}
					
				}
				catch(NoSuchElementException exception)
				{
					System.out.println("Invalid Input :");
					return null;
				}
				catch(StringIndexOutOfBoundsException exception )
				{
					System.out.println("No Input Entered :");
				}
				
			}
			System.out.println("Enter Employee Email :");
			for(;;)
			{
				try
				{
					//System.out.println("Enter Employee Email :");
					email = scanner.nextLine();
					if(valitation.checkEmail(email))
					{
						break;
					}
				}
				catch(NoSuchElementException exception)
				{
					System.out.println("Invalid Input :");
					return null;
				}
				catch(StringIndexOutOfBoundsException exception )
				{
					System.out.println("No Input Entered :");
				}
			}
			
		//constructor call and add data into Linked List
		Employee.employeeLinkedList.add(new Employee(employeeID, employeeName, dateOfBirth, dateOfJoin, phoneNumber, email));
		return employeeID;
	}
}

interface Valitation
{
	//Validation Method Signatures
	public boolean checkPno(String phoneNo);
	public boolean checkDOJ(String doj, String dob);
	public boolean checkDOB(String dob);
	public boolean checkName(String name);
	public boolean checkEmpId(String id);
	public boolean checkEmail(String mail);
	
}

class Checking implements Valitation
{
	//Validation Method Implementations
	
	public boolean checkEmpId(String employeeID)
	{
		employeeID = employeeID.toLowerCase();

		for(Employee employee : Employee.employeeLinkedList)
		{
			if(employee.getEmpID().equals(employeeID)) {
				System.out.println("Employee ID Already Available In Database Try Unique :");
				return false;
			}
		}
		
		if(employeeID.length() == 7)
		{
			if(Pattern.matches("^ace.*", employeeID))
			{
				if(Pattern.matches(".*\\d\\d\\d\\d$",employeeID))
				{
					return true;
				}
				else
				{
					System.out.println("Employee ID Ends With 4 Digits :");
					return false;
				}
			}
			else
			{
				System.out.println("Employee ID Starts With \"ACE\" Pattern :");
				return false;
			}
		}
		else
		{
			System.out.println("Employee ID Must Be 7 Letters Only :");
			return false;
		}
		
	}
	
	public boolean checkName(String employeeName)
	{
		try
		{
		char repeat = employeeName.charAt(0);
		int count = 0;
		if(Pattern.matches("[a-zA-Z]*", employeeName))
			for(int index = 1; index <employeeName.length(); index++)
			{
				char nameChar = employeeName.charAt(index);
				if(repeat == nameChar)
					count++;
				else
				{
					count = 0;
					repeat = nameChar;
				}
				if(count == 2)
				{
					System.out.println("More Then Two Repeated Characters Are Not Allowed :");
					return false;
				}
			}
		else
		{
			System.out.println("Only Alphabets Are Allowed, Special Characters And Numbers Are Not Allowed :");
			return false;
		}
		}
		catch(StringIndexOutOfBoundsException exception )
		{
			System.out.println("No Input Entered :");
		}
		return true;
	}
	
	public boolean checkDOB(String employeeDateOfBirth)
	{
		try
		{
		String[] dobArray = employeeDateOfBirth.split("-");
		LocalDate currentDate = LocalDate.now();
		LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(dobArray[0]), Integer.parseInt(dobArray[1]), Integer.parseInt(dobArray[2]));
		
		if((currentDate.getYear() - dateOfBirth.getYear()) >18 && (currentDate.getYear() - dateOfBirth.getYear()) <=60 )
		{
			return true;
		}
		else if(currentDate.getYear() - dateOfBirth.getYear() >= 17)
		{
			if(currentDate.getDayOfYear() <= dateOfBirth.getDayOfYear())
			{
				return true;
			}
			else
			{
				System.out.println("Age 17 Not Allowed :");
				return false;
			}
		}
		System.out.println("Age Must Be Between \"18 To 60\" :");
		}
		catch(IndexOutOfBoundsException exception )
		{
			System.out.println("Enter all the details like year,month,day in \"yyyy-mm-dd\" format :");
		}
		return false;
	}
	
	public boolean checkDOJ(String employeeDateOfJoin, String employeeDateOfBirth)
	{
		try
		{
		String[] dojArray = employeeDateOfJoin.split("-");
		String[] dobArray = employeeDateOfBirth.split("-");
		LocalDate currentDate = LocalDate.now();
		LocalDate dateOfBirth = LocalDate.of(Integer.parseInt(dobArray[0]), Integer.parseInt(dobArray[1]), Integer.parseInt(dobArray[2]));
		LocalDate dateOfJoin = LocalDate.of(Integer.parseInt(dojArray[0]), Integer.parseInt(dojArray[1]), Integer.parseInt(dojArray[2]));
		if((dateOfJoin.getYear() - dateOfBirth.getYear()) <= 14)
		{
			System.out.println("Date Of Join Not Aceptable As Per \"Law In INDIA\"\n Enter Valid Date Of Join :");
			return false;
		}
		else if(currentDate.getYear() == dateOfJoin.getYear())
		{
			if(currentDate.getDayOfYear() >= dateOfJoin.getDayOfYear())
			{
				return true;
			}
			else
			{
				System.out.println("Future Dates Are Not Possible :");
				return false;
			}
		}
		else if(currentDate.getYear() > dateOfJoin.getYear())
		{
			return true;
		}
		System.out.println("Future Dates Are Not Possible :");
		}
		catch(IndexOutOfBoundsException exception )
		{
			System.out.println("Enter all the details like YEAR, MONTH, DAY In \"yyyy-mm-dd\" format :");
		} 
		return false;
	}

	
	public boolean checkPno(String phoneNo)
	{
			if(Pattern.matches("^[6-9].*", phoneNo))
				{
					if(phoneNo.length() == 10)
					{
						if(Pattern.matches("^\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d$", phoneNo))
						{
							return true;
						}
						else
						{
							System.out.println("Phone Number Contains Only Digits , Not Allowed Any Alphabets and Special Characters :");
							return false;
						}
					}
					else if(phoneNo.length() == 0)
					{
						System.out.println("No Input Entered :");
						return false;
					}
					else
						{
						 	System.out.println("Phone Number Must Be 10 Digits Only :");
							return false;
						}
				}
			else 
			{
				System.out.println("Phone Number Should Starts With \"6, 7, 8, 9\" :");
				return false;
			}
	}
	
	public boolean checkEmail(String employeeEmail)
	{
		try
		{
			char repeat = employeeEmail.charAt(0);
			int count = 0;
			for(int index = 1; index <employeeEmail.length(); index++)
			{
				char nameChar = employeeEmail.charAt(index);
				if(repeat == nameChar)
					count++;
				else
				{
					count = 0;
					repeat = nameChar;
				}
				if(count == 2)
				{
					System.out.println("More Then Two Repeated Characters Are Not Allowed :");
					return false;
				}
			}
		
			if(Pattern.matches(".*@@.*", employeeEmail) || (Pattern.matches(".*.@.*.@.*", employeeEmail)))
			{
				System.out.println("Email Contains Only One \"@\" Symbol");
				return false;
			}
				
			if(Pattern.matches(".*@.*", employeeEmail))	
			{
				if(Pattern.matches(".*[a-zA-Z0-9]@.*", employeeEmail))
				{
					if(Pattern.matches("^.*@[a-zA-Z]*[.][a-zA-Z].*$", employeeEmail))
					{
						return true;
					}
					else
					{
						System.out.println("Invalid Domain Name :");
						return false;
					}
				}
				else
				{
					System.out.println("Email Not Allowed Special Characters Other Then \"@\" :");
					return false;
				}
			}
			else if(employeeEmail.length() == 0)
			{
				System.out.println("No Input Entered :");
			}
			else
			{
				System.out.println("Email Must Contain \"@\" Symbol :");
			}
		}
		catch(IndexOutOfBoundsException exception )
		{
			System.out.println("No Input Entered :");
		}
		return false;
	}
}
