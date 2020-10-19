package pages;
import java.io.IOException;
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
			Statement stat = CreateDatabase.connect();
			ResultSet rs;
			String firstname = request.getParameter("fname");
			String lastname = request.getParameter("lname");
			String classs = request.getParameter("class");
			long DOB = 0;
			if (request.getParameter("DOB").equals("null")) {
				stat.execute("INSERT INTO Student_Details (FirstName, LastName, Class) VALUES ('" + firstname + "', '" + lastname + "', '" + classs + "');");
			}
			else {
				DOB = (new SimpleDateFormat("ddMMyyyy").parse(request.getParameter("DOB").replaceAll("/", "")).getTime()) / 1000;
				stat.execute("INSERT INTO Student_Details (FirstName, LastName, DOB , Class) VALUES ('" + firstname + "', '" + lastname + "', " + DOB + ", '" + classs + "');");
			}
			rs = stat.executeQuery("SELECT last_insert_rowid();");
			int studentID = rs.getInt(1);
			response.getWriter().print("Student ID: " + studentID + "<br> <form action = \"AddStudent.jsp\"> <input type = \"submit\" value = \"Back\"> </form>");
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
