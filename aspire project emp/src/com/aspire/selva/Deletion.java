package com.aspire.selva;

import java.sql.SQLException;

public class Deletion extends Employee
{
	void deleteEmployee(String deletionEmpID)
	{
		//database deletion
		try {
			
			String query = "DELETE FROM EMPLOYEE WHERE ID = \""+deletionEmpID+"\"";
			Main.statement.executeUpdate(query);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		
		//LinkedList deletion
		
		for(int index=0;index<employeeLinkedList.size();index++)
		{
			if(employeeLinkedList.get(index).getEmpID().equals(deletionEmpID))
			{
				employeeLinkedList.remove(index);
				Updation.trackChange("Employee "+deletionEmpID+" Deleted");
			}
		}
	}
}
