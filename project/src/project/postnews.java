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
 * Servlet implementation class postnews
 */
@WebServlet("/postnews")
public class postnews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public postnews() {
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
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String address = request.getParameter("address");
		String dateoc = request.getParameter("dateoc");
		String headlines = request.getParameter("headlines");
		String story = request.getParameter("story");
		String reportername = request.getParameter("reportername");
		String reporterid = request.getParameter("reporterid");
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("driver loaded");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe","system","12345");
            
            String sql = "insert into news(country,state,address,dateoc,headlines,story,reportername,reporterid) values(?,?,?,?,?,?,?,?)";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, country);
            pstmt.setString(2, state);
            pstmt.setString(3, address);
            pstmt.setString(4, dateoc);
            pstmt.setString(5, headlines);
            pstmt.setString(6, story);
            pstmt.setString(7, reportername);
            pstmt.setString(8, reporterid);
            
            int count = pstmt.executeUpdate();
            String sql2 = "commit;";
            // Redirect

            if(count >0) {
    			//successful login
            	 String sql1 = "commit;";
    			RequestDispatcher rd = request.getRequestDispatcher("loginsuccess.html");
    			rd.forward(request, response);
    		}
    		else {
    			//login failed
    			RequestDispatcher rd = request.getRequestDispatcher("registerfail.html");
    			rd.forward(request, response);
    			
    		}
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
