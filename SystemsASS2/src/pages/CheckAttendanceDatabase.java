package pages;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckAttendanceDatabase
 */
public class CheckAttendanceDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAttendanceDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Statement stat = CreateDatabase.connect();
			Long date = (new SimpleDateFormat("ddMMyyyy").parse(request.getParameter("date").replaceAll("/", "")).getTime()) / 1000;
			ResultSet rs = stat.executeQuery("SELECT*FROM Attendance_Record WHERE Date = " + date + ";");
			ArrayList<Row> rows = new ArrayList<Row>();
			while (rs.next()) {
				Row temp = new Row();
				temp.StudentID = rs.getInt("StudentID");
				temp.Date = rs.getInt("Date");
				if (rs.getInt("Attendance") == 1) {
					temp.Attendance = true;
				}
				ResultSet rs2 = stat.executeQuery("Select LastName, FirstName FROM Student_Details WHERE StudentID = '" + temp.StudentID + "');");
				temp.name = rs2.getString(1) + " " + rs2.getString(2);
			}
			request.setAttribute("rows", rows);
			request.getRequestDispatcher("CheckAttendance.jsp").forward(request, response);
		}
		catch(Exception e) {
			
		}
		
	}
	class Row{
		int StudentID;
		int Date;
		boolean Attendance = false;
		String name;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
