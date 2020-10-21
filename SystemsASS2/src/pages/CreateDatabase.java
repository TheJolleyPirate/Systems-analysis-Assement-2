package pages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabase {
	static Connection conn = null;
	public static Connection connect() {
		Statement stat;
		if (conn == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:Student_Database.db");
				stat = conn.createStatement();
				stat.execute("CREATE TABLE IF NOT EXISTS Student_Details (StudentID INTEGER PRIMARY KEY, FirstName TEXT NOT NULL, LastName TEXT, DOB INTEGER, Class TEXT NOT NULL);");
				stat.execute("CREATE TABLE IF NOT EXISTS Attendance_Record (StudentID INTEGER, Date INTEGER, Attendance INTEGER, PRIMARY KEY(StudentID, Date));");
				stat.execute("CREATE TABLE IF NOT EXISTS Test_Record (StudentID INTEGER, TestID TEXT, Subject TEXT, Score INTEGER, CompletedOn INTEGER, PRIMARY KEY(StudentID, TestID));");
				//stat.execute("CREATE TABLE IF NOT EXISTS Leaderboard (StudentID INTEGER PRIMARY KEY, Mark INTEGER, CompletedTasks INTEGER);");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return(conn);
	}
	public static void main (String [] args) {
		connect();
		System.out.print("done");
	}
}
