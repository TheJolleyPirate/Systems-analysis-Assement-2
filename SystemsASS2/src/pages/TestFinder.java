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
 * Servlet implementation class TestFinder
 */
public class TestFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestFinder() {
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
			Statement stat2 = conn.createStatement();
			PreparedStatement pstat = null;
			ResultSet rs = null;
			String searchBy = request.getParameter("searchBy");
			String searchvalue = request.getParameter("searchvalue");
			int StudentID;
			String name;
			String TestName;
			String Subject;
			int Score;
			long date;
			//response.getWriter().print("running");
			if(searchBy.equals("TestName")) {
				pstat = conn.prepareStatement("SELECT*FROM Test_Record WHERE TestID = ?;");
				pstat.setString(1, searchvalue);
			}
			else if(searchBy.equals("subject")) {
				pstat = conn.prepareStatement("SELECT*FROM Test_Record WHERE Subject = ?;");
				pstat.setString(1, searchvalue);
			}
			else {
				pstat = conn.prepareStatement("SELECT*FROM Test_Record;");
			}
			rs = pstat.executeQuery();
			String Ndate;
			List<String> rows = new ArrayList<String>();
			//response.getWriter().print("preloop");
			while(rs.next()) {
				//response.getWriter().print("inloop");
				StudentID = rs.getInt("StudentID");
				TestName = rs.getString("TestID");
				Subject = rs.getString("Subject");
				Score = rs.getInt("Score");
				date = rs.getLong("CompletedOn");
				//response.getWriter().print(date);
				if (rs.wasNull() == false) {
					SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy"); 
					Ndate = sdf.format(date * 1000);
					//response.getWriter().print(Ndate);
				}
				else {
					Ndate = null;
				}
				ResultSet rs2 = stat2.executeQuery("Select LastName, FirstName, Class FROM Student_Details WHERE StudentID = '" + StudentID + "';");
				name = rs2.getString(1) + " " + rs2.getString(2);
				String temp = TestName + name + "?<tr> <td> " + StudentID + "</td> <td> " + name + " </td> <td> " + TestName + " </td> <td> " + Subject + " </td> <td> "  + Score + " </td> <td> " + Ndate + " </td> <td> <form action = \"TestFinder\" method = \"post\"> <input type = \"hidden\" name = \"searchBy\" value = \"" + searchBy + "\"> <input type = \"hidden\" name = \"searchvalue\" value = \"" + searchvalue + "\"> <input type = \"hidden\" name = \"TestName\" value = \"" + TestName + "\"> <input type = \"hidden\" name = \"StudentID\" value = \"" + StudentID + "\"> Score: <input type = \"text\" id = \"Score\" name = \"Score\" value = \""+ Score + "\"> <br>Date Completed: <input type = \"text\" id = \"date\" name = \"date\" value = \"" + Ndate + "\"> <br> <input type = \"submit\" value = \"Mark\"> </form> </td> </tr>";
				//response.getWriter().print(temp);
				rows.add(temp);
			}
			//response.getWriter().print("postloop");
			rows = rows.stream().sorted().collect(Collectors.toList());
			List<String> Rows = new ArrayList<String>();
			for (int i = 0; i < rows.size(); i++) {
				Rows.add(rows.get(i).split("\\?")[1]);
			}
			request.setAttribute("Rows", Rows);
			request.setAttribute("TestName", searchvalue);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		request.getRequestDispatcher("TestMarker.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = CreateDatabase.connect();
			Long date = (new SimpleDateFormat("ddMMyyyy").parse(request.getParameter("date").replaceAll("/", "")).getTime()) / 1000;
			int Score = Integer.parseInt(request.getParameter("Score"));
			int StudentID =  Integer.parseInt(request.getParameter("StudentID"));
			String TestName = request.getParameter("TestName");
			PreparedStatement pstat = conn.prepareStatement("UPDATE Test_Record SET Score = ?, CompletedOn = ? WHERE StudentID = ? AND TestID = ?");
			pstat.setInt(1, Score);
			pstat.setLong(2, date);
			pstat.setInt(3, StudentID);
			pstat.setString(4, TestName);
			pstat.execute();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
