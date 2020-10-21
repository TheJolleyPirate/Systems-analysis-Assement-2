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
 * Servlet implementation class AddStudentToDatabase
 */
public class AddStudentToDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentToDatabase() {
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
			PreparedStatement pstat;
			ResultSet rs;
			String firstname = request.getParameter("fname");
			String lastname = request.getParameter("lname");
			String classs = request.getParameter("class");
			long DOB = 0;
			pstat = conn.prepareStatement("INSERT INTO Student_Details (FirstName, LastName, DOB, Class) VALUES ( ?, ?, ?, ?);");
			pstat.setString(1, firstname);
			pstat.setString(2, lastname);
			pstat.setString(4, classs);
			if (request.getParameter("DOB").equals("null")) {
				pstat.setNull(3, java.sql.Types.INTEGER);
			}
			else {
				DOB = (new SimpleDateFormat("ddMMyyyy").parse(request.getParameter("DOB").replaceAll("/", "")).getTime()) / 1000;
				pstat.setLong(3, DOB);
			}
			pstat.execute();
			rs = stat.executeQuery("SELECT last_insert_rowid();");
			int StudentID = rs.getInt(1);
			rs = stat.executeQuery("SELECT DISTINCT TestID FROM Test_Record;");
			while(rs.next()) {
				stat.execute("INSERT INTO Test_Record (StudentID, TestID) VALUES (" + StudentID + ", '" + rs.getString(1) + "');");
			}
			String back = ("Student ID: " + StudentID + "<br> <form action = \"AddStudent.jsp\"> <input type = \"submit\" value = \"Back\"> </form>");
			request.setAttribute("back", back);
			request.getRequestDispatcher("Addstudentback.jsp").forward(request, response);
		} catch (Exception e) {
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
