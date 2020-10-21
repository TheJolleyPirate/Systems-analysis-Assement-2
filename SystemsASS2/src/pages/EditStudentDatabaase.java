package pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditStudentDatabaase
 */
public class EditStudentDatabaase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudentDatabaase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = CreateDatabase.connect();
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
			request.getRequestDispatcher("EditStudent.jsp").forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = CreateDatabase.connect();
			PreparedStatement pstat = conn.prepareStatement("UPDATE Student_Details SET FirstName = ?, LastName = ?, DOB = ?, Class = ? WHERE StudentID = ?;");
			pstat.setString(1, request.getParameter("fname"));
			pstat.setString(2, request.getParameter("lname"));
			Long date = (new SimpleDateFormat("ddMMyyyy").parse(request.getParameter("date").replaceAll("/", "")).getTime()) / 1000;
			pstat.setLong(3, date);
			pstat.setString(4, request.getParameter("Class"));
			pstat.setInt(5, Integer.parseInt(request.getParameter("StudentID")));
			pstat.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
