package pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTestNames
 */
public class AddTestNames extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTestNames() {
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
			String Class = request.getParameter("Class");
			ResultSet rs = null;
			if (Class.equals("all")) {
				rs = stat.executeQuery("SELECT StudentID FROM Student_Details");
			}
			else {
				pstat = conn.prepareStatement("SELECT StudentID FROM Student_Details WHERE Class = ?");
				pstat.setString(1, Class);
				rs = pstat.executeQuery();
			}
			String name = request.getParameter("Name");
			String subject = request.getParameter("Subject");
			while(rs.next()) {
				pstat = conn.prepareStatement("INSERT INTO Test_Record (StudentID, TestID, Subject) VALUES (?, ?, ?);");
				pstat.setInt(1, rs.getInt(1));
				pstat.setString(2, name);
				pstat.setString(3, subject);
				pstat.execute();
			}
			response.getWriter().print("<form action = \"TestAdder.jsp\"> <input type = \"submit\" value = \"Back\"> </form> <form action = \"TestMarkerPasser\"> <input type = \"hidden\" name = \"TestName\" value = \"" + name + "\"> <input type = \"submit\" value = \"Mark test\"> </form>");
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
