package pages;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AttendanceDatabase
 */
public class AttendanceDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statement stat = CreateDatabase.connect();
		ResultSet rs = null;
		String classe = request.getParameter("class");
		try {
			rs = stat.executeQuery("SELECT FirstName, LastName FROM Student_Details WHERE Class = '" + classe + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<String> names = new ArrayList<String>();
		try {
			while(rs.next()) {
				names.add(rs.getString("LastName") + "." + rs.getString("FirstName"));
			}
			names = names.stream().sorted().collect(Collectors.toList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("names", names);
		request.setAttribute("class2", classe);
		request.getRequestDispatcher("Attendance.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
