package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dealerhome
 */
@WebServlet("/dealerhome")
public class dealerhome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dealerhome() {
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
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String pnumber = request.getParameter("pnumber");
		
		//check from database
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe","system","12345");
		
		String sql = "select * from customers where fullname= ? and email= ? and address = ? and pnumber = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,fullname);
		pstmt.setString(2,email);
		pstmt.setString(3,address);
		pstmt.setString(4,pnumber);
		
		
	    ResultSet rs = pstmt.executeQuery();
	    
	    if(rs.next()) {
	      //successful login
	      RequestDispatcher rd = request.getRequestDispatcher("home.html");
	      rd.forward(request, response);
	    }
	    else {
	      //login failed
	      RequestDispatcher rd = request.getRequestDispatcher("loginfail.html");
	      rd.forward(request, response);
	      
	    }
		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}


	}

