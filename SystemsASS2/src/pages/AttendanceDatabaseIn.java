package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AttendanceDatabaseIn
 */
public class AttendanceDatabaseIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceDatabaseIn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Statement stat = CreateDatabase.connect();
			ResultSet rs = null;
			@SuppressWarnings("unchecked")
			ArrayList<String> names = new ArrayList<String>((Collection<? extends String>) request.getParameterNames());
			ArrayList<String> fnames = new ArrayList<String>();
			ArrayList<String> lnames = new ArrayList<String>();
			for(int i = 0; i < names.size(); i ++) {
				String[] temp = names.get(i).split("\\.");
				lnames.add(temp[0]);
				fnames.add(temp[1]);
				response.getWriter().print(temp[1] + " " + temp[2]);
			}
			Long date = (new SimpleDateFormat("ddMMyyyy").parse(request.getParameter("adate").replaceAll("/", "")).getTime()) / 1000;
			for (int i = 0; i < names.size(); i ++) {
				rs = stat.executeQuery("SELECT StudentID FROM Student_Details WHERE FirstName = '" + fnames.get(i) + "' AND LastName = '" + lnames.get(i) + "' AND Class = '" + request.getParameter("class2") + "';");
				int StudentID = rs.getInt(1);
				try {
					if(request.getParameter(names.get(i)).equals("on")) {
						stat.execute("INSERT INTO Attendance_Record (StudentID, Date, Attendance) VALUES (" + StudentID + ", " + date + ", " + 1 + ");");
					}
				}
				catch(Exception e) {
					stat.execute("INSERT INTO Attendance_Record (StudentID, Date, Attendance) VALUES (" + StudentID + ", " + date + ", " + 0 + ");");
				}
			}
			request.getRequestDispatcher("PreAttendance.jsp").forward(request, response);
		}
		catch(Exception e) {
			
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
