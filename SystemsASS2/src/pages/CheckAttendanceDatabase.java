package pages;

import java.io.IOException;
import java.sql.Connection;
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
			Connection conn = CreateDatabase.connect();
			Statement stat = conn.createStatement();
			Long date = (new SimpleDateFormat("ddMMyyyy").parse(request.getParameter("date").replaceAll("/", "")).getTime()) / 1000;
			ResultSet rs = stat.executeQuery("SELECT StudentID, Attendance FROM Attendance_Record WHERE Date = " + date + ";");
			int StudentID;
			boolean Attendance = false;
			String name;
			String Class;
			List<String> rows = new ArrayList<String>();
			while (rs.next()) {
				StudentID = rs.getInt(1);
				if (rs.getInt(2) == 1) {
					Attendance = true;
				}
				ResultSet rs2 = stat.executeQuery("Select LastName, FirstName, Class FROM Student_Details WHERE StudentID = '" + StudentID + "';");
				name = rs2.getString(1) + " " + rs2.getString(2);
				Class = rs.getString(3);
				rs2.close();
				String tempString = name + "?<tr> <td> " + StudentID + "</td> <td> " + name + " </td> <td> " + Attendance + " </td> <td> " + Class + " </td> </tr>";
				rows.add(tempString);
			}
			rows = rows.stream().sorted().collect(Collectors.toList());
			List<String> Rows = new ArrayList<String>();
			for (int i = 0; i < rows.size(); i++) {
				Rows.add(rows.get(i).split("\\?")[1]);
			}
			request.setAttribute("Rows", Rows);
			request.getRequestDispatcher("CheckAttendance.jsp").forward(request, response);
		}
		catch(Exception e) {
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
