package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Error;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class feedback
 */
@WebServlet("/feedback")
public class feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public feedback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String rate = request.getParameter("rate");
		String lacking = request.getParameter("lacking");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("driver loaded");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","system","12345");
            String sql = "insert into feedback values(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, rate);
            pstmt.setString(3, lacking);
            
            int count = pstmt.executeUpdate();
            if (count > 0)
            {
            	String sql1 = "commit;";
            	RequestDispatcher rd = request.getRequestDispatcher("home");
            	rd.forward(request, response);
            }
            else {
            	RequestDispatcher rd = request.getRequestDispatcher("loginfail");
            	rd.forward(request, response);
            }
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
