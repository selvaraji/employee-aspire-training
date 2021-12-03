package com.aspire.selva;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Updation extends Employee
{
	boolean updateEmployee()
	{
		String updatationEmpID;
		int choice = 0, flag =0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Employee ID For Updation:");
		updatationEmpID = scanner.nextLine();
		Valitation valitation = new Checking();
		
			if(checkIDinDatabase(updatationEmpID))
			{
			flag = 1;
			//Updation Menu
			for(;;)
				{
					System.out.println("*******************************************************************");
					System.out.println("Select Updation :");
					System.out.println("1.Employee ID :");
					System.out.println("2.Employee Name :");
					System.out.println("3.Employee Date Of Birth :");
					System.out.println("4.Employee Date Of Join :");
					System.out.println("5.Employee Phone Number :");
					System.out.println("6.Employee Email :");
					System.out.println("7.Exit To Main Menu :");
					System.out.println("*******************************************************************");
					try
					{
						choice = Integer.parseInt(scanner.nextLine());
					}
					catch(Exception e)
					{
						System.out.println("Enter Valid Choice :");
						continue;
					}
					
					switch (choice)
					{
					case 1:
						for(;;)
						{
							System.out.println("Enter Employee ID :");
							String employeeID = scanner.nextLine();
							if(valitation.checkEmpId(employeeID))
							{
								setEmpId(employeeID, updatationEmpID);
								trackChange("Employee Id--"+updatationEmpID+"--TO--"+ employeeID);
								System.out.println("*******************************************************************");
								System.out.println("Employee ID Updated");
								System.out.println("*******************************************************************");
								return true;
							}
							else
							{
								System.out.println("Enter Employee ID Like ACE Followed By Four Digit (ACE1234) :");
							}
						}
						
						
					case 2:
						for(;;)
						{
							System.out.println("Enter Employee Name :");
							String employeeName = scanner.nextLine();
							if(valitation.checkName(employeeName))
							{
								setName(employeeName, updatationEmpID);
								trackChange(updatationEmpID+" Employee Name Changed To "+ employeeName);
								System.out.println("*******************************************************************");
								System.out.println("Employee Name Updated");
								System.out.println("*******************************************************************");
								break;
							}
							else
							{
								System.out.println("Enter Valid Employee Name :");
							}
						}
						break;
						
					case 3:
						for(;;)
						{
							System.out.println("Enter Employee Date Of Birth :");
							String employeeDateOfBirth = scanner.nextLine();
							if(valitation.checkDOB(employeeDateOfBirth))
							{
								setDob(employeeDateOfBirth, updatationEmpID);
								trackChange(updatationEmpID+" Employee Date of Birth Changed To "+ employeeDateOfBirth);
								System.out.println("*******************************************************************");
								System.out.println("Employee Date Of Birth Updated");
								System.out.println("*******************************************************************");
								break;
								}
							else
							{
								System.out.println("Enter A Valid Employee Date Of Birth :");
							}
						}
						break;
						
					case 4:
						for(;;)
						{
							System.out.println("Enter Employee Date Of Join :");
							String employeeDateOfJoin = scanner.nextLine();
							String queryOne = "SELECT * FROM EMPLOYEE WHERE ID=\""+updatationEmpID+"\"";
							ResultSet databaseData = null ;
							String dateOfBirth = null;
							
							try 
							{
								databaseData = Main.statement.executeQuery(queryOne);
								while(databaseData.next())
								{
									dateOfBirth = databaseData.getString(3);
								}
							}
							catch (SQLException exception) {
								exception.printStackTrace();
							}
							
							if(valitation.checkDOJ(employeeDateOfJoin ,dateOfBirth))
							{
								setDoj(employeeDateOfJoin, updatationEmpID);
								trackChange(updatationEmpID+" Employee Date of Join Changed To "+ employeeDateOfJoin);
								System.out.println("*******************************************************************");
								System.out.println("Employee Date Of Join Updated");
								System.out.println("*******************************************************************");	
								break;
							}
							else
							{
								System.out.println("Enter Valid Employee Date Of Join :");
							}
						}
						break;
						
					case 5:
						for(;;)
						{
							System.out.println("Enter Employee Phone Number :");
							String employeePhoneNumber = scanner.nextLine();
							if(valitation.checkPno(employeePhoneNumber))
							{
								setPno(employeePhoneNumber, updatationEmpID);
								trackChange(updatationEmpID+" Employee Phone Number Changed To "+ employeePhoneNumber);
								System.out.println("*******************************************************************");
								System.out.println("Employee Phone Number Updated");
								System.out.println("*******************************************************************");
								break;
								}
							else
							{
								System.out.println("Enter Valid Phone Number :");
							}
						}
						break;
						
					case 6:
						for(;;)
						{
							System.out.println("Enter Employee Email");
							String employeeEmail = scanner.nextLine();
							if(valitation.checkEmail(employeeEmail))
							{
								setEmail(employeeEmail, updatationEmpID);
								trackChange(updatationEmpID+" Employee Email Changed To "+ employeeEmail);
								System.out.println("*******************************************************************");
								System.out.println("Employee Email Updated");
								System.out.println("*******************************************************************");
								break;
							}
						}
						break;
					case 7:
						
						
						return true;					}
				}
			}
			else if(flag ==1)
			{
				System.out.println("*******************************************************************");
				System.out.println("Employee Details Updated");
			}
			else
			{
				return false;
			}
			scanner.close();
			return true;
		}
	
	//Track Modifications method
	static void trackChange(String text)
	{
		String currentDate = LocalDateTime.now().toString();
		text = text+" - "+currentDate;
		logsArrayList.add((Employee.logsArrayList.size()+1)+"-- "+text);
		
		try {
			String query = "INSERT INTO LOG(LOG) VALUES (\""+ Employee.logsArrayList.size()+"-- "+text+"\")";
			Main.statement.executeUpdate(query);
			
		} catch (SQLIntegrityConstraintViolationException exception) {
			exception.printStackTrace();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} 
	}
	
	//
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

