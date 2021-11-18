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
		System.out.println("************************************************");
		System.out.println("Enter Employee Details :");
		System.out.println("************************************************");		
		
		System.out.println("Enter Employee ID Like ACE Followed By Four Digit (ACE1234) :");
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
		
			System.out.println("Enter Employee Date Of Birth In (yyyy-mm-dd) Format :");
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
					System.out.println("You Have Entered Invalid Date :");
				}
				catch(NumberFormatException exception)
				{
					System.out.println("You Have Entered  Invalid Date Format. Try yyyy-mm-dd Format :");
				}
				catch(NoSuchElementException exception)
				{
					System.out.println("Invalid Input :");
					return null;
				}
				catch(IndexOutOfBoundsException exception)
				{
					System.out.println("Enter all the details like year,month,day in yyyy-mm-dd format :");
				}
				
			}
		
			System.out.println("Enter Employee Date Of Join In (yyyy-mm-dd) Format :");
			for(;;)
			{
				try
				{
					//System.out.println("Enter Employee Date Of Join In (yyyy-mm-dd) Format :");
					dateOfJoin = scanner.nextLine();
					if(valitation.checkDOJ(dateOfJoin))
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
					System.out.println("You Have Entered Invalid Date Format. Enter yyyy-mm-dd Format :");
				}
				catch(NoSuchElementException exception)
				{
					System.out.println("Invalid Input :");
					return null;
				}
				catch(IndexOutOfBoundsException exception)
				{
					System.out.println("Enter all the details like year,month,day in yyyy-mm-dd format :");
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
			
		//new Employee(employeeID, employeeName, dateOfBirth, dateOfJoin, phoneNumber, email);
		Employee.employeeLinkedList.add(new Employee(employeeID, employeeName, dateOfBirth, dateOfJoin, phoneNumber, email));
		return employeeID;
		//scan.close();
	}
}

interface Valitation
{
	public boolean checkPno(String phoneNo);
	public boolean checkDOJ(String doj);
	public boolean checkDOB(String dob);
	public boolean checkName(String name);
	public boolean checkEmpId(String id);
	public boolean checkEmail(String mail);
	
}

class Checking implements Valitation
{
	
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
							System.out.println("Phone Number Contains Only Digits , Not Allowed Any Alphabets and Special Characters : : ");
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
						 	System.out.println("Phone Number Allowed Exactly 10 Digits Only :");
							return false;
						}
				}
			else 
			{
				System.out.println("Phone Number Should Starts With 6, 7, 8, 9 :");
				return false;
			}
	}
	
	public boolean checkEmpId(String id)
	{

		if(id.length() == 7)
		{
			if(Pattern.matches("^ace.*", id))
			{
				if(Pattern.matches(".*\\d\\d\\d\\d$",id))
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
				System.out.println("Employee ID Starts With ACE Pattern :");
				return false;
			}
		}
		else
		{
			System.out.println("Employee ID Must Be 7 Letters :");
			return false;
		}
	}
	
	public boolean checkName(String name)
	{
		char repeat = name.charAt(0);
		int count = 0;
		if(Pattern.matches("[a-zA-Z]*", name))
			for(int index = 1; index <name.length(); index++)
			{
				char nameChar = name.charAt(index);
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
		return true;
	}
	
	public boolean checkDOB(String dob)
	{
		String[] dobArray = dob.split("-");
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = LocalDate.of(Integer.parseInt(dobArray[0]), Integer.parseInt(dobArray[1]), Integer.parseInt(dobArray[2]));
		
		if((date1.getYear() - date2.getYear()) >18 && (date1.getYear() - date2.getYear()) <=60 )
		{
			return true;
		}
		else if(date1.getYear() - date2.getYear() >= 17)
		{
			if(date1.getDayOfYear() <= date2.getDayOfYear())
			{
				return true;
			}
			else
			{
				System.out.println("Age 17 Not Allowed :");
				return false;
			}
		}
		System.out.println("Age Must Be Between 18 To 60 :");
		return false;
	}
	
	public boolean checkDOJ(String doj)
	{
		String[] dojArray = doj.split("-");
		LocalDate currentDate = LocalDate.now();
		LocalDate dateOfJoin = LocalDate.of(Integer.parseInt(dojArray[0]), Integer.parseInt(dojArray[1]), Integer.parseInt(dojArray[2]));
		if((currentDate.getYear() > dateOfJoin.getYear()))
		{
			return true;
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
		System.out.println("Future Dates Are Not Possible :");
		return false;
	}

	public boolean checkEmail(String mail) {
		
		if(Pattern.matches(".*@.*", mail))	
		{
			if(Pattern.matches(".*[a-zA-Z0-9]@.*", mail))
			{
				if(Pattern.matches("^.*@[a-zA-Z]*[.][a-zA-Z].*$", mail))
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
				System.out.println("Email Not Allowed Special Characters Other Then @ :");
				return false;
			}
		}
		else if(mail.length() == 0)
		{
			System.out.println("No Input Entered :");
		}
		{
			System.out.println("Email Must Contain @ Symbol :");
			return false;
		}
	}
}
