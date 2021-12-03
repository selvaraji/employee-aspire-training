package com.aspire.selva;


public class Print {
	public void  printEmployee()
	{
		int count = 0;
		for(Employee employee : Employee.employeeLinkedList)
		{
			System.out.println("*******************************************************************");
			System.out.printf("Employee                   : %d\n", ++count);
			System.out.printf("Employee ID %-15s: %s\n", "", employee.getEmpID());
			System.out.printf("Employee Name %-13s: %s\n", "", employee.getName());
			System.out.printf("Employee Date of Birth %-4s: %s\n", "", employee.getDOB());
			System.out.printf("Employee Date of Join %-5s: %s\n", "",employee.getDOJ());
			System.out.printf("Employee Phone number %-5s: %s\n", "",employee.getPno());
			System.out.printf("Employee Email %-12s: %s\n", "",employee.getEmail());
			System.out.println("*******************************************************************");
		}
	}
	
	public void printEmployee(String employeeID)
	{
		for(Employee employee : Employee.employeeLinkedList)
		{
			if(employee.getEmpID().equals(employeeID)) {
				System.out.println("*******************************************************************");
			//	System.out.printf("Employee                   : %d\n", ++count);
				System.out.printf("Employee ID %-15s: %s\n", "", employee.getEmpID());
				System.out.printf("Employee Name %-13s: %s\n", "", employee.getName());
				System.out.printf("Employee Date of Birth %-4s: %s\n", "", employee.getDOB());
				System.out.printf("Employee Date of Join %-5s: %s\n", "",employee.getDOJ());
				System.out.printf("Employee Phone number %-5s: %s\n", "",employee.getPno());
				System.out.printf("Employee Email %-12s: %s\n", "",employee.getEmail());
				System.out.println("*******************************************************************");
				return;
			}
		}
		System.out.println("No Employee Database Available On -  "+employeeID);
	}
		
	public void printLog()
	{ 
		if(Updation.logsArrayList.size() == 0)
		{
			System.out.println("No Modification Recorded :");
		}
		for(String logs: Updation.logsArrayList)
		{
			System.out.println(logs);
		}
	}
}
