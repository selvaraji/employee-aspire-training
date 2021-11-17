package com.aspire.selva;

import java.sql.SQLException;

public class Deletion extends Employee
{
	void deleteEmployee(String deletion_emp_id)
	{
		try {
			
			String query = "DELETE FROM EMPLOYEE WHERE ID = \""+deletion_emp_id+"\"";
			int rs = Main.statement.executeUpdate(query);
			System.out.println(rs);
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
		}
		
	}
}
