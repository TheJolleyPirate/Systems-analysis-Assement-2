package pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabase {
	static Statement stat = null;
	public static Statement connect() {
		if (stat == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection("jdbc:sqlite:Student_Database.db");
				stat = conn.createStatement();
				stat.execute("CREATE TABLE IF NOT EXISTS Student_Details (StudentID INTEGER PRIMARY KEY, FirstName TEXT NOT NULL, LastName TEXT, DOB INTEGER, Class TEXT NOT NULL);");
				stat.execute("CREATE TABLE IF NOT EXISTS Attendance_Record (StudentID INTEGER, Date INTEGER, Attendance INTEGER, PRIMARY KEY(StudentID, Date));");
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return(stat);
	}
	public static void main (String [] args) {
		connect();
		System.out.print("done");
	}
}
