package pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VeiwStudentDatabase
 */
public class VeiwStudentDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VeiwStudentDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = CreateDatabase.connect();
			Statement stat = conn.createStatement();
			PreparedStatement pstat = conn.prepareStatement("SELECT*FROM Student_Details WHERE StudentID = ?;");
			int StudentID = Integer.parseInt(request.getParameter("StudentID"));
			String searchBy = request.getParameter("searchBy");
			String searchValue = request.getParameter("searchValue");
			pstat.setInt(1, StudentID);
			ResultSet rs = pstat.executeQuery();
			String fname = rs.getString("FirstName");
			String lname = rs.getString("LastName");
			String Class = rs.getString("Class");
			Long initialDate = rs.getLong("DOB");
			SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy"); 
			String date = sdf.format(initialDate * 1000);
			request.setAttribute("StudentID", StudentID);
			request.setAttribute("fname", fname);
			request.setAttribute("lname", lname);
			request.setAttribute("Class", Class);
			request.setAttribute("date", date);
			request.setAttribute("searchValue", searchValue);
			request.setAttribute("searchBy", searchBy);
			rs = stat.executeQuery("SELECT*FROM Attendance_Record WHERE StudentID = " + StudentID + ";");
			List<String> Attendance = new ArrayList<String>();
			boolean attend;
			while(rs.next()) {
				if (rs.getInt("Attendance") == 1) {
					attend = true;
				}
				else {
					attend = false;
				}
				initialDate = rs.getLong("Date");
				date = sdf.format(initialDate * 1000);
				Attendance.add("<tr> <td>" + date + "</td> <td>" + attend + "</td> </tr>");
			}
			request.setAttribute("Attendace", Attendance);
			rs = stat.executeQuery("SELECT*FROM Test_Record WHERE StudentID = " + StudentID + ";");
			List<String> Tests = new ArrayList<String>();
			int score;
			String test;
			while(rs.next()) {
				initialDate = rs.getLong("CompletedOn");
				if (rs.wasNull() == false) {
					date = sdf.format(initialDate * 1000);
				}
				else {
					date = null;
				}
				score = rs.getInt("Score");
				test = rs.getString("TestID");
				Tests.add("<tr> <td>" + test + "</td> <td>" + score + "</td> <td>" + date + "</td> </tr>");
			}
			request.setAttribute("Tests", Tests);
			request.getRequestDispatcher("ViewStudent.jsp").forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
