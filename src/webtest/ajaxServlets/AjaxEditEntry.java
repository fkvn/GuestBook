package webtest.ajaxServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxEditEntry
 */
@WebServlet("/AjaxEditEntry")
public class AjaxEditEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxEditEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null &&!request.getParameter("id").isEmpty())
		{
			int id = Integer.valueOf(request.getParameter("id"));

			if (request.getParameter("name") != null &&!request.getParameter("name").isEmpty()
					&& request.getParameter("message") != null && !request.getParameter("message").isEmpty())

			{
				String name = request.getParameter("name");
				String message = request.getParameter("message");

				Connection c = null;
				try
				{
					String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu35?useSSL=false&allowPublicKeyRetrieval=true";
					String username = "cs3220stu35";
					String password = "*#61VJXW";

					System.out.println("id: " + id);
					System.out.println("name: " + name);
					System.out.println("message: " + message);

					String updateCandidate = "update guestbook set name = ?, message = ? where id = ?;";

					c = DriverManager.getConnection( url, username, password );
					PreparedStatement pstmt = c.prepareStatement(updateCandidate);

					pstmt.setString(1,  name);
					pstmt.setString(2,  message);
					pstmt.setInt(3,  id);

					pstmt.executeUpdate();

					c.close();
				}
				catch( SQLException e )
				{
					e.printStackTrace();
				}
				finally
				{
					try
					{
						if( c != null ) c.close();
					}
					catch( SQLException e )
					{
						e.printStackTrace();
					}
				}
			}
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
