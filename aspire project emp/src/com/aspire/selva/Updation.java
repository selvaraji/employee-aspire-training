package com.aspire.selva;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Updation extends Employee
{
	void updateEmployee( String updation_emp_id)
	{
		int choice = 0;
		Scanner scanner = new Scanner(System.in);
		Valitation valitation = new Checking();
		
			if(checkIDinDatabase(updation_emp_id))
			{
			while(choice != 7)
				{
					System.out.println("************************************************");
					System.out.println("Select Updation :");
					System.out.println("1.Employee ID :");
					System.out.println("2.Employee Name :");
					System.out.println("3.Employee Date Of Birth :");
					System.out.println("4.Employee Date Of Join :");
					System.out.println("5.Employee Phone Number :");
					System.out.println("6.Employee Email :");
					System.out.println("7.Exit To Main Menu :");
					System.out.println("************************************************");
					
					choice = scanner.nextInt();
					scanner.nextLine();
					
					switch (choice)
					{
					case 1:
						System.out.println("Enter Employee ID");
						String employeeID = scanner.nextLine();
						if(valitation.checkEmpId(employeeID))
						{
							setEmpId(employeeID, updation_emp_id);
							trackChange("Employee Id--"+updation_emp_id+"--TO--"+ employeeID);
							System.out.println("************************************************");
							System.out.println("Employee ID Updated");
							System.out.println("************************************************");
							}
						else
						{
							System.out.println("Enter Employee ID Like ACE Followed By Four Digit (ACE1234) :");
						}
						break;
						
					case 2:
						System.out.println("Enter Employee Name");
						String employeeName = scanner.nextLine();
						if(valitation.checkName(employeeName))
						{
							setName(employeeName, updation_emp_id);
							trackChange(updation_emp_id+" Employee Name Changed To "+ employeeName);
							System.out.println("************************************************");
							System.out.println("Employee Name Updated");
							System.out.println("************************************************");
						}
						else
						{
							System.out.println("Enter Valid Employee Name");
						}
						break;
						
					case 3:
						System.out.println("Enter Employee Date Of Birth");
						String employeeDateOfBirth = scanner.nextLine();
						if(valitation.checkDOB(employeeDateOfBirth))
						{
							setDob(employeeDateOfBirth, updation_emp_id);
							trackChange(updation_emp_id+" Employee Date of Birth Changed To "+ employeeDateOfBirth);
							System.out.println("************************************************");
							System.out.println("Employee Date Of Birth Updated");
							System.out.println("************************************************");
							}
						else
						{
							System.out.println("Enter A Valid Employee Date Of Birth ");
						}
						break;
						
					case 4:
						System.out.println("Enter Employee Date Of Join");
						String employeeDateOfJoin = scanner.nextLine();
						if(valitation.checkDOJ(employeeDateOfJoin))
						{
							setDoj(employeeDateOfJoin, updation_emp_id);
							trackChange(updation_emp_id+" Employee Date of Join Changed To "+ employeeDateOfJoin);
							System.out.println("************************************************");
							System.out.println("Employee Date Of Join Updated");
							System.out.println("************************************************");	
							}
						else
						{
							System.out.println("Enter Valid Employee Date Of Join ");
						}
						break;
						
					case 5:
						System.out.println("Enter Employee Phone Number");
						String employeePhoneNumber = scanner.nextLine();
						if(valitation.checkPno(employeePhoneNumber))
						{
							setPno(employeePhoneNumber, updation_emp_id);
							trackChange(updation_emp_id+" Employee Phone Number Changed To "+ employeePhoneNumber);
							System.out.println("************************************************");
							System.out.println("Employee Phone Number Updated");
							System.out.println("************************************************");
							}
						else
						{
							System.out.println("Enter Valid Phone Number");
						}
						break;
						
					case 6:
						System.out.println("Enter Employee Email");
						String employeeEmail = scanner.nextLine();
						if(valitation.checkEmail(employeeEmail))
						{
							setEmail(employeeEmail, updation_emp_id);
							trackChange(updation_emp_id+" Employee Email Changed To "+ employeeEmail);
							System.out.println("************************************************");
							System.out.println("Employee Email Updated");
							System.out.println("************************************************");
						}
					}
				}
			}
			else
			{
				System.out.println("************************************************");
				System.out.println("Employee Details Updated");
			}
			scanner.close();
		}
	
	static void trackChange(String text)
	{
		String currentDate = LocalDateTime.now().toString();
		text = text+" - "+currentDate;
		arrayList.add((Employee.noOfLogs++)+"-- "+text);
	}
	
	static boolean checkIDinDatabase(String id)
	{
		try
		{
			
			String query = "SELECT ID FROM EMPLOYEE";
			ResultSet date = Main.statement.executeQuery(query);
			while(date.next())
			{
				if(date.getString(1).toLowerCase().equals(id))
				{
					return true;
				}
			}
			System.out.println("Employee ID Not Available in Database :");
			return false;
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		return false;
	}
}

