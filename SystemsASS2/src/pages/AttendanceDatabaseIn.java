package pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
			Connection conn = CreateDatabase.connect();
			ResultSet rs;
			List<String> names = new ArrayList<String>();
			String Class = request.getParameter("class2");
			ArrayList<String> fnames = new ArrayList<String>();
			ArrayList<String> lnames = new ArrayList<String>();
			
			PreparedStatement pstat = conn.prepareStatement("SELECT FirstName, LastName FROM Student_Details WHERE Class = ?");
			pstat.setString(1, Class);
			rs = pstat.executeQuery();
			while(rs.next()) {
				String tempf = rs.getString("FirstName");
				String templ = rs.getString("LastName");
				lnames.add(templ);
				fnames.add(tempf);
				names.add(templ + " " + tempf);
			}
			
			Long date = (new SimpleDateFormat("ddMMyyyy").parse(request.getParameter("Adate").replaceAll("/", "")).getTime()) / 1000;
			response.getWriter().print(names.get(0) + " " + date + " " + Class);
			for (int i = 0; i < names.size(); i ++) {
				pstat = conn.prepareStatement("SELECT StudentID FROM Student_Details WHERE FirstName = ? AND LastName = ? AND Class = ?");
				pstat.setString(1, fnames.get(i));
				pstat.setString(2, lnames.get(i));
				pstat.setString(3, Class);
				rs = pstat.executeQuery();
				int StudentID = rs.getInt(1);
				pstat = conn.prepareStatement("INSERT OR REPLACE INTO Attendance_Record (StudentID, Date, Attendance) VALUES (?, ?, ?);");
				pstat.setInt(1, StudentID);
				pstat.setLong(2, date);
				try {
					if(request.getParameter(names.get(i)).equals("on")) {
						pstat.setInt(3, 1);
					}
				}
				catch(Exception e) {
					pstat.setInt(3, 0);				
					}
				pstat.execute();
			}
			request.getRequestDispatcher("PreAttendance.jsp").forward(request, response);
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
