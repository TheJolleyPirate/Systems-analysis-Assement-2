package pages;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LeaderboardDatabase
 */
public class LeaderboardDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaderboardDatabase() {
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
			ResultSet rs = stat.executeQuery("SELECT StudentID, FirstName, LastName FROM Student_Details;");
			int StudentID;
			int total;
			int completed;
			int av;
			String name;
			List<String> table = new ArrayList<String>();
			while(rs.next()) {
				StudentID = rs.getInt("StudentID");
				name = rs.getString("LastName") + " " + rs.getString("FirstName");
				total = 0;
				completed = 0;
				ResultSet rs2 = stat2.executeQuery("SELECT Score, CompletedOn FROM Test_Record WHERE StudentID = " + StudentID + ";");
				while(rs2.next()) {
					total += rs2.getInt(1);
					rs2.getLong(2);
					if(rs2.wasNull() == false) {
						completed += 1;
					}
				}
				if (completed > 0) {
					av = total / completed;
				}
				else {
					av = 0;
				}
				table.add(av + completed + "?<tr> <td> " + StudentID + "</td> <td> " + name + " </td> <td> " + av + " </td> <td> " + completed + " </td> </tr>");
			}
			Collections.sort(table, new AlphanumComparator());
			List<String> Rows = new ArrayList<String>();
			for (int i = 0; i < table.size(); i++) {
				Rows.add(table.get(i).split("\\?")[1]);
			}
			Collections.reverse(Rows);
			request.setAttribute("Rows", Rows);
			request.getRequestDispatcher("Leaderboard.jsp").forward(request, response);
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
