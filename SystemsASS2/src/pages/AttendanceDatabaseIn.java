package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
		ArrayList<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Paul");
		names.add("Ringo");
		names.add("George");
		PrintWriter out = response.getWriter();
		for (int i = 0; i < names.size(); i ++) {
			out.print(names.get(i));
			try {
				if(request.getParameter(names.get(i)).equals("on")) {
					out.print(": present");
				}
				else {
					throw new Exception();
				}
			}
			catch(Exception e) {
				out.print(": absent");
			}
			out.print("<br>");
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
