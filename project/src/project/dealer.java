package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dealer
 */
@WebServlet("/dealer")
public class dealer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dealer() {
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
		String password = request.getParameter("password");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String pnumber = request.getParameter("pnumber");
		String address = request.getParameter("address");
		String officelocation = request.getParameter("officelocation");
	
		
		try 
		{
	     
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	            System.out.println("driver loaded");
	            Connection conn = DriverManager.getConnection(
	                    "jdbc:oracle:thin:@localhost:1521:xe","system","12345");

	            System.out.println("connected to database");


	            String sql = "insert into dealerdata(fname,lname,email,password,pnumber,address,officelocation) values(?,?,?,?,?,?,?)";
	            //set values for ?

	            PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, fname);
	            pstmt.setString(2, lname);
	            pstmt.setString(3, email);
	            pstmt.setString(4, password);
	            pstmt.setString(5, pnumber);      
	            pstmt.setString(6, address);
	            pstmt.setString(7, officelocation);
	      
	           
	            System.out.println("Executed");
	            int count = pstmt.executeUpdate();
	            String sql2 = "commit;";
	            // Redirect

	            if(count >0) {
	    			//successful login
	            	 String sql1 = "commit;";
	    			RequestDispatcher rd = request.getRequestDispatcher("dealerlogin.html");
	    			rd.forward(request, response);
	    		}
	    		else {
	    			//login failed
	    			RequestDispatcher rd = request.getRequestDispatcher("registerfail.html");
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


