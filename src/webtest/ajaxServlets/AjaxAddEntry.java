package webtest.ajaxServlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxAddEntry
 */
@WebServlet("/AjaxAddEntry")
public class AjaxAddEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxAddEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;

		Connection c = null;
		try
		{
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu35?useSSL=false&allowPublicKeyRetrieval=true";
			String username = "cs3220stu35";
			String password = "*#61VJXW";

			String sql = "insert into guestbook (name, message, date) values (?, ?, now())";

			c = DriverManager.getConnection( url, username, password );
			PreparedStatement pstmt = c.prepareStatement( sql,
					Statement.RETURN_GENERATED_KEYS );
			pstmt.setString( 1, request.getParameter( "name" ) );
			pstmt.setString( 2, request.getParameter( "message" ) );
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if( rs.next() ) id = rs.getInt( 1 );
			c.close();
		}
		catch( SQLException e )
		{
			throw new ServletException( e );
		}
		finally
		{
			try
			{
				if( c != null ) c.close();
			}
			catch( SQLException e )
			{
				throw new ServletException( e );
			}
		}

		response.setContentType( "text/plain" );
		response.getWriter().print( id );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
