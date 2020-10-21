package pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchStudentDatabase
 */
public class SearchStudentDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStudentDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = CreateDatabase.connect();
			PreparedStatement pstat;
			ResultSet rs;
			String searchBy = request.getParameter("searchBy");
			String searchValue = request.getParameter("searchValue");
			int StudentID;
			Long date;
			String name;
			String Class;
			if(searchBy.equals("StudentID")) {
				pstat = conn.prepareStatement("SELECT*FROM Student_Details WHERE StudentID = ?;");
				pstat.setInt(1, Integer.parseInt(searchValue));
			}
			else if(searchBy.equals("fname")) {
				pstat = conn.prepareStatement("SELECT*FROM Student_Details WHERE FirstName LIKE ?;");
				pstat.setString(1, "%" + searchValue + "%");
			}
			else if(searchBy.equals("lname")) {
				pstat = conn.prepareStatement("SELECT*FROM Student_Details WHERE LastName LIKE ?;");
				pstat.setString(1, "%" + searchValue + "%");
			}
			else if(searchBy.equals("Class")) {
				pstat = conn.prepareStatement("SELECT*FROM Student_Details WHERE Class LIKE ?;");
				pstat.setString(1, "%" + searchValue + "%");
			}
			else if(searchBy.equals("DOB")) {
				pstat = conn.prepareStatement("SELECT*FROM Student_Details WHERE DOB = ?;");
				date = (new SimpleDateFormat("ddMMyyyy").parse(searchValue.replaceAll("/", "")).getTime()) / 1000;
				pstat.setLong(1, date);
			}
			else {
				pstat = conn.prepareStatement("SELECT*FROM Student_Details;");
			}
			rs = pstat.executeQuery();
			List<String> rows = new ArrayList<String>();
			while(rs.next()) {
				StudentID = rs.getInt("StudentID");
				date = rs.getLong("DOB");
				Class = rs.getString("Class");
				name = rs.getString("LastName") + " " + rs.getString("FirstName");
				String delete = "<form action = \"SearchStudentDatabase\" method = \"post\"> <input type = \"hidden\" name = \"searchBy\" value = \"" + searchBy + "\"> <input type = \"hidden\" name = \"searchvalue\" value = \"" + searchValue + "\"> <input type = \"hidden\" name = \"StudentID\" value = \"" + StudentID + "\"> <input type = \"submit\" value = \"Delete\"> </form> ";
				String view = "<form action = \"VeiwStudentDatabase\"> <input type = \"hidden\" name = \"searchBy\" value = \"" + searchBy + "\"> <input type = \"hidden\" name = \"searchvalue\" value = \"" + searchValue + "\"> <input type = \"hidden\" name = \"StudentID\" value = \"" + StudentID + "\"> <input type = \"submit\" value = \"View\"> </form> ";
				String edit = "<form action = \"EditStudentDatabaase\"> <input type = \"hidden\" name = \"searchBy\" value = \"" + searchBy + "\"> <input type = \"hidden\" name = \"searchvalue\" value = \"" + searchValue + "\"> <input type = \"hidden\" name = \"StudentID\" value = \"" + StudentID + "\"> <input type = \"submit\" value = \"Edit\"> </form> ";
				String temp = name + "?<tr> <td> " + StudentID + "</td> <td> " + name + " </td> <td> "  + Class + " </td> <td> " + view + edit + delete + "</td> </tr>";
				rows.add(temp);
			}
			rows = rows.stream().sorted().collect(Collectors.toList());
			List<String> Rows = new ArrayList<String>();
			for (int i = 0; i < rows.size(); i++) {
				Rows.add(rows.get(i).split("\\?")[1]);
			}
			request.setAttribute("Rows", Rows);
			request.setAttribute("searchValue", searchValue);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("SearchStudent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = CreateDatabase.connect();
			Statement stat = conn.createStatement();
			int StudentID = Integer.parseInt(request.getParameter("StudentID"));
			stat.execute("DELETE FROM Student_Details WHERE StudentID = " + StudentID + ";");
			stat.execute("DELETE FROM Attendance_Record WHERE StudentID = " + StudentID + ";");
			stat.execute("DELETE FROM Test_Record WHERE StudentID = " + StudentID + ";");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
